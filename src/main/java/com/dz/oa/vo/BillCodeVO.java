package com.dz.oa.vo;

import com.dz.oa.entity.TsBillCodeLookup;

import java.util.Date;
import java.util.Map;

/**
 * Created by daweizhuang on 9/27/16.
 */
public class BillCodeVO {
    private TsBillCodeLookup billCode;
    private Map<Date, TimeSheetSlotVO> slots;

    public TsBillCodeLookup getBillCode() {
        return billCode;
    }

    public void setBillCode(TsBillCodeLookup billCode) {
        this.billCode = billCode;
    }

    public Map<Date, TimeSheetSlotVO> getSlots() {
        return slots;
    }

    public void setSlots(Map<Date, TimeSheetSlotVO> slots) {
        this.slots = slots;
    }
}
