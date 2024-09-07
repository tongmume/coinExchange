package com.bizzan.utils;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.bizzan.app.MyApplication;


public class WonderfulToastUtils {
    private static Context context = MyApplication.getApp();
    private static TextView textView = MyApplication.getApp().getTvToast();
    private static int duration = 2000;
    private static Toast mToast;
    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        @Override
        public void run() {
            mToast.cancel();
        }
    };

    /**
     * 获取string.xml 资源文件字符串
     * @param id 资源文件id
     * @return 资源文件对应的字符串
     */
    public static String getString(Context context,int id){
        return context.getResources().getString(id);
    }

    /**
     * 获取string.xml 资源文件字符串数组
     * @param id 资源文件ID
     * @return 资源文件对应字符串数组
     */
    public static String[] getStringArray(int id){
        return context.getResources().getStringArray(id);
    }
    public static void showMyViewToast(String s) {
        mHandler.removeCallbacks(r);
        if (mToast != null) {
            textView.setText(s);
        } else {
            mToast = new Toast(context);
            textView.setText(s);
        }
        mToast.setView(textView);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mHandler.postDelayed(r, duration);
        mToast.show();
    }

    public static void showToast(String s) {
        mHandler.removeCallbacks(r);
        if (mToast != null) {
            try {
                mToast.setText(s);
            } catch (Exception e) {
                // 这里是由于mToast是static的如果之前已经使用了自定义的toast（即是new出来的）那么，就会出现错误;
                mToast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
            }
        } else {
            mToast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
        }
        mHandler.postDelayed(r, duration);
        mToast.show();
    }

    public static void showToast(int resourceId) {
        String s = MyApplication.getApp().getResources().getString(resourceId);
        if (s == null) throw new RuntimeException("Toast message can  not be  null !");
        mHandler.removeCallbacks(r);
        if (mToast != null) {
            try {
                mToast.setText(s);
            } catch (Exception e) {
                // 这里是由于mToast是static的如果之前已经使用了自定义的toast（即是new出来的）那么，就会出现错误;
                mToast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
            }
        } else {
            mToast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
        }
        mHandler.postDelayed(r, duration);
        mToast.show();
    }

    public static void setDuration(int duration) {
        WonderfulToastUtils.duration = duration;
    }
}
