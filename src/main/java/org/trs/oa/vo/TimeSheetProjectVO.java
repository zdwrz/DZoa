package org.trs.oa.vo;

import java.util.List;

/**
 * Created by daweizhuang on 9/27/16.
 */
public class TimeSheetProjectVO {
    private ProjectVO project;
    private List<BillCodeVO> billCodeList;

    public ProjectVO getProject() {
        return project;
    }

    public void setProject(ProjectVO project) {
        this.project = project;
    }

    public List<BillCodeVO> getBillCodeList() {
        return billCodeList;
    }

    public void setBillCodeList(List<BillCodeVO> billCodeList) {
        this.billCodeList = billCodeList;
    }
}
