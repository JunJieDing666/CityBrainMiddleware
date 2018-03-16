package com.djj.middleware.resource.POJOResource;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Evaluation {
    @JsonProperty(value = "DeviceNo")
    private String DeviceNo;

    @JsonProperty(value = "Timestamp")
    private String Timestamp;

    @JsonProperty(value = "LaneNum")
    private int LaneNum;

    @JsonProperty(value = "Evaluation_List")
    private List<EvaluationListBean> Evaluation_List;

    public Evaluation() {
    }

    public Evaluation(String deviceNo, String timestamp, int laneNum, List<EvaluationListBean> evaluation_List) {
        DeviceNo = deviceNo;
        Timestamp = timestamp;
        LaneNum = laneNum;
        Evaluation_List = evaluation_List;
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

    public List<EvaluationListBean> getEvaluation_List() {
        return Evaluation_List;
    }

    public void setEvaluation_List(List<EvaluationListBean> Evaluation_List) {
        this.Evaluation_List = Evaluation_List;
    }

    public static class EvaluationListBean {
        @JsonProperty(value = "LaneNo")
        private int LaneNo;

        @JsonProperty(value = "Stops")
        private float Stops;

        @JsonProperty(value = "Delay")
        private float Delay;

        @JsonProperty(value = "Fuel")
        private float Fuel;

        @JsonProperty(value = "Emissions")
        private float Emissions;

        public EvaluationListBean() {
        }

        public EvaluationListBean(int laneNo, float stops, float delay, float fuel, float emissions) {
            LaneNo = laneNo;
            Stops = stops;
            Delay = delay;
            Fuel = fuel;
            Emissions = emissions;
        }

        public int getLaneNo() {
            return LaneNo;
        }

        public void setLaneNo(int LaneNo) {
            this.LaneNo = LaneNo;
        }

        public float getStops() {
            return Stops;
        }

        public void setStops(float Stops) {
            this.Stops = Stops;
        }

        public float getDelay() {
            return Delay;
        }

        public void setDelay(float Delay) {
            this.Delay = Delay;
        }

        public float getFuel() {
            return Fuel;
        }

        public void setFuel(float Fuel) {
            this.Fuel = Fuel;
        }

        public float getEmissions() {
            return Emissions;
        }

        public void setEmissions(float Emissions) {
            this.Emissions = Emissions;
        }
    }
}
