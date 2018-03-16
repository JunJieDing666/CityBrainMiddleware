package com.djj.middleware.entities;

import javax.persistence.*;

@Entity
@Table(name = "intersection", schema = "citybrainmiddleware", catalog = "")
public class IntersectionEntity {
    private int id;
    private String interSectionId;
    private int dirNum;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "DirNum")
    public int getDirNum() {
        return dirNum;
    }

    public void setDirNum(int dirNum) {
        this.dirNum = dirNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IntersectionEntity that = (IntersectionEntity) o;

        if (id != that.id) return false;
        if (dirNum != that.dirNum) return false;
        if (interSectionId != null ? !interSectionId.equals(that.interSectionId) : that.interSectionId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (interSectionId != null ? interSectionId.hashCode() : 0);
        result = 31 * result + dirNum;
        return result;
    }
}
