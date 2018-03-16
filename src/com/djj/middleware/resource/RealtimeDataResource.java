package com.djj.middleware.resource;

import com.djj.middleware.entities.DeviceEntity;
import com.djj.middleware.entities.IntersectionEntity;
import com.djj.middleware.entities.LaneEntity;
import com.djj.middleware.resource.POJOResource.InterSectionID;
import com.djj.middleware.resource.POJOResource.RealtimeData;
import com.djj.middleware.utils.DBUtil;
import com.djj.middleware.utils.HibernateSessionFactory;
import com.djj.middleware.utils.RadarUtil;
import org.hibernate.Session;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Path("/realtimeData")
public class RealtimeDataResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RealtimeData getRealtimeData() {
        System.out.println("hhhh");
        //1.创建Session
        Session session = HibernateSessionFactory.getSession();

        Connection conn = DBUtil.getConnection();
        Statement stmt = null;

        //2.为所有雷达建立tcp连接并请求一帧数据
        //找到所有路口
        List<IntersectionEntity> interSectionList = session.createNativeQuery("select * from intersection"
        ).addEntity(IntersectionEntity.class).list();
        //容错
        if (interSectionList.isEmpty()) {
            return new RealtimeData();
        }

        for (IntersectionEntity i : interSectionList) {
            //找到路口的所有雷达
            List<DeviceEntity> deviceList = session.createNativeQuery("select * from device where InterSectionID = "
                    + i.getInterSectionId()).addEntity(DeviceEntity.class).list();
            //路口无设备，跳过
            if (deviceList.isEmpty()) {
                break;
            }

            for (DeviceEntity d : deviceList) {
                //与所有雷达建立tcp连接，解析雷达数据获得所有目标，每隔50ms获取一次并更新数据库
                List<LaneEntity> lanes = session.createNativeQuery("select * from lane where DeviecNo = "
                        + d.getDeviceNo()).addEntity(LaneEntity.class).list();
                //路口没记录车道，跳过
                if (lanes.isEmpty()) {
                    break;
                }

                RadarUtil.getRealTimeData(i.getInterSectionId(), d.getDir(), d.getDeviceNo(),
                        lanes, d.getDeviceIp(), d.getDevicePort(),conn,stmt);
            }


        }

        session.close();

        RealtimeData realtimeData = new RealtimeData();
        return realtimeData;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RealtimeData getRealtimeData(InterSectionID interSectionID) {
        String interSectionIDFinal = interSectionID.getInterSectionID();
        System.out.println(interSectionIDFinal);

        //1.根据路口号获得所有雷达
        // 创建Session
        Session session = HibernateSessionFactory.getSession();


        //2.为所有雷达建立tcp连接并请求一帧数据
        List<DeviceEntity> deviceList = session.createNativeQuery("select * from device where InterSectionID = "
                + interSectionIDFinal).addEntity(DeviceEntity.class).list();

        if (deviceList.isEmpty()){
            RealtimeData errorRealtime = new RealtimeData();
            errorRealtime.setInterSectionID("查无此路口");
            return errorRealtime;
        }

        //一级信息
        RealtimeData realtimeData = new RealtimeData();
        realtimeData.setInterSectionID(interSectionIDFinal);
        realtimeData.setDirNum((Integer) session.createNativeQuery("select DirNum from intersection where InterSectionID = "
                + interSectionIDFinal).list().get(0));
        ArrayList<RealtimeData.DirListBean> dirList = new ArrayList<RealtimeData.DirListBean>();
        realtimeData.setDirList(dirList);

        /*for (DeviceEntity d : deviceList) {
            System.out.println(d.getDeviceNo() + "--" + d.getDeviceIp() + "--" + d.getDevicePort());

            //二级信息
            RealtimeData.DirListBean dirListBean = new RealtimeData.DirListBean();
            dirList.add(dirListBean);
            dirListBean.setRadarNo(d.getDeviceNo());
            List<LaneEntity> lanes = session.createNativeQuery("select * from lane where DeviecNo = "
                    + d.getDeviceNo()).addEntity(LaneEntity.class).list();
            dirListBean.setLaneNum(lanes.size());
            ArrayList<RealtimeData.DirListBean.LaneListBean> laneList = new ArrayList<RealtimeData.DirListBean.LaneListBean>();
            dirListBean.setLaneList(laneList);

            //3.解析雷达数据
            ArrayList<HashMap> realTimeDataMap = RadarUtil.getRealTimeData(d.getDeviceIp(), d.getDevicePort());
            if (realTimeDataMap != null) {
                ArrayList<HashMap> tempMapArray = new ArrayList<HashMap>();
                //获取每个目标所属车道
                for (HashMap h : realTimeDataMap) {
                    RealtimeData.DirListBean.LaneListBean.ObjListBean objListBean = new RealtimeData.DirListBean.LaneListBean.ObjListBean();
                    objListBean.setObjID((Integer) h.get("Id"));
                    objListBean.setObjLength((Float) h.get("Length"));
                    objListBean.setObjSpeed((Float) h.get("Speed_X"));
                    Float y_point = (Float) h.get("Y_Point");
                    for (LaneEntity l : lanes) {
                        if (l.getPointY() - (l.getWidth() / 2) < y_point && y_point <= l.getPointY() + (l.getWidth() / 2)) {
                            //属于该车道，将该目标加入对应车道Bean类下
                            Float x_point = (Float) h.get("X_Point");
                            objListBean.setObjPos(x_point - l.getPointX());

                            HashMap hashMap = new HashMap();
                            hashMap.put("LaneID", l.getLaneId());
                            hashMap.put("obj", objListBean);
                            tempMapArray.add(hashMap);
                            break;
                        }
                    }
                }

                for (LaneEntity l : lanes) {
                    //三级信息
                    RealtimeData.DirListBean.LaneListBean laneListBean = new RealtimeData.DirListBean.LaneListBean();
                    laneList.add(laneListBean);

                    laneListBean.setLaneID(l.getLaneId());
                    ArrayList<RealtimeData.DirListBean.LaneListBean.ObjListBean> objList = new ArrayList<RealtimeData
                            .DirListBean.LaneListBean.ObjListBean>();
                    laneListBean.setObjList(objList);

                    //找出属于该车道的目标，计算总的目标数
                    int cnt = 0;
                    for (HashMap h : tempMapArray) {
                        if ((Integer) h.get("LaneID") == l.getLaneId()) {
                            objList.add((RealtimeData.DirListBean.LaneListBean.ObjListBean) h.get("obj"));
                            cnt++;
                        }
                    }
                    laneListBean.setObjNum(cnt);
                }
            }

        }

        session.close();*/

        //4.组装数据返回json
        return realtimeData;



        /*//开启一个队列
        Queue<Byte> queueTemp = new LinkedList<Byte>();
        Queue<Byte> queueRemain = new LinkedList<Byte>();
        Queue<Byte> queueFinal = new LinkedList<Byte>();
        //建立TCP向雷达请求一帧数据
        try {
            //1.与雷达建立连接
            Socket realDataSck = new Socket(PropertiesUtil.REALTIMEDATA_IP, PropertiesUtil.REALTIMEDATA_PORT);
            realDataSck.setSoTimeout(10000);
            //2.向雷达发送请求
            OutputStream os = realDataSck.getOutputStream();
            os.write("ff".getBytes());
            //3.接收雷达返回的数据
            InputStream is = realDataSck.getInputStream();
            byte buf[] = new byte[1024];
            int len;
            //4.处理断包、粘包
            if ((len = is.read(buf)) > 0) {
                while (true) {
                    //读入数据
                    byte[] bufFinal = new byte[len];
                    System.arraycopy(buf, 0, bufFinal, 0, len);

                    //判断残余缓冲区中是否有数据,如果有则存入临时缓冲区
                    if (!queueRemain.isEmpty()) {
                        queueTemp = new LinkedList<Byte>(queueRemain);
                        queueRemain.clear();
                    }

                    //将数据全部存入临时缓冲区
                    for (byte b : bufFinal) {
                        queueTemp.offer(b);
                    }

                    //将临时缓冲区转为数组
                    byte[] tempBytes = RadarUtil.QueueToBytes(queueTemp);

                    //在临时缓冲区中寻找包头包尾
                    byte[] head = {-54, -53, -52, -51};
                    byte[] tail = {-22, -21, -20, -19, 97, 103, 92, 20, -119, -58};

                    int headIndex = RadarUtil.indexOfArray(tempBytes, head);
                    int tailIndex = RadarUtil.indexOfArray(tempBytes, tail);

                    //TODO 包头........包头.......包尾      .......包尾包头.........包尾
                    if (headIndex >= 0 && tailIndex >= 0 && headIndex < tailIndex) {
                        //找到了包头包尾，则提取出一帧放入最终缓冲区,如有多帧数据直接覆盖
                        queueFinal.clear();
                        for (int i = headIndex; i < tailIndex + 10; i++) {
                            queueFinal.offer(tempBytes[i]);
                        }

                        //粘包，包尾后还有内容，则放入残余缓存区
                        if (tailIndex + 10 < tempBytes.length) {
                            for (int i = tailIndex + 10; i < tempBytes.length; i++) {
                                queueRemain.offer(tempBytes[i]);
                            }
                            Thread.sleep(10);
                            System.out.println("粘包了");
                            len = is.read(buf);
                        } else {
                            break;
                        }
                    } else if (headIndex == -1 || tailIndex == -1) {
                        //断包
                        for (int i = 0; i < tempBytes.length; i++) {
                            queueRemain.offer(tempBytes[i]);
                        }
                        Thread.sleep(10);
                        System.out.println("断包了");
                        len = is.read(buf);
                    }
                }

                byte[] databytes = RadarUtil.QueueToBytes(queueFinal);

                //5.解析雷达数据
                RadarUtil.RealTimeAnalyze(databytes);
                Thread.sleep(50);
            } else {
                Thread.sleep(10);
            }

            realDataSck.close();

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }*/
        //return new RealtimeData();
    }
}
