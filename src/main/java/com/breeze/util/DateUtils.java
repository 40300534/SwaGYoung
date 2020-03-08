package com.breeze.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取YYYY-MM-DD格式
     * @return
     */
    public static String getDay() {
        return sdfDay.format(new Date());
    }
}
