package com.djj.middleware.utils;

import com.djj.middleware.entities.LaneEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * @author   丁俊杰
 * @name     雷达解析类
 * @time     2018/3/2
 * */
public class RadarUtil {
    /*
     * @func     将byte转为64位的long
     *
     * @param    bytes 8字节数组
     * @return   64位无符号整数
     * */
    public static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();
        return buffer.getLong();
    }

    /**
     * @param data 处理的数据
     *             low     低位
     *             high    高位
     * @return 2字节整数
     * @func 从数组的第start开始，提取8个字节，组成Long，低字节在前
     * 在long中提取从high位到low位的数据
     */
    public static short Longto(long data, int low, int high) {
        try {
            int length = high - low + 1;
            short tempint;
            short tempand;
            tempand = (short) ((1 << length) - 1);
            data = data >> low;
            tempint = (short) data;

            return (short) (tempint & tempand);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /*
     * @func    分析雷达实时数据
     *
     * @param   datatype 雷达数据
     * @return  返回车况信息
     * */
    public static ArrayList<HashMap> RealTimeAnalyze(Object datatype) {
        ArrayList<HashMap> RealTimeData = new ArrayList<HashMap>();

        byte[] CarExist;    //存在信号
        byte[] QueuePos;    //排队位置
        byte[] QueueLength; //排队长度
        byte[] realtimedata = (byte[]) datatype;

        int length, count;
        short temp;
        long data;

        try {
            ArrayList<Byte> array = new ArrayList<Byte>();
            length = 4;
            count = 1;

            //即时目标数据的解析
            int id;
            float l, xs, ys, xp, yp;

            while (realtimedata[length] == 0x05) {
                length += 3;
                array.clear();
                array.add(realtimedata[length++]);
                array.add(realtimedata[length++]);
                array.add(realtimedata[length++]);
                array.add(realtimedata[length++]);
                array.add(realtimedata[length++]);
                array.add(realtimedata[length++]);
                array.add(realtimedata[length++]);
                array.add(realtimedata[length++]);
                byte[] arrayBytes = new byte[8];
                for (int i = 0; i < array.size(); i++) {
                    arrayBytes[i] = array.get(i);
                }
                data = bytesToLong(arrayBytes);
                System.out.format("%x", data);

                id = Longto(data, 56, 63);
                l = Longto(data, 49, 55) * 0.2f;
                temp = Longto(data, 38, 48);
                ys = (temp - 1024) * 0.36f;
                temp = Longto(data, 27, 37);
                xs = (temp - 1024) * 0.36f;
                temp = Longto(data, 14, 26);
                yp = (temp - 4096) * 0.128f;
                temp = Longto(data, 1, 13);
                xp = (temp - 4096) * 0.128f;

                HashMap RealTimeDataMap = new HashMap();
                RealTimeDataMap.put("Row_No", count);
                RealTimeDataMap.put("Id", id);
                RealTimeDataMap.put("Length", l);
                RealTimeDataMap.put("Speed_Y", ys);
                RealTimeDataMap.put("Speed_X", xs);
                RealTimeDataMap.put("Y_Point", yp);
                RealTimeDataMap.put("X_Point", xp);
                RealTimeData.add(RealTimeDataMap);
                count++;
            }

            QueueLength = new byte[8];
            if (realtimedata[length] == 0x06 && realtimedata[length + 1] == 0x00) {
                length += 3;
                QueueLength[0] = realtimedata[length++];
                QueueLength[1] = realtimedata[length++];
                QueueLength[2] = realtimedata[length++];
                QueueLength[3] = realtimedata[length++];
                QueueLength[4] = realtimedata[length++];
                QueueLength[5] = realtimedata[length++];
                QueueLength[6] = realtimedata[length++];
                QueueLength[7] = realtimedata[length++];
            }

            QueuePos = new byte[8];
            if (realtimedata[length] == 0x06 && realtimedata[length + 1] == 0x01) {
                length += 3;
                QueuePos[0] = realtimedata[length++];
                QueuePos[1] = realtimedata[length++];
                QueuePos[2] = realtimedata[length++];
                QueuePos[3] = realtimedata[length++];
                QueuePos[4] = realtimedata[length++];
                QueuePos[5] = realtimedata[length++];
                QueuePos[6] = realtimedata[length++];
                QueuePos[7] = realtimedata[length++];
            }

            CarExist = new byte[8];
            if (realtimedata[length] == 0x06 && realtimedata[length + 1] == 0x02) {
                length += 3;
                CarExist[0] = realtimedata[length++];
                CarExist[1] = realtimedata[length++];
                CarExist[2] = realtimedata[length++];
                CarExist[3] = realtimedata[length++];
                CarExist[4] = realtimedata[length++];
                CarExist[5] = realtimedata[length++];
                CarExist[6] = realtimedata[length++];
                CarExist[7] = realtimedata[length++];
            }

            //排队长度，排队位置，线圈状态的推送
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            String endTime = sdf.format(System.currentTimeMillis());
            System.out.println(endTime + "\n");
            System.out.println(RealTimeData.toString());
            return RealTimeData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /* *
     * @func    用于在数组中查找特定的数组*
     *
     * @param   container 在哪个数组中查找特定的数组
     *          target    需要查找的数组
     * @return  target在container中的索引值，如果不存在则返回-1
     * */
    public static int indexOfArray(List<Byte> container, byte[] target) {
        int start = 0;
        if (target.length > 0) {
            if (target.length > container.size()) {
                return -1;
            }
            // 针对｛1，2，3，2，3，4｝中查找｛2，3，4｝第一次出现23的时候不符合但不能终止查找，所以要继续
            while (true) {
                // 从第一个匹配到的位置开始查找
                for (; start < container.size(); start++) {
                    if (container.get(start) == target[0])
                        break;
                }

                if (start == container.size()
                        || target.length + start > container.size()) {
                    return -1;
                }
                int o1 = start, o2 = 0;
                while (o1 < container.size() && o2 < target.length) {
                    if (!(container.get(o1) == target[o2])) {
                        break;
                    }
                    o1++;
                    o2++;
                }
                if (o2 == target.length) {
                    return start;
                }
                start++;
            }
        }
        return -1;
    }

    /*
     * @func     将队列转为字节数组
     *
     * @param    queue 需转换队列
     * @return   bytes 转化成的字节数组
     * */
    public static byte[] QueueToBytes(Queue<Byte> queue) {
        int queueLen = queue.size();
        byte[] bytes = new byte[queueLen];
        for (int i = 0; i < queueLen; i++) {
            bytes[i] = queue.poll();
        }
        return bytes;
    }


    /*
    * @func 与雷达建立长连接，获取并操作实时数据
    * @param    interSectionId  路口号
    *           dir             方向号
    *           deviceNo        设备号
    *           lanes           车道信息
    *           deviceIp        设备ip
    *           devicePort      设备端口
    *           conn            数据库
    *           stmt            数据库statement
    * @return   null
    * */
    public static void getRealTimeData(String interSectionId, short dir, String deviceNo,
                                       List<LaneEntity> lanes, String deviceIp, int devicePort, Connection conn, Statement stmt) {
        //开启一个队列
        List<Byte> queueFinal = new LinkedList<Byte>();
        try {
            /*stmt = conn.createStatement();
            String sql;
            for (int i = 0; i < 128; i++) {
                sql = "select * from where inter_id = " + interSectionId + " and dir_num = " + dir + " and obj_id = " + i;
                ResultSet rs = stmt.executeQuery(sql);
                while (!rs.next()) {
                    sql = "INSERT INTO radar_rt(inter_id, dir_num, radar_no, lane_id, obj_id, obj_pos," +
                            "obj_speed, obj_length, time_create, gmt_create)"
                            + "values(" + "?,?,?,?,?,?,?,?,?,?)";

                    PreparedStatement ptmt = conn.prepareStatement(sql);

                    ptmt.setString(1, interSectionId);
                    ptmt.setInt(2, dir);
                    ptmt.setString(3, deviceNo);
                    ptmt.setInt(4, 0);
                    ptmt.setInt(5, i);

                    ptmt.execute();
                }
            }*/
            //1.与雷达建立连接
            Socket realDataSck = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(deviceIp, devicePort);
			//确保在网络不通畅或雷达设备故障的情况下也能持续连接
            while (true) {
                try {
                    realDataSck.connect(socketAddress, 2000);
                    break;
                } catch (Exception e) {
                    System.out.println("重连");
                    realDataSck.close();
                    realDataSck = new Socket();
                }

            }
            realDataSck.setSoTimeout(2000);
            //2.准备请求
            OutputStream os = realDataSck.getOutputStream();
            InputStream is = realDataSck.getInputStream();

            //定义包头包尾
            byte[] head = {-54, -53, -52, -51};
            byte[] tail = {-22, -21, -20, -19, 97, 103, 92, 20, -119, -58};
            int headIndex = -1;
            int tailIndex = -1;

            os.write("ff".getBytes());

            //3.建立长连接
            while (true) {
				//判断远端服务器是否断开连接了
                if (!isServerClose(realDataSck)) {
                    //3.接收雷达返回的数据
                    //判断输入流是否有数据，如果没有则等待10ms
                    if (is.available() > 0) {
                        int len = is.available();
                        byte buf[] = new byte[len];
                        is.read(buf, 0, len);
						//将数据全部存入临时缓冲区
                        for (byte b : buf) {
                            queueFinal.add(b);
                        }
                        //4.处理断包、粘包
                        while (true) {
                            headIndex = RadarUtil.indexOfArray(queueFinal, head);
                            tailIndex = RadarUtil.indexOfArray(queueFinal, tail);

                            if (headIndex >= 0 && tailIndex >= 0 && headIndex < tailIndex) {
                                byte[] bytesFinal = new byte[tailIndex + 10 - headIndex];
                                //找到了包头包尾，则提取出一帧放入字节缓冲区,如有多帧数据直接覆盖，同时扔掉队列缓冲区中包头前的多余字节
                                for (int i = 0; i < headIndex; i++) {
                                    queueFinal.remove(0);
                                }
								
                                for (int i = 0; i < bytesFinal.length; i++) {
                                    bytesFinal[i] = queueFinal.get(0);
                                    queueFinal.remove(0);
                                }

								//解析雷达数据并存入数据库
                                operateRealTimeData(interSectionId, dir, deviceNo, lanes, deviceIp, devicePort, conn, stmt, bytesFinal);

								//粘包，即包尾后还有内容，如果没有粘包则继续发送tcp请求收取下一帧数据
                                if (queueFinal.size() > 0) {
                                    System.out.println("粘包了");
                                } else {
                                    Thread.sleep(60);
                                    os.write("ff".getBytes());
                                    break;
                                }

                            } else if (headIndex >= 0 && tailIndex == -1 || headIndex == -1 && tailIndex == -1) {
                                //断包，即接收到的包不完整，则跳出内圈循环，进入外圈循环，从输入流中继续读取数据
                                System.out.println("断包了");
                                Thread.sleep(10);
                                break;
                            } else if ((headIndex == -1 && tailIndex >= 0) || headIndex > tailIndex) {
                                //残包，即只找到包尾或包头在包尾后面，则扔掉队列缓冲区中包尾及其之前的多余字节
                                System.out.println("残包了");
                                for (int i = 0; i < tailIndex + 10; i++) {
                                    queueFinal.remove(0);
                                }
                                //如果扔掉后队列缓冲区中为空，则可以直接进行下一轮tcp请求，否则跳出内圈循环，进入外圈循环，从输入流中继续读取数据
                                if (queueFinal.size() == 0) {
                                    Thread.sleep(60);
                                    os.write("ff".getBytes());
                                    break;
                                }
                            }

                        }

                    } else {
                        Thread.sleep(10);
                    }
                } else {
                    //如果断开了，持续重连
                    Thread.sleep(1000);
                    os.close();
                    realDataSck.close();

                    realDataSck = new Socket();
                    socketAddress = new InetSocketAddress(deviceIp, devicePort);
					//确保在网络不通畅或雷达设备故障的情况下也能持续连接
                    while (true) {
                        try {
                            realDataSck.connect(socketAddress, 2000);
                            break;
                        } catch (Exception e) {
                            System.out.println("重连");
                            realDataSck.close();
                            realDataSck = new Socket();
                        }

                    }
                    realDataSck.setSoTimeout(2000);
                    os = realDataSck.getOutputStream();
                    is = realDataSck.getInputStream();
                    queueFinal = new LinkedList<Byte>();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
    * @func     判断远程服务器是否断开
    *
    * @param    socket 待判断的套接字
    * @return   服务器是否关闭连接了
    * */
    public static Boolean isServerClose(Socket socket) {
        try {
            socket.sendUrgentData(0xFF);//发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
            return false;
        } catch (Exception se) {
            return true;
        }
    }


    /*
    * @func     对实时数据进行处理
    *
    * @param    interSectionId  路口号
    *           dir             方向号
    *           deviceNo        设备号
    *           lanes           车道信息
    *           deviceIp        设备ip
    *           devicePort      设备端口
    *           conn            数据库
    *           stmt            数据库statement
    *           databytes       16进制实时数据
    * @return   null
    *
    * */
    public static void operateRealTimeData(String interSectionId, short dir, String deviceNo,
                               List<LaneEntity> lanes, String deviceIp, int devicePort, Connection conn,
                               Statement stmt, byte[] databytes) throws Exception {

        /*//5.解析雷达数据
        ArrayList<HashMap> realTimeAnalyze = RadarUtil.RealTimeAnalyze(databytes);

        //6.删除该雷达的所有数据.将新数据插入数据库
        if (realTimeAnalyze != null) {
            //stmt = conn.createStatement();
            String sql = "delete from radar_rt where radar_no = " + deviceNo;
            //stmt.executeUpdate(sql);

            sql = "update radar_rt set lane_id = 0 where device_no = " + deviceNo;
            for (HashMap h : realTimeAnalyze) {
                //获得该目标所属车道
                Integer id = (Integer) h.get("Id");
                Float length = (Float) h.get("Length");
                Float speed = (Float) h.get("Speed_X");
                Float y_point = (Float) h.get("Y_Point");
                Float objPos = (Float) h.get("X_Point") - lanes.get(0).getPointX();
                Integer laneId = -1;
                for (LaneEntity l : lanes) {
                    if (l.getPointY() - (l.getWidth() / 2) < y_point && y_point <= l.getPointY() + (l.getWidth() / 2)) {
                        //属于该车道，将该目标加入对应车道Bean类下
                        objPos = (Float) h.get("X_Point") - l.getPointX();
                        laneId = l.getLaneId();
                        break;
                    }
                }

                sql += "\n update radar_rt set lane_id = " + laneId + ",obj_pos = " + objPos + ",obj_speed=" + speed
                            + ",obj_length = " + length + ",time_create=" + System.currentTimeMillis() + ",gmt_create = " + new Date(System.currentTimeMillis());



                Thread.sleep(50);
            }


        }*/

        //5.解析雷达数据
        ArrayList<HashMap> realTimeAnalyze = RadarUtil.RealTimeAnalyze(databytes);

        //6.删除该雷达的所有数据.将新数据插入数据库
        stmt = conn.createStatement();
        String sql = "delete from radar_rt where radar_no = '" + deviceNo+"'";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO radar_rt(inter_id, dir_num, radar_no, lane_id, obj_id, obj_pos," +
                "obj_speed, obj_length, time_create, gmt_create)"
                + "values(" + "?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        if (realTimeAnalyze != null) {
            for (HashMap h : realTimeAnalyze) {
                //获得该目标所属车道
                Integer id = (Integer) h.get("Id");
                Float length = (Float) h.get("Length");
                Float speed = Math.abs((Float) h.get("Speed_X"));
                Float y_point = (Float) h.get("Y_Point");
                Float objPos = (Float) h.get("X_Point") - lanes.get(0).getPointX();
                Integer laneId = -1;
                for (LaneEntity l : lanes) {
                    if (l.getPointY() - (l.getWidth() / 2) < y_point && y_point <= l.getPointY() + (l.getWidth() / 2)) {
                        //属于该车道，将该目标加入对应车道Bean类下
                        objPos = (Float) h.get("X_Point") - l.getPointX();
                        laneId = l.getLaneId();
                        break;
                    }
                }
                if (laneId == -1) {
                    continue;
                }

                ptmt.setString(1, interSectionId);
                ptmt.setInt(2, dir);
                ptmt.setString(3, deviceNo);
                ptmt.setInt(4, laneId);
                ptmt.setInt(5, id);
                ptmt.setDouble(6, objPos);
                ptmt.setDouble(7, speed);
                ptmt.setDouble(8, length);
                ptmt.setLong(9, System.currentTimeMillis());
                ptmt.setTimestamp(10, new Timestamp(System.currentTimeMillis()));

                ptmt.addBatch();

            }
            ptmt.executeBatch();

        }
    }


}
