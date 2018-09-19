package com.github.binarywang.demo.wechat.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author huyong
 * @since 2018/3/23
 */
public class DateUtils {

    /**
     * 格式化时间
     *
     * @param sdf 格式化时间格式:yyyyMMdd
     * @param time 时间戳
     * @return
     */
    public static String format(String format, long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d = new Date(time);
        return sdf.format(d);
    }


    public static Date convertStr2Date(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
