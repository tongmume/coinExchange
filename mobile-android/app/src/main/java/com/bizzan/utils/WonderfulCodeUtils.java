package com.bizzan.utils;


import android.support.v4.app.Fragment;

import com.bizzan.R;
import com.bizzan.app.GlobalConstant;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseActivity;

/**
 * Created by wonderful on 2017/5/23.
 */

public class WonderfulCodeUtils {

    private static String unknown_error;
    private static String no_network;
    private static String parse_error;
    private static String login_invalid;
    private static String no_data;


    static {
        unknown_error = MyApplication.getApp().getResources().getString(R.string.unknown_error);
        no_network = MyApplication.getApp().getResources().getString(R.string.no_network);
        parse_error = MyApplication.getApp().getResources().getString(R.string.parse_error);
        login_invalid = MyApplication.getApp().getResources().getString(R.string.login_invalid);
        no_data = MyApplication.getApp().getResources().getString(R.string.no_data);
    }

    public static void checkedErrorCode(BaseActivity activity, Integer code, String toastMessage) {
        if (code == GlobalConstant.OKHTTP_ERROR){
            return;
        }
        String toast = "";
        switch (code) {
            case GlobalConstant.NO_DATA:
                toast = no_data;
//                WonderfulToastUtils.showToast(toast);
                return;
            case GlobalConstant.TOKEN_DISABLE1:
                toast = login_invalid;
                MyApplication.getApp().loginAgain(activity);
//                WonderfulToastUtils.showToast(toast);
                return;
            case GlobalConstant.TOKEN_DISABLE2:
                toast = login_invalid;
                MyApplication.getApp().loginAgain(activity);
//                WonderfulToastUtils.showToast(toast);
                return;
            case GlobalConstant.JSON_ERROR:
                toast = parse_error;
//                WonderfulToastUtils.showToast(toast);
                return;
            case GlobalConstant.VOLLEY_ERROR:
                if (MyApplication.getApp().isConnect()) toast = unknown_error;
                else toast = no_network;
//                WonderfulToastUtils.showToast(toast);
                return;
            case GlobalConstant.OKHTTP_ERROR:
                if (MyApplication.getApp().isConnect()) toast = unknown_error;
                else toast = no_network;
                if(toastMessage != null) {
                    toast = toastMessage;
                }
//                WonderfulToastUtils.showToast(toast);
                return;

        }
        toast = toastMessage;
        if (!WonderfulStringUtils.isEmpty(toastMessage)) {
            WonderfulToastUtils.showToast(toast);
            return;
        }
    }

    public static void checkedErrorCode(Fragment fragment, Integer code, String toastMessage) {
        String toast = "";
        switch (code) {
            case GlobalConstant.NO_DATA:
                toast = no_data;
//                WonderfulToastUtils.showToast(toast);
                return;

            case GlobalConstant.TOKEN_DISABLE1:
                toast = login_invalid;
                MyApplication.getApp().loginAgain(fragment);
//                WonderfulToastUtils.showToast(toast);
                return;
            case GlobalConstant.TOKEN_DISABLE2:
                toast = login_invalid;
                MyApplication.getApp().loginAgain(fragment);
//                WonderfulToastUtils.showToast(toast);
                return;
            case GlobalConstant.JSON_ERROR:
                toast = parse_error;
//                WonderfulToastUtils.showToast(toast);
                return;
            case GlobalConstant.VOLLEY_ERROR:
                if (MyApplication.getApp().isConnect()) toast = unknown_error;
                else toast = no_network;
//                WonderfulToastUtils.showToast(toast);
                return;
        }
        toast = toastMessage;
        if (!WonderfulStringUtils.isEmpty(toastMessage)) {
            WonderfulToastUtils.showToast(toast);
            return;
        }
    }

}
