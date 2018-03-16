package com.djj.middleware.entities;

import javax.persistence.*;

@Entity
@Table(name = "lane", schema = "citybrainmiddleware", catalog = "")
public class LaneEntity {
    private int id;
    private String deviecNo;
    private int laneId;
    private float pointX;
    private float pointY;
    private float width;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DeviecNo")
    public String getDeviecNo() {
        return deviecNo;
    }

    public void setDeviecNo(String deviecNo) {
        this.deviecNo = deviecNo;
    }

    @Basic
    @Column(name = "LaneID")
    public int getLaneId() {
        return laneId;
    }

    public void setLaneId(int laneId) {
        this.laneId = laneId;
    }

    @Basic
    @Column(name = "PointX")
    public float getPointX() {
        return pointX;
    }

    public void setPointX(float pointX) {
        this.pointX = pointX;
    }

    @Basic
    @Column(name = "PointY")
    public float getPointY() {
        return pointY;
    }

    public void setPointY(float pointY) {
        this.pointY = pointY;
    }

    @Basic
    @Column(name = "Width")
    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LaneEntity that = (LaneEntity) o;

        if (id != that.id) return false;
        if (laneId != that.laneId) return false;
        if (Float.compare(that.pointX, pointX) != 0) return false;
        if (Float.compare(that.pointY, pointY) != 0) return false;
        if (Float.compare(that.width, width) != 0) return false;
        if (deviecNo != null ? !deviecNo.equals(that.deviecNo) : that.deviecNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (deviecNo != null ? deviecNo.hashCode() : 0);
        result = 31 * result + laneId;
        result = 31 * result + (pointX != +0.0f ? Float.floatToIntBits(pointX) : 0);
        result = 31 * result + (pointY != +0.0f ? Float.floatToIntBits(pointY) : 0);
        result = 31 * result + (width != +0.0f ? Float.floatToIntBits(width) : 0);
        return result;
    }
}
