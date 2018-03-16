package com.djj.middleware.entities;

import javax.persistence.*;

@Entity
@Table(name = "queuedata_queue_list", schema = "citybrainmiddleware", catalog = "")
public class QueuedataQueueListEntity {
    private int queueDataId;
    private String id;
    private int laneNo;
    private int queueLen;
    private int queueHead;
    private int queueTail;
    private int queueNum;

    @Id
    @Column(name = "QueueData_Id")
    public int getQueueDataId() {
        return queueDataId;
    }

    public void setQueueDataId(int queueDataId) {
        this.queueDataId = queueDataId;
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
    @Column(name = "Queue_Len")
    public int getQueueLen() {
        return queueLen;
    }

    public void setQueueLen(int queueLen) {
        this.queueLen = queueLen;
    }

    @Basic
    @Column(name = "Queue_Head")
    public int getQueueHead() {
        return queueHead;
    }

    public void setQueueHead(int queueHead) {
        this.queueHead = queueHead;
    }

    @Basic
    @Column(name = "Queue_Tail")
    public int getQueueTail() {
        return queueTail;
    }

    public void setQueueTail(int queueTail) {
        this.queueTail = queueTail;
    }

    @Basic
    @Column(name = "Queue_Num")
    public int getQueueNum() {
        return queueNum;
    }

    public void setQueueNum(int queueNum) {
        this.queueNum = queueNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QueuedataQueueListEntity that = (QueuedataQueueListEntity) o;

        if (queueDataId != that.queueDataId) return false;
        if (laneNo != that.laneNo) return false;
        if (queueLen != that.queueLen) return false;
        if (queueHead != that.queueHead) return false;
        if (queueTail != that.queueTail) return false;
        if (queueNum != that.queueNum) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = queueDataId;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + laneNo;
        result = 31 * result + queueLen;
        result = 31 * result + queueHead;
        result = 31 * result + queueTail;
        result = 31 * result + queueNum;
        return result;
    }
}
