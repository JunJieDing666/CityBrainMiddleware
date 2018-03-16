package com.djj.middleware.entities;

import javax.persistence.*;

@Entity
@Table(name = "cycledata", schema = "citybrainmiddleware", catalog = "")
public class CycledataEntity {
    private String id;
    private String deviceNo;
    private String timestamp;
    private int cycle;
    private int coilNum;

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
    @Column(name = "Cycle")
    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    @Basic
    @Column(name = "CoilNum")
    public int getCoilNum() {
        return coilNum;
    }

    public void setCoilNum(int coilNum) {
        this.coilNum = coilNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CycledataEntity that = (CycledataEntity) o;

        if (cycle != that.cycle) return false;
        if (coilNum != that.coilNum) return false;
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
        result = 31 * result + cycle;
        result = 31 * result + coilNum;
        return result;
    }
}
