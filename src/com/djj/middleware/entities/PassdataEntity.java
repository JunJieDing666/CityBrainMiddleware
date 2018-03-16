package com.djj.middleware.entities;

import javax.persistence.*;

@Entity
@Table(name = "passdata", schema = "citybrainmiddleware", catalog = "")
public class PassdataEntity {
    private String id;
    private String deviceNo;
    private String timestamp;
    private int measNo;
    private int laneNo;
    private int coilNo;
    private float speed;
    private float vehicleLen;
    private int vehicleType;
    private int presenceTime;

    @Id
    @Column(name = "Id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DeviceNo")
    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    @Basic
    @Column(name = "Timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Basic
    @Column(name = "MeasNo")
    public int getMeasNo() {
        return measNo;
    }

    public void setMeasNo(int measNo) {
        this.measNo = measNo;
    }

    @Basic
    @Column(name = "LaneNo")
    public int getLaneNo() {
        return laneNo;
    }

    public void setLaneNo(int laneNo) {
        this.laneNo = laneNo;
    }

    @Basic
    @Column(name = "CoilNo")
    public int getCoilNo() {
        return coilNo;
    }

    public void setCoilNo(int coilNo) {
        this.coilNo = coilNo;
    }

    @Basic
    @Column(name = "Speed")
    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Basic
    @Column(name = "Vehicle_Len")
    public float getVehicleLen() {
        return vehicleLen;
    }

    public void setVehicleLen(float vehicleLen) {
        this.vehicleLen = vehicleLen;
    }

    @Basic
    @Column(name = "Vehicle_Type")
    public int getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(int vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Basic
    @Column(name = "PresenceTime")
    public int getPresenceTime() {
        return presenceTime;
    }

    public void setPresenceTime(int presenceTime) {
        this.presenceTime = presenceTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PassdataEntity that = (PassdataEntity) o;

        if (measNo != that.measNo) return false;
        if (laneNo != that.laneNo) return false;
        if (coilNo != that.coilNo) return false;
        if (Float.compare(that.speed, speed) != 0) return false;
        if (Float.compare(that.vehicleLen, vehicleLen) != 0) return false;
        if (vehicleType != that.vehicleType) return false;
        if (presenceTime != that.presenceTime) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (deviceNo != null ? !deviceNo.equals(that.deviceNo) : that.deviceNo != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (deviceNo != null ? deviceNo.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + measNo;
        result = 31 * result + laneNo;
        result = 31 * result + coilNo;
        result = 31 * result + (speed != +0.0f ? Float.floatToIntBits(speed) : 0);
        result = 31 * result + (vehicleLen != +0.0f ? Float.floatToIntBits(vehicleLen) : 0);
        result = 31 * result + vehicleType;
        result = 31 * result + presenceTime;
        return result;
    }
}
