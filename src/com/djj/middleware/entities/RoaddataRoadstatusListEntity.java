package com.djj.middleware.entities;

import javax.persistence.*;

@Entity
@Table(name = "roaddata_roadstatus_list", schema = "citybrainmiddleware", catalog = "")
public class RoaddataRoadstatusListEntity {
    private int roaddataId;
    private String id;
    private int laneNo;
    private int vehicleNum;
    private float occupancy;
    private float avSpeed;
    private float pareto;
    private int headPos;
    private int headSpeed;
    private int lastPos;
    private int lastSpeed;

    @Id
    @Column(name = "Roaddata_Id")
    public int getRoaddataId() {
        return roaddataId;
    }

    public void setRoaddataId(int roaddataId) {
        this.roaddataId = roaddataId;
    }

    @Basic
    @Column(name = "Id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Column(name = "Vehicle_Num")
    public int getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(int vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    @Basic
    @Column(name = "Occupancy")
    public float getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(float occupancy) {
        this.occupancy = occupancy;
    }

    @Basic
    @Column(name = "AVSpeed")
    public float getAvSpeed() {
        return avSpeed;
    }

    public void setAvSpeed(float avSpeed) {
        this.avSpeed = avSpeed;
    }

    @Basic
    @Column(name = "Pareto")
    public float getPareto() {
        return pareto;
    }

    public void setPareto(float pareto) {
        this.pareto = pareto;
    }

    @Basic
    @Column(name = "Head_Pos")
    public int getHeadPos() {
        return headPos;
    }

    public void setHeadPos(int headPos) {
        this.headPos = headPos;
    }

    @Basic
    @Column(name = "Head_Speed")
    public int getHeadSpeed() {
        return headSpeed;
    }

    public void setHeadSpeed(int headSpeed) {
        this.headSpeed = headSpeed;
    }

    @Basic
    @Column(name = "Last_Pos")
    public int getLastPos() {
        return lastPos;
    }

    public void setLastPos(int lastPos) {
        this.lastPos = lastPos;
    }

    @Basic
    @Column(name = "Last_Speed")
    public int getLastSpeed() {
        return lastSpeed;
    }

    public void setLastSpeed(int lastSpeed) {
        this.lastSpeed = lastSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoaddataRoadstatusListEntity that = (RoaddataRoadstatusListEntity) o;

        if (roaddataId != that.roaddataId) return false;
        if (laneNo != that.laneNo) return false;
        if (vehicleNum != that.vehicleNum) return false;
        if (Float.compare(that.occupancy, occupancy) != 0) return false;
        if (Float.compare(that.avSpeed, avSpeed) != 0) return false;
        if (Float.compare(that.pareto, pareto) != 0) return false;
        if (headPos != that.headPos) return false;
        if (headSpeed != that.headSpeed) return false;
        if (lastPos != that.lastPos) return false;
        if (lastSpeed != that.lastSpeed) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roaddataId;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + laneNo;
        result = 31 * result + vehicleNum;
        result = 31 * result + (occupancy != +0.0f ? Float.floatToIntBits(occupancy) : 0);
        result = 31 * result + (avSpeed != +0.0f ? Float.floatToIntBits(avSpeed) : 0);
        result = 31 * result + (pareto != +0.0f ? Float.floatToIntBits(pareto) : 0);
        result = 31 * result + headPos;
        result = 31 * result + headSpeed;
        result = 31 * result + lastPos;
        result = 31 * result + lastSpeed;
        return result;
    }
}
