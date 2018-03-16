package com.djj.middleware.resource.POJOResource;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PassData {
    @JsonProperty(value = "DeviceNo")
    private String DeviceNo;

    @JsonProperty(value = "Timestamp")
    private String Timestamp;

    @JsonProperty(value = "MeasNo")
    private int MeasNo;

    @JsonProperty(value = "CoilNo")
    private int CoilNo;

    @JsonProperty(value = "LaneNo")
    private int LaneNo;

    @JsonProperty(value = "Speed")
    private float Speed;

    @JsonProperty(value = "Vehicle_Len")
    private float Vehicle_Len;

    @JsonProperty(value = "Vehicle_Type")
    private int Vehicle_Type;

    @JsonProperty(value = "PresenceTime")
    private int PresenceTime;

    public PassData() {
    }

    public PassData(String deviceNo, String timestamp, int measNo, int coilNo, int laneNo, float speed, float vehicle_Len, int vehicle_Type, int presenceTime) {
        DeviceNo = deviceNo;
        Timestamp = timestamp;
        MeasNo = measNo;
        CoilNo = coilNo;
        LaneNo = laneNo;
        Speed = speed;
        Vehicle_Len = vehicle_Len;
        Vehicle_Type = vehicle_Type;
        PresenceTime = presenceTime;
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

    public int getMeasNo() {
        return MeasNo;
    }

    public void setMeasNo(int MeasNo) {
        this.MeasNo = MeasNo;
    }

    public int getCoilNo() {
        return CoilNo;
    }

    public void setCoilNo(int CoilNo) {
        this.CoilNo = CoilNo;
    }

    public int getLaneNo() {
        return LaneNo;
    }

    public void setLaneNo(int LaneNo) {
        this.LaneNo = LaneNo;
    }

    public float getSpeed() {
        return Speed;
    }

    public void setSpeed(float Speed) {
        this.Speed = Speed;
    }

    public float getVehicle_Len() {
        return Vehicle_Len;
    }

    public void setVehicle_Len(float Vehicle_Len) {
        this.Vehicle_Len = Vehicle_Len;
    }

    public int getVehicle_Type() {
        return Vehicle_Type;
    }

    public void setVehicle_Type(int Vehicle_Type) {
        this.Vehicle_Type = Vehicle_Type;
    }

    public int getPresenceTime() {
        return PresenceTime;
    }

    public void setPresenceTime(int PresenceTime) {
        this.PresenceTime = PresenceTime;
    }
}
