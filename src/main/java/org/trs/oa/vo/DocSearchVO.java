package org.trs.oa.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by daweizhuang on 9/23/16.
 */
public class DocSearchVO {
    private String name;
    private Integer projId;
    private @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate;
    private @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProjId() {
        return projId;
    }

    public void setProjId(Integer projId) {
        this.projId = projId;
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
}
