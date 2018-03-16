package com.djj.middleware.resource;

import com.djj.middleware.entities.QueuedataEntity;
import com.djj.middleware.entities.QueuedataQueueListEntity;
import com.djj.middleware.resource.POJOResource.QueueData;
import com.djj.middleware.utils.HibernateSessionFactory;
import org.codehaus.jettison.json.JSONObject;
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

@Path("/queueData")
public class QueueDataResource {
    // 创建Session
    private Session session = HibernateSessionFactory.getSession();
    // 开启事务
    private Transaction transaction = session.beginTransaction();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public int collectQueueData(QueueData queueData) {
        //将排队数据存入数据库
        try {
            //将评估数据存入数据库
            QueuedataEntity queuedataEntity = new QueuedataEntity();
            queuedataEntity.setId(UUID.randomUUID().toString());
            queuedataEntity.setDeviceNo(queueData.getDeviceNo());
            queuedataEntity.setTimestamp(queueData.getTimestamp());
            queuedataEntity.setLaneNum(queueData.getLaneNum());
            // 保存
            session.save(queuedataEntity);

            QueuedataQueueListEntity queuedataQueueListEntity = new QueuedataQueueListEntity();

            for (QueueData.QueueListBean queueListBean : queueData.getQueue_List()) {
                queuedataQueueListEntity.setId(queuedataEntity.getId());
                queuedataQueueListEntity.setLaneNo(queueListBean.getLaneNo());
                queuedataQueueListEntity.setQueueHead(queueListBean.getQueue_Head());
                queuedataQueueListEntity.setQueueLen(queueListBean.getQueue_Len());
                queuedataQueueListEntity.setQueueNum(queueListBean.getQueue_Num());
                queuedataQueueListEntity.setQueueTail(queueListBean.getQueue_Tail());

                //保存事务
                session.save(queuedataQueueListEntity);
                session.flush();
                session.evict(queuedataQueueListEntity);
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
