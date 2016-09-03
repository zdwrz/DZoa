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
    private Integer enterpriseId;
    private ProjectLocation locationDetail;
    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public ProjectLocation getLocationDetail() {
        return locationDetail;
    }

    public void setLocationDetail(ProjectLocation locationDetail) {
        this.locationDetail = locationDetail;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    private String statusStr;

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

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    @Override
    public String toString() {
        return "ProjectVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", location=" + locationDetail.getCustomAddress() +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
