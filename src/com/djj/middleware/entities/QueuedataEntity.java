package com.djj.middleware.entities;

import javax.persistence.*;

@Entity
@Table(name = "queuedata", schema = "citybrainmiddleware", catalog = "")
public class QueuedataEntity {
    private String id;
    private String deviceNo;
    private String timestamp;
    private int laneNum;

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
    @Column(name = "LaneNum")
    public int getLaneNum() {
        return laneNum;
    }

    public void setLaneNum(int laneNum) {
        this.laneNum = laneNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QueuedataEntity that = (QueuedataEntity) o;

        if (laneNum != that.laneNum) return false;
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
        result = 31 * result + laneNum;
        return result;
    }
}
