package org.trs.oa.vo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by daweizhuang on 9/27/16.
 */
public class TimeSheetDateVO {
    private Date date;
    private boolean isWeekend;
    private Calendar cal;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
    public TimeSheetDateVO(Date date) {
        cal = Calendar.getInstance();
        cal.setTime(date);
        this.date = date;
        int dayOrWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOrWeek == Calendar.SUNDAY || dayOrWeek == Calendar.SATURDAY) {
            this.isWeekend = true;
        }
    }

    public Date getDate() {
        return date;
    }

    public String getDay() {
        return sdf.format(date);
    }

    public boolean isWeekend() {
        return isWeekend;
    }

}
