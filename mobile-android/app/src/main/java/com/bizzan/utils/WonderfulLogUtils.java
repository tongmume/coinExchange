package com.bizzan.utils;

import android.util.Log;

import com.bizzan.app.MyApplication;


/**
 * Created by Administrator on 2017/8/30.
 */

public class WonderfulLogUtils {
    public static void logi(String TAG, String content) {
        if (!MyApplication.getApp().isReleased()) {
            Log.i(TAG, content);
        }
    }

    public static void show(String TAG, String str) {
        if (!MyApplication.getApp().isReleased()) {
            str = str.trim();
            int index = 0;
            int maxLength = 4000;
            String sub;
            try {
                while (index < str.length()) {
                    // java的字符不允许指定超过总的长度end
                    if (str.length() <= index + maxLength) {
                        sub = str.substring(index);
                    } else {
                        sub = str.substring(index, maxLength);
                    }

                    index += maxLength;
                    Log.i(TAG, sub.trim());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public static void loge(String TAG, String content) {
        if (!MyApplication.getApp().isReleased()) {
            Log.e(TAG, content);
        }
    }

    public static void logd(String TAG, String content) {
        if (!MyApplication.getApp().isReleased()) {
            Log.d(TAG, content);
        }
    }


}
