package com.djj.middleware.resource.POJOResource;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CycleData {
    @JsonProperty(value = "DeviceNo")
    private String DeviceNo;

    @JsonProperty(value = "Timestamp")
    private String Timestamp;

    @JsonProperty(value = "Cycle")
    private int Cycle;

    @JsonProperty(value = "CoilNum")
    private int CoilNum;

    @JsonProperty(value = "Coil_List")
    private List<CoilListBean> Coil_List;

    public CycleData() {
    }

    public CycleData(String deviceNo, String timestamp, int cycle, int coilNum, List<CoilListBean> coil_List) {
        DeviceNo = deviceNo;
        Timestamp = timestamp;
        Cycle = cycle;
        CoilNum = coilNum;
        Coil_List = coil_List;
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

    public int getCycle() {
        return Cycle;
    }

    public void setCycle(int Cycle) {
        this.Cycle = Cycle;
    }

    public int getCoilNum() {
        return CoilNum;
    }

    public void setCoilNum(int CoilNum) {
        this.CoilNum = CoilNum;
    }

    public List<CoilListBean> getCoil_List() {
        return Coil_List;
    }

    public void setCoil_List(List<CoilListBean> Coil_List) {
        this.Coil_List = Coil_List;
    }

    public static class CoilListBean {
        @JsonProperty(value = "MeasNo")
        private int MeasNo;

        @JsonProperty(value = "LaneNo")
        private int LaneNo;

        @JsonProperty(value = "CoilNo")
        private int CoilNo;

        @JsonProperty(value = "Volume")
        private int Volume;

        @JsonProperty(value = "Volume1")
        private int Volume1;

        @JsonProperty(value = "Volume2")
        private int Volume2;

        @JsonProperty(value = "Volume3")
        private int Volume3;

        @JsonProperty(value = "Volume4")
        private int Volume4;

        @JsonProperty(value = "Volume5")
        private int Volume5;

        @JsonProperty(value = "AVSpeed")
        private float AVSpeed;

        @JsonProperty(value = "Occupancy")
        private float Occupancy;

        @JsonProperty(value = "Headway")
        private float Headway;

        @JsonProperty(value = "Gap")
        private float Gap;

        @JsonProperty(value = "Speed_85")
        private int Speed_85;

        public CoilListBean() {
        }

        public CoilListBean(int measNo, int laneNo, int coilNo, int volume, int volume1, int volume2, int volume3, int volume4, int volume5, float AVSpeed, float occupancy, float headway, float gap, int speed_85) {
            MeasNo = measNo;
            LaneNo = laneNo;
            CoilNo = coilNo;
            Volume = volume;
            Volume1 = volume1;
            Volume2 = volume2;
            Volume3 = volume3;
            Volume4 = volume4;
            Volume5 = volume5;
            this.AVSpeed = AVSpeed;
            Occupancy = occupancy;
            Headway = headway;
            Gap = gap;
            Speed_85 = speed_85;
        }

        public int getMeasNo() {
            return MeasNo;
        }

        public void setMeasNo(int MeasNo) {
            this.MeasNo = MeasNo;
        }

        public int getLaneNo() {
            return LaneNo;
        }

        public void setLaneNo(int LaneNo) {
            this.LaneNo = LaneNo;
        }

        public int getCoilNo() {
            return CoilNo;
        }

        public void setCoilNo(int CoilNo) {
            this.CoilNo = CoilNo;
        }

        public int getVolume() {
            return Volume;
        }

        public void setVolume(int Volume) {
            this.Volume = Volume;
        }

        public int getVolume1() {
            return Volume1;
        }

        public void setVolume1(int Volume1) {
            this.Volume1 = Volume1;
        }

        public int getVolume2() {
            return Volume2;
        }

        public void setVolume2(int Volume2) {
            this.Volume2 = Volume2;
        }

        public int getVolume3() {
            return Volume3;
        }

        public void setVolume3(int Volume3) {
            this.Volume3 = Volume3;
        }

        public int getVolume4() {
            return Volume4;
        }

        public void setVolume4(int Volume4) {
            this.Volume4 = Volume4;
        }

        public int getVolume5() {
            return Volume5;
        }

        public void setVolume5(int Volume5) {
            this.Volume5 = Volume5;
        }

        public float getAVSpeed() {
            return AVSpeed;
        }

        public void setAVSpeed(float AVSpeed) {
            this.AVSpeed = AVSpeed;
        }

        public float getOccupancy() {
            return Occupancy;
        }

        public void setOccupancy(float Occupancy) {
            this.Occupancy = Occupancy;
        }

        public float getHeadway() {
            return Headway;
        }

        public void setHeadway(float Headway) {
            this.Headway = Headway;
        }

        public float getGap() {
            return Gap;
        }

        public void setGap(float Gap) {
            this.Gap = Gap;
        }

        public int getSpeed_85() {
            return Speed_85;
        }

        public void setSpeed_85(int Speed_85) {
            this.Speed_85 = Speed_85;
        }
    }
}
