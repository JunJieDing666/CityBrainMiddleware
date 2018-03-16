package com.djj.middleware.resource.POJOResource;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoadData {
    @JsonProperty(value = "DeviceNo")
    private String DeviceNo;

    @JsonProperty(value = "Timestamp")
    private String Timestamp;

    @JsonProperty(value = "LaneNum")
    private int LaneNum;

    @JsonProperty(value = "RoadStatus_List")
    private List<RoadStatusListBean> RoadStatus_List;

    public RoadData() {
    }

    public RoadData(String deviceNo, String timestamp, int laneNum, List<RoadStatusListBean> roadStatus_List) {
        DeviceNo = deviceNo;
        Timestamp = timestamp;
        LaneNum = laneNum;
        RoadStatus_List = roadStatus_List;
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

    public List<RoadStatusListBean> getRoadStatus_List() {
        return RoadStatus_List;
    }

    public void setRoadStatus_List(List<RoadStatusListBean> RoadStatus_List) {
        this.RoadStatus_List = RoadStatus_List;
    }

    public static class RoadStatusListBean {
        @JsonProperty(value = "LaneNo")
        private int LaneNo;

        @JsonProperty(value = "Vehicle_Num")
        private int Vehicle_Num;

        @JsonProperty(value = "Occupancy")
        private float Occupancy;

        @JsonProperty(value = "AVSpeed")
        private float AVSpeed;

        @JsonProperty(value = "Pareto")
        private float Pareto;

        @JsonProperty(value = "Head_Pos")
        private int Head_Pos;

        @JsonProperty(value = "Head_Speed")
        private int Head_Speed;

        @JsonProperty(value = "Last_Pos")
        private int Last_Pos;

        @JsonProperty(value = "Last_Speed")
        private int Last_Speed;

        public RoadStatusListBean() {
        }

        public RoadStatusListBean(int laneNo, int vehicle_Num, float occupancy, float AVSpeed, float pareto, int head_Pos, int head_Speed, int last_Pos, int last_Speed) {
            LaneNo = laneNo;
            Vehicle_Num = vehicle_Num;
            Occupancy = occupancy;
            this.AVSpeed = AVSpeed;
            Pareto = pareto;
            Head_Pos = head_Pos;
            Head_Speed = head_Speed;
            Last_Pos = last_Pos;
            Last_Speed = last_Speed;
        }

        public int getLaneNo() {
            return LaneNo;
        }

        public void setLaneNo(int LaneNo) {
            this.LaneNo = LaneNo;
        }

        public int getVehicle_Num() {
            return Vehicle_Num;
        }

        public void setVehicle_Num(int Vehicle_Num) {
            this.Vehicle_Num = Vehicle_Num;
        }

        public float getOccupancy() {
            return Occupancy;
        }

        public void setOccupancy(int Occupancy) {
            this.Occupancy = Occupancy;
        }

        public float getAVSpeed() {
            return AVSpeed;
        }

        public void setAVSpeed(int AVSpeed) {
            this.AVSpeed = AVSpeed;
        }

        public float getPareto() {
            return Pareto;
        }

        public void setPareto(int Pareto) {
            this.Pareto = Pareto;
        }

        public int getHead_Pos() {
            return Head_Pos;
        }

        public void setHead_Pos(int Head_Pos) {
            this.Head_Pos = Head_Pos;
        }

        public int getHead_Speed() {
            return Head_Speed;
        }

        public void setHead_Speed(int Head_Speed) {
            this.Head_Speed = Head_Speed;
        }

        public int getLast_Pos() {
            return Last_Pos;
        }

        public void setLast_Pos(int Last_Pos) {
            this.Last_Pos = Last_Pos;
        }

        public int getLast_Speed() {
            return Last_Speed;
        }

        public void setLast_Speed(int Last_Speed) {
            this.Last_Speed = Last_Speed;
        }
    }
}
