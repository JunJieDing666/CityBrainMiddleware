package com.djj.middleware.entities;

import javax.persistence.*;

@Entity
@Table(name = "device", schema = "citybrainmiddleware", catalog = "")
public class DeviceEntity {
    private int id;
    private String deviceNo;
    private String interSectionId;
    private String deviceIp;
    private int devicePort;
    private short dir;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    @Column(name = "InterSectionID")
    public String getInterSectionId() {
        return interSectionId;
    }

    public void setInterSectionId(String interSectionId) {
        this.interSectionId = interSectionId;
    }

    @Basic
    @Column(name = "DeviceIP")
    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    @Basic
    @Column(name = "DevicePort")
    public int getDevicePort() {
        return devicePort;
    }

    public void setDevicePort(int devicePort) {
        this.devicePort = devicePort;
    }

    @Basic
    @Column(name = "Dir")
    public short getDir() {
        return dir;
    }

    public void setDir(short dir) {
        this.dir = dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceEntity that = (DeviceEntity) o;

        if (id != that.id) return false;
        if (devicePort != that.devicePort) return false;
        if (dir != that.dir) return false;
        if (deviceNo != null ? !deviceNo.equals(that.deviceNo) : that.deviceNo != null) return false;
        if (interSectionId != null ? !interSectionId.equals(that.interSectionId) : that.interSectionId != null)
            return false;
        if (deviceIp != null ? !deviceIp.equals(that.deviceIp) : that.deviceIp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (deviceNo != null ? deviceNo.hashCode() : 0);
        result = 31 * result + (interSectionId != null ? interSectionId.hashCode() : 0);
        result = 31 * result + (deviceIp != null ? deviceIp.hashCode() : 0);
        result = 31 * result + devicePort;
        result = 31 * result + (int) dir;
        return result;
    }
}
