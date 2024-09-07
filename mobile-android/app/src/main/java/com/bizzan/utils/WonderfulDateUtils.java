package com.bizzan.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Administrator on 2017/9/1.
 */

public class WonderfulDateUtils {
    /**
     * 将时间戳转化成固定格式（默认 yyyy-MM-dd HH:mm:ss 当前时间 ）
     */
    public static String getFormatTime(String format, Date date) {
        if (com.bizzan.utils.WonderfulStringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        if (date == null) {
            date = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        int code = SharedPreferenceInstance.getInstance().getLanguageCode();
        switch (code) {
            case -1:
            case 1:
                sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
                break;
            case 3:
                sdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
                break;
            case 4:
                sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
                break;
            case 5:
                sdf.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
                break;
            case 6:
                sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                break;
            case 7:
                sdf.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
                break;
            case 8:
                sdf.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
                break;
        }
        String formatTime = sdf.format(date);
        return formatTime;
    }

    /**
     * 将固定格式转化成时间戳（默认 yyyy-MM-dd HH:mm:ss）
     */
    public static long getTimeMillis(String format, String dateString) {
        if (com.bizzan.utils.WonderfulStringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        int code = SharedPreferenceInstance.getInstance().getLanguageCode();
        switch (code) {
            case -1:
            case 1:
                sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
                break;
            case 3:
                sdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
                break;
            case 4:
                sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
                break;
            case 5:
                sdf.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
                break;
            case 6:
                sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                break;
            case 7:
                sdf.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
                break;
            case 8:
                sdf.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
                break;
        }
        try {
            Date date = sdf.parse(dateString);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将固定格式转化成时间戳( HH:mm:ss）
     */
    public static long getTimeMillisFromHourMinuteSecond(String format, String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(dateString);
            Calendar calendarNow = Calendar.getInstance();
            Calendar calendarTarget = Calendar.getInstance();
            calendarTarget.setTime(date);
            calendarTarget.set(Calendar.YEAR, calendarNow.get(Calendar.YEAR));
            calendarTarget.set(Calendar.MONTH, calendarNow.get(Calendar.MONTH));
            calendarTarget.set(Calendar.DAY_OF_YEAR, calendarNow.get(Calendar.DAY_OF_YEAR));
            return calendarTarget.getTime().getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将时间戳转date
     */
    public static Date getDate(String pattern, Long dateString) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        int code = SharedPreferenceInstance.getInstance().getLanguageCode();
        switch (code) {
            case -1:
            case 1:
                format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
                break;
            case 3:
                format.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
                break;
            case 4:
                format.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
                break;
            case 5:
                format.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
                break;
            case 6:
                format.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                break;
            case 7:
                format.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
                break;
            case 8:
                format.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
                break;
        }
        String d = format.format(dateString);
        Date date = null;
        try {
            date = format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * string转date
     *
     * @param strTime
     * @param formatType
     * @return
     */
    public static Date getDateTransformString(String strTime, String formatType) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        int code = SharedPreferenceInstance.getInstance().getLanguageCode();
        switch (code) {
            case -1:
            case 1:
                formatter.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
                break;
            case 3:
                formatter.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
                break;
            case 4:
                formatter.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
                break;
            case 5:
                formatter.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
                break;
            case 6:
                formatter.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                break;
            case 7:
                formatter.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
                break;
            case 8:
                formatter.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
                break;
        }
        Date date = null;
        try {
            date = formatter.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private static ThreadLocal<SimpleDateFormat> DateLocal = new ThreadLocal<SimpleDateFormat>();

    /**
     * 判断是否为今天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean IsToday(String day) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为昨天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean IsYesterday(String day) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == -1) {
                return true;
            }
        }
        return false;
    }

    public static SimpleDateFormat getDateFormat() {
        if (null == DateLocal.get()) {
            DateLocal.set(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA));
        }
        return DateLocal.get();
    }

    /**
     * 将毫秒转化成固定格式的时间
     * 时间格式: yyyy-MM-dd HH:mm:ss
     *
     * @param millisecond
     * @return
     */
    public static String getDateTimeFromMillisecond(Long millisecond) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int code = SharedPreferenceInstance.getInstance().getLanguageCode();
        switch (code) {
            case -1:
            case 1:
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
                break;
            case 3:
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
                break;
            case 4:
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
                break;
            case 5:
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
                break;
            case 6:
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
                break;
            case 7:
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
                break;
            case 8:
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
                break;
        }
        Date date = new Date(millisecond);
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

}
