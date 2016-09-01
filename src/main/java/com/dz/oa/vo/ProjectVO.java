package com.dz.oa.vo;

import com.dz.oa.entity.ProjectLocation;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Dawei on 8/31/16.
 */
public class ProjectVO {
    private Integer id;
    private String name;
    private String desc;
    private String location;
    private ProjectLocation locationDetail;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ProjectLocation getLocationDetail() {
        return locationDetail;
    }

    public void setLocationDetail(ProjectLocation locationDetail) {
        this.locationDetail = locationDetail;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ProjectVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", location=" + location +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
