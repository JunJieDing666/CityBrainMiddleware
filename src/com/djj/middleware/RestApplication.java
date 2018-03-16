package com.djj.middleware;

import com.djj.middleware.entities.DeviceEntity;
import com.djj.middleware.entities.IntersectionEntity;
import com.djj.middleware.entities.LaneEntity;
import com.djj.middleware.utils.DBUtil;
import com.djj.middleware.utils.HibernateSessionFactory;
import com.djj.middleware.utils.RadarUtil;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

//入口类
/*
 * @author   丁俊杰
 * @name     程序入口类
 * @time     2018/2/9
 * */
public class RestApplication extends ResourceConfig {
    public RestApplication() {
        //建立restful服务
        packages("com.djj.middleware.resource");
        register(JacksonJsonProvider.class);

        //开启实时数据接收
        insertRealtimeData();


    }

    private String insertRealtimeData() {
        System.out.println("程序开始");
        //1.创建Session
        Session session = HibernateSessionFactory.getSession();

        final Connection conn = DBUtil.getConnection();
        final Statement stmt = null;

        //2.为所有雷达建立tcp连接并请求一帧数据
        //找到所有路口
        List<IntersectionEntity> interSectionList = session.createNativeQuery("select * from intersection"
        ).addEntity(IntersectionEntity.class).list();
        //容错
        if (interSectionList.isEmpty()) {
            return "查无路口";
        }

        for (final IntersectionEntity i : interSectionList) {
            //找到路口的所有雷达
            List<DeviceEntity> deviceList = session.createNativeQuery("select * from device where InterSectionID = '"
                    + i.getInterSectionId() + "'").addEntity(DeviceEntity.class).list();
            //路口无设备，跳过
            if (deviceList.isEmpty()) {
                break;
            }

            for (final DeviceEntity d : deviceList) {
                //与所有雷达建立tcp连接，解析雷达数据获得所有目标，每隔50ms获取一次并更新数据库
                final List<LaneEntity> lanes = session.createNativeQuery("select * from lane where DeviecNo = '"
                        + d.getDeviceNo() + "'").addEntity(LaneEntity.class).list();
                //路口没记录车道，跳过
                if (lanes.isEmpty()) {
                    break;
                }
                //开启匿名线程处理每个雷达
                Thread thread = new Thread() {
                    public void run() {
                        //与所有雷达建立长连接
                        RadarUtil.getRealTimeData(i.getInterSectionId(), d.getDir(), d.getDeviceNo(),
                                lanes, d.getDeviceIp(), d.getDevicePort(), conn, stmt);
                    }
                };
                thread.start();

            }


        }

        session.close();
        return "开启成功";
    }
}
