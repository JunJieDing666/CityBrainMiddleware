package com.djj.middleware.entities;

import javax.persistence.*;

@Entity
@Table(name = "cycledata_coil_list", schema = "citybrainmiddleware", catalog = "")
public class CycledataCoilListEntity {
    private int cycledataId;
    private String id;
    private int measNo;
    private int laneNo;
    private int coilNo;
    private int volume;
    private int volume1;
    private int volume2;
    private int volume3;
    private int volume4;
    private int volume5;
    private float avSpeed;
    private float occupancy;
    private float headway;
    private float gap;
    private float speed85;

    @Id
    @Column(name = "Cycledata_Id")
    public int getCycledataId() {
        return cycledataId;
    }

    public void setCycledataId(int cycledataId) {
        this.cycledataId = cycledataId;
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
    @Column(name = "Volume")
    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Basic
    @Column(name = "Volume1")
    public int getVolume1() {
        return volume1;
    }

    public void setVolume1(int volume1) {
        this.volume1 = volume1;
    }

    @Basic
    @Column(name = "Volume2")
    public int getVolume2() {
        return volume2;
    }

    public void setVolume2(int volume2) {
        this.volume2 = volume2;
    }

    @Basic
    @Column(name = "Volume3")
    public int getVolume3() {
        return volume3;
    }

    public void setVolume3(int volume3) {
        this.volume3 = volume3;
    }

    @Basic
    @Column(name = "Volume4")
    public int getVolume4() {
        return volume4;
    }

    public void setVolume4(int volume4) {
        this.volume4 = volume4;
    }

    @Basic
    @Column(name = "Volume5")
    public int getVolume5() {
        return volume5;
    }

    public void setVolume5(int volume5) {
        this.volume5 = volume5;
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
    @Column(name = "Occupancy")
    public float getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(float occupancy) {
        this.occupancy = occupancy;
    }

    @Basic
    @Column(name = "Headway")
    public float getHeadway() {
        return headway;
    }

    public void setHeadway(float headway) {
        this.headway = headway;
    }

    @Basic
    @Column(name = "Gap")
    public float getGap() {
        return gap;
    }

    public void setGap(float gap) {
        this.gap = gap;
    }

    @Basic
    @Column(name = "Speed_85")
    public float getSpeed85() {
        return speed85;
    }

    public void setSpeed85(float speed85) {
        this.speed85 = speed85;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CycledataCoilListEntity that = (CycledataCoilListEntity) o;

        if (cycledataId != that.cycledataId) return false;
        if (measNo != that.measNo) return false;
        if (laneNo != that.laneNo) return false;
        if (coilNo != that.coilNo) return false;
        if (volume != that.volume) return false;
        if (volume1 != that.volume1) return false;
        if (volume2 != that.volume2) return false;
        if (volume3 != that.volume3) return false;
        if (volume4 != that.volume4) return false;
        if (volume5 != that.volume5) return false;
        if (Float.compare(that.avSpeed, avSpeed) != 0) return false;
        if (Float.compare(that.occupancy, occupancy) != 0) return false;
        if (Float.compare(that.headway, headway) != 0) return false;
        if (Float.compare(that.gap, gap) != 0) return false;
        if (Float.compare(that.speed85, speed85) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cycledataId;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + measNo;
        result = 31 * result + laneNo;
        result = 31 * result + coilNo;
        result = 31 * result + volume;
        result = 31 * result + volume1;
        result = 31 * result + volume2;
        result = 31 * result + volume3;
        result = 31 * result + volume4;
        result = 31 * result + volume5;
        result = 31 * result + (avSpeed != +0.0f ? Float.floatToIntBits(avSpeed) : 0);
        result = 31 * result + (occupancy != +0.0f ? Float.floatToIntBits(occupancy) : 0);
        result = 31 * result + (headway != +0.0f ? Float.floatToIntBits(headway) : 0);
        result = 31 * result + (gap != +0.0f ? Float.floatToIntBits(gap) : 0);
        result = 31 * result + (speed85 != +0.0f ? Float.floatToIntBits(speed85) : 0);
        return result;
    }
}
