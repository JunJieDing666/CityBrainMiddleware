package com.djj.middleware.resource;

import com.djj.middleware.entities.PassdataEntity;
import com.djj.middleware.resource.POJOResource.PassData;
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

@Path("/passData")
public class PassDataResource {
    // 创建Session
    private Session session = HibernateSessionFactory.getSession();;
    // 开启事务
    private Transaction transaction = session.beginTransaction();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public int collectPassData(PassData passData) {
        //将过车数据存入数据库
        try {
            PassdataEntity passdataEntity = new PassdataEntity();
            passdataEntity.setId(UUID.randomUUID().toString());
            passdataEntity.setDeviceNo(passData.getDeviceNo());
            passdataEntity.setTimestamp(passData.getTimestamp());
            passdataEntity.setMeasNo(passData.getMeasNo());
            passdataEntity.setLaneNo(passData.getLaneNo());
            passdataEntity.setCoilNo(passData.getCoilNo());
            passdataEntity.setSpeed(passData.getSpeed());
            passdataEntity.setVehicleLen(passData.getVehicle_Len());
            passdataEntity.setVehicleType(passData.getVehicle_Type());
            passdataEntity.setPresenceTime(passData.getPresenceTime());

            // 保存
            session.save(passdataEntity);
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
