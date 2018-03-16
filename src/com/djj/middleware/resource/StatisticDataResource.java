package com.djj.middleware.resource;

import com.djj.middleware.entities.CycledataCoilListEntity;
import com.djj.middleware.entities.DeviceEntity;
import com.djj.middleware.entities.LaneEntity;
import com.djj.middleware.entities.QueuedataQueueListEntity;
import com.djj.middleware.resource.POJOResource.InterSection;
import com.djj.middleware.resource.POJOResource.StatisticData;
import com.djj.middleware.utils.HibernateSessionFactory;
import org.hibernate.Session;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("statistic")
public class StatisticDataResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public StatisticData getStatisticData() {
        StatisticData statisticData = new StatisticData();
        return statisticData;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public StatisticData getStatisticData(InterSection interSection) {
        String interSectionID = interSection.getInterSectionID();
        String startTime = interSection.getStart();
        String endTime = interSection.getEnd();
        System.out.println(interSectionID + "--" + startTime + "--" + endTime);


        //将时间转为统一形式
        Date dateStart = new Date();
        Date dateEnd = new Date();
        //注意format的格式要与日期String的格式相匹配
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dateStart = sdf.parse(startTime);
            dateEnd = sdf.parse(endTime);
            startTime = sdf.format(dateStart);
            endTime = sdf.format(dateEnd);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //1.根据路口号获得所有雷达
        // 创建Session
        Session session = HibernateSessionFactory.getSession();
        List<DeviceEntity> deviceList = session.createNativeQuery("select * from device where InterSectionID = "
                + interSectionID).addEntity(DeviceEntity.class).list();

        if (deviceList.isEmpty()) {
            StatisticData errorStatistic = new StatisticData();
            errorStatistic.setInterSectionID("查无此路口");
            return errorStatistic;
        }

        //一级信息
        StatisticData statisticData = new StatisticData();
        statisticData.setInterSectionID(interSectionID);
        statisticData.setDirNum((Integer) session.createNativeQuery("select DirNum from intersection where InterSectionID = "
                + interSectionID).list().get(0));
        ArrayList<StatisticData.DirListBean> dirList = new ArrayList<StatisticData.DirListBean>();
        statisticData.setDirList(dirList);

        for (DeviceEntity d : deviceList) {
            System.out.println(d.getDeviceNo());

            //二级信息
            StatisticData.DirListBean dirListBean = new StatisticData.DirListBean();
            dirList.add(dirListBean);
            dirListBean.setRadarNo(d.getDeviceNo());
            List<LaneEntity> lanes = session.createNativeQuery("select * from lane where DeviecNo = "
                    + d.getDeviceNo()).addEntity(LaneEntity.class).list();
            dirListBean.setLaneNum(lanes.size());
            ArrayList<StatisticData.DirListBean.LaneListBean> laneList = new ArrayList<StatisticData.DirListBean.LaneListBean>();
            dirListBean.setLaneList(laneList);

            //2.为该雷达从数据库中找到指定时间内的所有数据计算平均值
            List<CycledataCoilListEntity> cycledataCoilList = session.createNativeQuery("select * from cycledata_coil_list,cycledata where " +
                    "cycledata.Id = cycledata_coil_list.Id and cycledata.DeviceNo = '" + d.getDeviceNo()
                    + "' and cycledata.Timestamp > '" + startTime + "' AND cycledata.Timestamp < '" + endTime + "'").addEntity(CycledataCoilListEntity.class).list();

            for (LaneEntity l : lanes) {
                //三级信息
                StatisticData.DirListBean.LaneListBean laneListBean = new StatisticData.DirListBean.LaneListBean();
                laneList.add(laneListBean);

                laneListBean.setLaneID(l.getLaneId());

                ArrayList<CycledataCoilListEntity> cycledataCoilListEntities = new ArrayList<CycledataCoilListEntity>();
                //找出属于该车道的数据，存入list
                for (CycledataCoilListEntity c : cycledataCoilList) {
                    if (c.getLaneNo() == l.getLaneId()) {
                        cycledataCoilListEntities.add(c);
                    }
                }

                int volume = 0, volume1 = 0, volume2 = 0, volume3 = 0, volume4 = 0, volume5 = 0, cnt = 0, cnt85 = 0;
                float speed = 0, occupation = 0, headway = 0, speed85 = 0, gap = 0, queue;
                //对数据计算统计值
                for (CycledataCoilListEntity c : cycledataCoilListEntities) {
                    cnt++;
                    volume += c.getVolume();
                    volume1 += c.getVolume1();
                    volume2 += c.getVolume2();
                    volume3 += c.getVolume3();
                    volume4 += c.getVolume4();
                    volume5 += c.getVolume5();
                    speed += c.getAvSpeed() * c.getVolume();
                    occupation += c.getOccupancy();
                    headway = c.getHeadway() * c.getVolume();
                    gap = c.getGap() * c.getVolume();
                    if (c.getSpeed85() != 0) {
                        cnt85++;
                        speed85 += speed85;
                    }
                }
                if (volume != 0) {
                    speed = speed / volume;
                    headway = headway / volume;
                    gap = gap / volume;
                }

                if (cnt != 0) {
                    occupation = occupation / cnt;
                }

                if (cnt85 != 0) {
                    speed85 = speed85 / cnt85;
                }

                List<QueuedataQueueListEntity> queuedataQueueList = session.createNativeQuery("SELECT * FROM queuedata_queue_list,queuedata WHERE queuedata.Id = queuedata_queue_list.Id AND queuedata_queue_list.LaneNo = '" + l.getLaneId() + "' \n" +
                        "AND queuedata.Timestamp > '" + startTime + "' AND queuedata.Timestamp < '" + endTime + "' ORDER BY queuedata_queue_list.Queue_Len DESC;").addEntity(QueuedataQueueListEntity.class).list();
                if (queuedataQueueList.isEmpty()) {
                    queue = 0;
                } else {
                    queue = queuedataQueueList.get(0).getQueueLen();
                }

                laneListBean.setVolume(volume);
                laneListBean.setVolume1(volume1);
                laneListBean.setVolume2(volume2);
                laneListBean.setVolume3(volume3);
                laneListBean.setVolume4(volume4);
                laneListBean.setVolume5(volume5);
                laneListBean.setSpeed(speed);
                laneListBean.setOccupation(occupation);
                laneListBean.setHeadway(headway);
                laneListBean.setGap(gap);
                laneListBean.setSpeed_85(speed85);
                laneListBean.setQueue(queue);
            }

        }

        session.close();

        //4.组装数据返回json
        return statisticData;

    }
}
