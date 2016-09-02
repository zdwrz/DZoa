package com.dz.oa.utility;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by daweizhuang on 8/30/16.
 */
public class OaUtils {
    public static String timeStampPrefix(String origin) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
        String prefix = formatter.format(new Date());
        return prefix + "!" + origin;
    }

    public static boolean notEmpty(String location) {
        return StringUtils.isNotEmpty(location);
    }
}
