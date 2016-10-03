package com.dz.oa.utility;import org.apache.commons.lang3.ClassPathUtils;import org.apache.commons.lang3.StringUtils;import java.text.SimpleDateFormat;import java.util.Calendar;import java.util.Date;/** * Created by daweizhuang on 8/30/16. */public class OaUtils {    public static String timeStampPrefix(String origin) {        SimpleDateFormat formatter = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");        String prefix = formatter.format(new Date());        return prefix + "!" + origin;    }    public static boolean notEmpty(String location) {        return StringUtils.isNotEmpty(location);    }    public static Date getMondayOfThisWeek() {        Calendar cal = Calendar.getInstance();        int today = cal.get(Calendar.DAY_OF_WEEK);        cal.add(Calendar.DAY_OF_MONTH, (today >= 2) ? -1 * today + 2 : today - 7);        return cal.getTime();    }    public static String getDayOfWeek(Date date) {        String res = "";        Calendar cal = Calendar.getInstance();        cal.setTime(date);        int day = cal.get(Calendar.DAY_OF_WEEK);        switch (day) {            case 1: res="sunday";break;            case 2: res="monday";break;            case 3: res="tuesday";break;            case 4: res="wednesday";break;            case 5: res="thursday";break;            case 6: res="friday";break;            case 7: res="saturday";break;        }        return res;    }}