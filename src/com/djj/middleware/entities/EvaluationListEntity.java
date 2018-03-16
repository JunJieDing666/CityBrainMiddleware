package com.djj.middleware.entities;

import javax.persistence.*;

@Entity
@Table(name = "evaluation_list", schema = "citybrainmiddleware", catalog = "")
public class EvaluationListEntity {
    private int evaluationId;
    private String id;
    private int laneNo;
    private float stops;
    private float delay;
    private float fuel;
    private float emissions;

    @Id
    @Column(name = "Evaluation_Id")
    public int getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
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
    @Column(name = "Stops")
    public float getStops() {
        return stops;
    }

    public void setStops(float stops) {
        this.stops = stops;
    }

    @Basic
    @Column(name = "Delay")
    public float getDelay() {
        return delay;
    }

    public void setDelay(float delay) {
        this.delay = delay;
    }

    @Basic
    @Column(name = "Fuel")
    public float getFuel() {
        return fuel;
    }

    public void setFuel(float fuel) {
        this.fuel = fuel;
    }

    @Basic
    @Column(name = "Emissions")
    public float getEmissions() {
        return emissions;
    }

    public void setEmissions(float emissions) {
        this.emissions = emissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EvaluationListEntity that = (EvaluationListEntity) o;

        if (evaluationId != that.evaluationId) return false;
        if (laneNo != that.laneNo) return false;
        if (Float.compare(that.stops, stops) != 0) return false;
        if (Float.compare(that.delay, delay) != 0) return false;
        if (Float.compare(that.fuel, fuel) != 0) return false;
        if (Float.compare(that.emissions, emissions) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = evaluationId;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + laneNo;
        result = 31 * result + (stops != +0.0f ? Float.floatToIntBits(stops) : 0);
        result = 31 * result + (delay != +0.0f ? Float.floatToIntBits(delay) : 0);
        result = 31 * result + (fuel != +0.0f ? Float.floatToIntBits(fuel) : 0);
        result = 31 * result + (emissions != +0.0f ? Float.floatToIntBits(emissions) : 0);
        return result;
    }
}
