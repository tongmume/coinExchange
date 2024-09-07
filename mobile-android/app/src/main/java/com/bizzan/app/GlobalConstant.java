package com.bizzan.app;

/**
 * Created by Administrator on 2017/5/5.
 */

public class GlobalConstant {
    //TOKEN失效错误码
    public static final int TOKEN_DISABLE1 = 4000;
    public static final int TOKEN_DISABLE2 = -1;
    //自定义错误码
    public static final int JSON_ERROR = -9999;
    public static final int VOLLEY_ERROR = -9998;
    public static final int TOAST_MESSAGE = -9997;
    public static final int OKHTTP_ERROR = -9996;
    public static final int NO_DATA = -9995;

    ///////////////////permission
    public static final int PERMISSION_CONTACT = 0;
    public static final int PERMISSION_CAMERA = 1;
    public static final int PERMISSION_STORAGE = 2;

    //常用常量
    public static final int TAKE_PHOTO = 10;
    public static final int CHOOSE_ALBUM = 11;

    /**
     * k线图对应tag值
     */
    public static final int TAG_DIVIDE_TIME = 0; // 分时图
    public static final int TAG_ONE_MINUTE = 1; // 1分钟
    public static final int TAG_FIVE_MINUTE = 2; // 5分钟
    public static final int TAG_AN_HOUR = 3; // 1小时
    public static final int TAG_DAY = 4; // 1天
    public static final int TAG_THIRTY_MINUTE = 5; // 30分钟
    public static final int TAG_WEEK = 6; // 1周
    public static final int TAG_MONTH = 7; // 1月


    //用户注册协议
    public static final String USER_AGREEMENT_ID="5";
    //商家认证协议
    public static final String SELLER_AGREEMENT_ID="11";

    // C2C交易须知
    public static final String CTC_TRADEARTICLE_ID="40";
}
