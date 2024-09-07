package com.bizzan.base;

import android.content.Context;
import android.view.View;

import com.bizzan.app.MyApplication;


/**
 * View相关类

 */
public class LViewHelper {
    /**
     * 通过布局资源id获取相应的view对象
     *
     * @param resId 资源id
     * @return 填充好的view
     */
    public static View getView(int resId) {
        return View.inflate(MyApplication.getApp(), resId, null);
    }

    /**
     * 通过布局资源id获取相应的view对象
     *
     * @param resId   资源id
     * @param context 上下文
     * @return 填充好的view
     */
    public static View getView(int resId, Context context) {
        return View.inflate(context, resId, null);
    }


}
