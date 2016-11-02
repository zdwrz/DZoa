package com.dz.oa.converter;

import com.dz.oa.entity.Project;
import com.dz.oa.entity.TsBillCodeLookup;
import com.dz.oa.entity.TsMain;
import com.dz.oa.utility.Constants;
import com.dz.oa.vo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by daweizhuang on 11/2/16.
 */
public class TimesheetToReportConverter {
    public static List<TsReportItem> convertTsToReportItem(List<TimeSheetProjectVO> dataList){
        List<TsReportItem> itemList = new ArrayList<>();
        for (TimeSheetProjectVO tsvo : dataList) {
            ProjectVO project = tsvo.getProject();
            for (int i = 0 ; i < tsvo.getBillCodeList().size(); i++) {
                TsBillCodeLookup code = tsvo.getBillCodeList().get(i).getBillCode();
                Map<String, TimeSheetSlotVO> valueMap = tsvo.getBillCodeList().get(i).getSlots();
                String projName = "";
                if(i == 0){
                    projName = project.getName();
                }
                itemList.add(new TsReportItem(projName,code.getCodeValue(),
                        valueMap.get(Constants.MONDAY) == null?null:valueMap.get(Constants.MONDAY).getValue(),
                        valueMap.get(Constants.TUESDAY) == null?null:valueMap.get(Constants.TUESDAY).getValue(),
                        valueMap.get(Constants.WEDNESDAY) == null?null:valueMap.get(Constants.WEDNESDAY).getValue(),
                        valueMap.get(Constants.THURSDAY) == null?null:valueMap.get(Constants.THURSDAY).getValue(),
                        valueMap.get(Constants.FRIDAY) == null?null:valueMap.get(Constants.FRIDAY).getValue(),
                        valueMap.get(Constants.SATURDAY) == null?null:valueMap.get(Constants.SATURDAY).getValue(),
                        valueMap.get(Constants.SUNDAY) == null?null:valueMap.get(Constants.SUNDAY).getValue(),
                        ""));
            }
        }
        return itemList;
    }
}
