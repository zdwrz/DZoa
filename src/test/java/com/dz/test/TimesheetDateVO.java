package com.dz.test;

import com.dz.oa.vo.TimeSheetDateVO;
import org.junit.Test;

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
}
