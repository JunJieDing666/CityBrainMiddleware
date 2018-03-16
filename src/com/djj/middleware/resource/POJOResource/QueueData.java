package com.djj.middleware.resource.POJOResource;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueueData {
    @JsonProperty(value = "DeviceNo")
    private String DeviceNo;

    @JsonProperty(value = "Timestamp")
    private String Timestamp;

    @JsonProperty(value = "LaneNum")
    private int LaneNum;

    @JsonProperty(value = "Queue_List")
    private List<QueueListBean> Queue_List;

    public QueueData() {
    }

    public QueueData(String deviceNo, String timestamp, int laneNum, List<QueueListBean> queue_List) {
        DeviceNo = deviceNo;
        Timestamp = timestamp;
        LaneNum = laneNum;
        Queue_List = queue_List;
    }

    public String getDeviceNo() {
        return DeviceNo;
    }

    public void setDeviceNo(String DeviceNo) {
        this.DeviceNo = DeviceNo;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String Timestamp) {
        this.Timestamp = Timestamp;
    }

    public int getLaneNum() {
        return LaneNum;
    }

    public void setLaneNum(int LaneNum) {
        this.LaneNum = LaneNum;
    }

    public List<QueueListBean> getQueue_List() {
        return Queue_List;
    }

    public void setQueue_List(List<QueueListBean> Queue_List) {
        this.Queue_List = Queue_List;
    }

    public static class QueueListBean {
        @JsonProperty(value = "LaneNo")
        private int LaneNo;

        @JsonProperty(value = "Queue_Len")
        private int Queue_Len;

        @JsonProperty(value = "Queue_Head")
        private int Queue_Head;

        @JsonProperty(value = "Queue_Tail")
        private int Queue_Tail;

        @JsonProperty(value = "Queue_Num")
        private int Queue_Num;

        public QueueListBean() {
        }

        public QueueListBean(int laneNo, int queue_Len, int queue_Head, int queue_Tail, int queue_Num) {
            LaneNo = laneNo;
            Queue_Len = queue_Len;
            Queue_Head = queue_Head;
            Queue_Tail = queue_Tail;
            Queue_Num = queue_Num;
        }

        public int getLaneNo() {
            return LaneNo;
        }

        public void setLaneNo(int LaneNo) {
            this.LaneNo = LaneNo;
        }

        public int getQueue_Len() {
            return Queue_Len;
        }

        public void setQueue_Len(int Queue_Len) {
            this.Queue_Len = Queue_Len;
        }

        public int getQueue_Head() {
            return Queue_Head;
        }

        public void setQueue_Head(int Queue_Head) {
            this.Queue_Head = Queue_Head;
        }

        public int getQueue_Tail() {
            return Queue_Tail;
        }

        public void setQueue_Tail(int Queue_Tail) {
            this.Queue_Tail = Queue_Tail;
        }

        public int getQueue_Num() {
            return Queue_Num;
        }

        public void setQueue_Num(int Queue_Num) {
            this.Queue_Num = Queue_Num;
        }
    }
}
