package org.trs.oa.vo;

import org.trs.oa.entity.TsBillCodeLookup;

import java.util.Map;

/**
 * Created by daweizhuang on 9/27/16.
 */
public class BillCodeVO {
    private TsBillCodeLookup billCode;
    private Map<String, TimeSheetSlotVO> slots;

    public TsBillCodeLookup getBillCode() {
        return billCode;
    }

    public void setBillCode(TsBillCodeLookup billCode) {
        this.billCode = billCode;
    }

    public Map<String, TimeSheetSlotVO> getSlots() {
        return slots;
    }

    public void setSlots(Map<String, TimeSheetSlotVO> slots) {
        this.slots = slots;
    }
}
