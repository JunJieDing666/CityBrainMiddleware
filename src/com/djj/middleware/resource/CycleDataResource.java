package com.djj.middleware.resource;

import com.djj.middleware.entities.CycledataCoilListEntity;
import com.djj.middleware.entities.CycledataEntity;
import com.djj.middleware.entities.QueuedataQueueListEntity;
import com.djj.middleware.resource.POJOResource.CycleData;
import com.djj.middleware.utils.DBUtil;
import com.djj.middleware.utils.HibernateSessionFactory;
import com.djj.middleware.utils.HibernateSessionFactoryAli;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Path("/cycleData")
public class CycleDataResource {
    // 创建Session
    private Session session = HibernateSessionFactory.getSession();
    // 开启事务
    private Transaction transaction = session.beginTransaction();

    private Connection conn = DBUtil.getConnection();
    private String sql;
    private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public int collectRoadData(CycleData cycleData) {
        try {
            //将统计数据存入数据库
            CycledataEntity cycledataEntity = new CycledataEntity();
            cycledataEntity.setId(UUID.randomUUID().toString());
            cycledataEntity.setDeviceNo(cycleData.getDeviceNo());
            cycledataEntity.setTimestamp(cycleData.getTimestamp());
            cycledataEntity.setCycle(cycleData.getCycle());
            cycledataEntity.setCoilNum(cycleData.getCoilNum());
            // 保存
            session.save(cycledataEntity);

            //获得路口号，设备方向
            List list1 = session.createNativeQuery("select InterSectionID from device where DeviceNo = '"
                    + cycleData.getDeviceNo() + "'").list();
            String interSectionID;
            if (list1.isEmpty()) {
                interSectionID = "-1";
            } else {
                interSectionID = (String) list1.get(0);
            }

            List list2 = session.createNativeQuery("select Dir from device where DeviceNo = '"
                    + cycleData.getDeviceNo() + "'").list();
            Byte dir;
            if (list2.isEmpty()) {
                dir = -1;
            } else {
                dir = (Byte) list2.get(0);
            }

            CycledataCoilListEntity cycledataCoilListEntity = new CycledataCoilListEntity();

            //将数据存入阿里数据库 TODO
            sql = "INSERT INTO radar_statistics(inter_id, dir_num, radar_no, lane_id, volume,volume_1," +
                    "volume_2,volume_3,volume_4,volume_5,avg_speed,occupation,headway,speed_85,gap,avg_queue,gmt_create)"
                    + "values(" + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ptmt = conn.prepareStatement(sql);

            for (CycleData.CoilListBean coilListBean : cycleData.getCoil_List()) {
                cycledataCoilListEntity.setId(cycledataEntity.getId());
                cycledataCoilListEntity.setMeasNo(coilListBean.getMeasNo());
                cycledataCoilListEntity.setLaneNo(coilListBean.getLaneNo());
                cycledataCoilListEntity.setCoilNo(coilListBean.getCoilNo());
                cycledataCoilListEntity.setVolume(coilListBean.getVolume());
                cycledataCoilListEntity.setVolume1(coilListBean.getVolume1());
                cycledataCoilListEntity.setVolume2(coilListBean.getVolume2());
                cycledataCoilListEntity.setVolume3(coilListBean.getVolume3());
                cycledataCoilListEntity.setVolume4(coilListBean.getVolume4());
                cycledataCoilListEntity.setVolume5(coilListBean.getVolume5());
                cycledataCoilListEntity.setAvSpeed(coilListBean.getAVSpeed());
                cycledataCoilListEntity.setOccupancy(coilListBean.getOccupancy());
                cycledataCoilListEntity.setHeadway(coilListBean.getHeadway());
                cycledataCoilListEntity.setGap(coilListBean.getGap());
                cycledataCoilListEntity.setSpeed85(coilListBean.getSpeed_85());


                //获取最大排队长度
                int queue = 0;
                String startTime = sdf.format(System.currentTimeMillis() - 300000);
                String endTime = sdf.format(System.currentTimeMillis());
                List<QueuedataQueueListEntity> queuedataQueueList = session.createNativeQuery("SELECT * FROM queuedata_queue_list," +
                        "queuedata WHERE queuedata.Id = queuedata_queue_list.Id AND queuedata_queue_list.LaneNo = '"
                        + cycledataCoilListEntity.getLaneNo() + "' AND queuedata.Timestamp > '" + startTime
                        + "' AND queuedata.Timestamp < '" + endTime + "' ORDER BY queuedata_queue_list.Queue_Len DESC;")
                        .addEntity(QueuedataQueueListEntity.class).list();
                if (queuedataQueueList.isEmpty()) {
                    queue = 0;
                } else {
                    queue = queuedataQueueList.get(0).getQueueLen();
                }

                ptmt.setString(1, interSectionID);
                ptmt.setInt(2, dir);
                ptmt.setString(3, cycleData.getDeviceNo());
                ptmt.setInt(4, coilListBean.getLaneNo());
                ptmt.setInt(5, coilListBean.getVolume());
                ptmt.setInt(6, coilListBean.getVolume1());
                ptmt.setInt(7, coilListBean.getVolume2());
                ptmt.setInt(8, coilListBean.getVolume3());
                ptmt.setInt(9, coilListBean.getVolume4());
                ptmt.setInt(10, coilListBean.getVolume5());
                ptmt.setDouble(11, coilListBean.getAVSpeed());
                ptmt.setDouble(12, coilListBean.getOccupancy());
                ptmt.setDouble(13, coilListBean.getHeadway());
                ptmt.setDouble(14, coilListBean.getSpeed_85());
                ptmt.setDouble(15, coilListBean.getGap());
                ptmt.setDouble(16, queue);
                java.util.Date d = sdf.parse(cycleData.getTimestamp());
                java.sql.Timestamp date = new java.sql.Timestamp(d.getTime());
                ptmt.setTimestamp(17, date);

                ptmt.addBatch();

                //保存事务
                session.save(cycledataCoilListEntity);
                session.flush();
                session.evict(cycledataCoilListEntity);
            }

            ptmt.executeBatch();

            transaction.commit();
            // 关闭Session，释放资源
            session.close();

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
