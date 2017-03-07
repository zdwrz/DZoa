package org.trs.oa.vo;

/**
 * Created by daweizhuang on 11/2/16.
 */
public class TsReportItem {

    private String projectName;
    private String billCode;
    private Integer hours1;
    private Integer hours2;
    private Integer hours3;
    private Integer hours4;
    private Integer hours5;
    private Integer hours6;
    private Integer hours7;
    private String comment;

    public TsReportItem(String projectName, String billCode, Integer hours1, Integer hours2, Integer hours3, Integer hours4, Integer hours5, Integer hours6, Integer hours7, String comment) {
        this.projectName = projectName;
        this.billCode = billCode;
        this.hours1 = hours1;
        this.hours2 = hours2;
        this.hours3 = hours3;
        this.hours4 = hours4;
        this.hours5 = hours5;
        this.hours6 = hours6;
        this.hours7 = hours7;
        this.comment = comment;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public Integer getHours1() {
        return hours1;
    }

    public void setHours1(Integer hours1) {
        this.hours1 = hours1;
    }

    public Integer getHours2() {
        return hours2;
    }

    public void setHours2(Integer hours2) {
        this.hours2 = hours2;
    }

    public Integer getHours3() {
        return hours3;
    }

    public void setHours3(Integer hours3) {
        this.hours3 = hours3;
    }

    public Integer getHours4() {
        return hours4;
    }

    public void setHours4(Integer hours4) {
        this.hours4 = hours4;
    }

    public Integer getHours5() {
        return hours5;
    }

    public void setHours5(Integer hours5) {
        this.hours5 = hours5;
    }

    public Integer getHours6() {
        return hours6;
    }

    public void setHours6(Integer hours6) {
        this.hours6 = hours6;
    }

    public Integer getHours7() {
        return hours7;
    }

    public void setHours7(Integer hours7) {
        this.hours7 = hours7;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
