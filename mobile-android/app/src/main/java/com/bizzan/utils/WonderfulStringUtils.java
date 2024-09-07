package com.bizzan.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/30.
 */

public class WonderfulStringUtils {

    /**
     * 高精度小数转换为字符串，避免出现科学计数法
     */
    public static String getLongFloatString(String value, int length) {
        try {
            String format4 = new DecimalFormat("#0.00000000").format(value);
            BigDecimal bigDecimal = new BigDecimal(format4);
            return bigDecimal.setScale(length, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        } catch (Exception e) {
            Log.e("ddd",value);
            e.printStackTrace();
            return value;
        }
    }

    public static String getLongFloatString(double value, int length) {
        try {
            String format4 = new DecimalFormat("#0.00000000").format(value);
            BigDecimal bigDecimal = new BigDecimal(format4);
            return bigDecimal.setScale(length, BigDecimal.ROUND_DOWN).stripTrailingZeros().toPlainString();
        } catch (Exception e) {
            e.printStackTrace();
            return value + "";
        }
    }

    public static String parseFloatString(String value, int length) {
        try {
            double dvalue = parseDouble(value,0);
            BigDecimal bigDecimal = new BigDecimal(dvalue);
            return bigDecimal.setScale(length, BigDecimal.ROUND_DOWN).toPlainString();
        } catch (Exception e) {
            e.printStackTrace();
            return value + "";
        }
    }

    /**
     * String转Int
     */
    public static int parseInt(String str, int defaultValue) {
        try {
            return (int) parseDouble(str, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * String 转 double
     */
    public static double parseDouble(String str, double defaultValue) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 判断文本是否为空为null
     */
    public static boolean isEmpty(String... strs) {
        for (String str : strs) {
            if (str == null || "".equals(str) || "null".equals(str.toLowerCase())) return true;
        }
        return false;
    }

    /**
     * 截取千位字符
     */

    public static String getThousand(String str) {
        Double d = Double.parseDouble(str);
        if (d >= 1000) {
            d = d / 1000.0;
            str = WonderfulMathUtils.getRundNumber(d, 1, null) + "k";
        }
        return str;
    }

    /**
     * 验证密码 至少一个字母和数字6-20位
     */
    public static boolean isStandard(String... strs) {
        String regex = "^(?=.*?[a-zA-Z])(?=.*?[0-9])[a-zA-Z0-9]{6,20}$";
        if (!isEmpty(strs)) {
            for (String str : strs) {
                if (!str.matches(regex)) return false;
                //else return true;
            }
        } else return false;
        return true;
    }


    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    // currentTime要转换的long类型的时间
    // formatType要转换的string类型的时间格式
    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    // currentTime要转换的long类型的时间
    // formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    // formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
    // data Date类型的时间
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    /**
     * 从asset路径下读取对应文件转String输出
     *
     * @param mContext
     * @return
     */
    public static String getJson(Context mContext, String fileName) {
        StringBuilder sb = new StringBuilder();
        AssetManager am = mContext.getAssets();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    am.open(fileName)));
            String next = "";
            while (null != (next = br.readLine())) {
                sb.append(next);
            }
        } catch (IOException e) {
            e.printStackTrace();
            sb.delete(0, sb.length());
        }
        return sb.toString().trim();
    }


}
