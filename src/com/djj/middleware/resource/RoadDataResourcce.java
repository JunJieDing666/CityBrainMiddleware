package com.djj.middleware.resource;

import com.djj.middleware.entities.RoaddataEntity;
import com.djj.middleware.entities.RoaddataRoadstatusListEntity;
import com.djj.middleware.resource.POJOResource.RoadData;
import com.djj.middleware.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("/roadData")
public class RoadDataResourcce {
    // 创建Session
    private Session session = HibernateSessionFactory.getSession();
    // 开启事务
    private Transaction transaction = session.beginTransaction();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public int collectRoadData(RoadData roadData) {
        //将路况数据存入数据库
        try {
            //将评估数据存入数据库
            RoaddataEntity roaddataEntity = new RoaddataEntity();
            roaddataEntity.setId(UUID.randomUUID().toString());
            roaddataEntity.setDeviceNo(roadData.getDeviceNo());
            roaddataEntity.setTimestamp(roadData.getTimestamp());
            roaddataEntity.setLaneNum(roadData.getLaneNum());
            // 保存
            session.save(roaddataEntity);


            RoaddataRoadstatusListEntity roaddataRoadstatusListEntity = new RoaddataRoadstatusListEntity();

            for (RoadData.RoadStatusListBean roadStatusListBean : roadData.getRoadStatus_List()) {
                roaddataRoadstatusListEntity.setId(roaddataEntity.getId());
                roaddataRoadstatusListEntity.setLaneNo(roadStatusListBean.getLaneNo());
                roaddataRoadstatusListEntity.setVehicleNum(roadStatusListBean.getVehicle_Num());
                roaddataRoadstatusListEntity.setOccupancy(roadStatusListBean.getOccupancy());
                roaddataRoadstatusListEntity.setAvSpeed(roadStatusListBean.getAVSpeed());
                roaddataRoadstatusListEntity.setPareto(roadStatusListBean.getPareto());
                roaddataRoadstatusListEntity.setHeadPos(roadStatusListBean.getHead_Pos());
                roaddataRoadstatusListEntity.setHeadSpeed(roadStatusListBean.getHead_Speed());
                roaddataRoadstatusListEntity.setLastPos(roadStatusListBean.getLast_Pos());
                roaddataRoadstatusListEntity.setLastSpeed(roadStatusListBean.getLast_Speed());

                //保存事务
                session.save(roaddataRoadstatusListEntity);
                session.flush();
                session.evict(roaddataRoadstatusListEntity);

            }

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
