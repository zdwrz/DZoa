package org.trs.oa.vo;

import java.util.Date;

/**
 * Created by daweizhuang on 10/31/16.
 */
public class TimeSheetListItemVO {

    private int offSet;
    private Date startDate;
    private Date endDate;
    private String status = "uncompleted";

    public TimeSheetListItemVO(int offSet, Date startDate, Date endDate) {
        this.offSet = offSet;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public int getOffSet() {
        return offSet;
    }

    public void setOffSet(int offSet) {
        this.offSet = offSet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
