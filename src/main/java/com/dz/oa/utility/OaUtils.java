package com.dz.oa.utility;import org.apache.commons.lang3.ClassPathUtils;import org.apache.commons.lang3.StringUtils;import java.text.SimpleDateFormat;import java.util.Calendar;import java.util.Date;/** * Created by daweizhuang on 8/30/16. */public class OaUtils {    public static String timeStampPrefix(String origin) {        SimpleDateFormat formatter = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");        String prefix = formatter.format(new Date());        return prefix + "!" + origin;    }    public static boolean notEmpty(String location) {        return StringUtils.isNotEmpty(location);    }    public static Date getMondayOfThisWeek() {        Calendar cal = Calendar.getInstance();        int today = cal.get(Calendar.DAY_OF_WEEK);        cal.add(Calendar.DAY_OF_MONTH, (today >= 2) ? -1 * today + 2 : today - 7);        return cal.getTime();    }    public static String getDayOfWeek(Date date) {        String res = "";        Calendar cal = Calendar.getInstance();        cal.setTime(date);        int day = cal.get(Calendar.DAY_OF_WEEK);        switch (day) {            case 1: res="sunday";break;            case 2: res="monday";break;            case 3: res="tuesday";break;            case 4: res="wednesday";break;            case 5: res="thursday";break;            case 6: res="friday";break;            case 7: res="saturday";break;        }        return res;    }    public static Date getDateOfWeekDay(Date dateOfMonday, String weekDay) {        Calendar cal = Calendar.getInstance();        cal.setTime(dateOfMonday);        int offset = -1;        switch (weekDay) {            case "monday":                offset = 0;                break;            case "tuesday":                offset = 1;                break;            case "wednesday":                offset = 2;                break;            case "thursday":                offset = 3;                break;            case "friday":                offset = 4;                break;            case "saturday":                offset = 5;                break;            case "sunday":                offset = 6;                break;        }        if (offset < 0) {            throw new RuntimeException("weekDay is not recognized: " + weekDay);        }        cal.add(Calendar.DAY_OF_MONTH, offset);        return cal.getTime();    }    public static Date trimTimeFromDate(Date date){        Calendar cal = Calendar.getInstance();        cal.setTime(date);        cal.set(Calendar.HOUR,0);        cal.set(Calendar.MINUTE,0);        cal.set(Calendar.SECOND,0);        cal.set(Calendar.MILLISECOND,0);        return cal.getTime();    }    public static Date getTimesheetEndDate(Date startDate) {        Calendar cal = Calendar.getInstance();        cal.setTime(startDate);        cal.add(Calendar.DAY_OF_MONTH, 6);        return cal.getTime();    }}