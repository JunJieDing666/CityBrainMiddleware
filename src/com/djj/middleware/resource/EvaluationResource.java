package com.djj.middleware.resource;

import com.djj.middleware.entities.EvaluationEntity;
import com.djj.middleware.entities.EvaluationListEntity;
import com.djj.middleware.resource.POJOResource.Evaluation;
import com.djj.middleware.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Path("/evaluation")
public class EvaluationResource {
    // 创建Session
    private Session session = HibernateSessionFactory.getSession();
    // 开启事务
    private Transaction transaction = session.beginTransaction();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public int collectRoadData(Evaluation evaluation) {
        try {
            //将评估数据存入数据库
            EvaluationEntity evaluationEntity = new EvaluationEntity();
            evaluationEntity.setId(UUID.randomUUID().toString());
            evaluationEntity.setDeviceNo(evaluation.getDeviceNo());
            evaluationEntity.setTimestamp(evaluation.getTimestamp());
            evaluationEntity.setLaneNum(evaluation.getLaneNum());
            // 保存
            session.save(evaluationEntity);

            EvaluationListEntity evaluationListEntity = new EvaluationListEntity();

            for (Evaluation.EvaluationListBean evaluationListBean : evaluation.getEvaluation_List()) {
                evaluationListEntity.setId(evaluationEntity.getId());
                evaluationListEntity.setLaneNo(evaluationListBean.getLaneNo());
                evaluationListEntity.setStops(evaluationListBean.getStops());
                evaluationListEntity.setDelay(evaluationListBean.getDelay());
                evaluationListEntity.setFuel(evaluationListBean.getFuel());
                evaluationListEntity.setEmissions(evaluationListBean.getEmissions());

                //保存事务
                session.save(evaluationListEntity);
                session.flush();
                session.evict(evaluationListEntity);
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
