package com.dz.test;

import com.dz.oa.utility.OaUtils;
import com.dz.oa.vo.TimeSheetDateVO;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by daweizhuang on 9/27/16.
 */
public class TimesheetDateVO {
    @Test
    public void testParseDay(){
        TimeSheetDateVO vo = new TimeSheetDateVO(new Date());
        System.out.println(vo.getDay());
        System.out.println(vo.getDate());
    }

    @Test
    public void testUtil() {
        System.out.println(OaUtils.getMondayOfThisWeek());
    }

    @Test
    public void testgetOffsetOf() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH,26);
        cal.set(Calendar.MONTH,8);
        System.out.println(OaUtils.getOffsetOf((cal.getTime())));
    }
}
