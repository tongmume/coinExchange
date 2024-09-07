package com.bizzan.utils;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/8/30.
 */

public class WonderfulMathUtils {

    public static String getRundNumber(double number, int n, String pattern) {
        if (WonderfulStringUtils.isEmpty(pattern)) pattern = "########0.";
        String str = "";
        for (int j = 0; j < n; j++) str += "0";
        pattern += str;
        int m = (int) Math.pow(10, n);
        number = (Math.round(number * m)) / (m * 1.0);
        return new DecimalFormat(pattern).format(number);
    }
}
