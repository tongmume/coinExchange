package com.bizzan.utils.okhttp;


import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import com.bizzan.R;
import com.bizzan.app.MyApplication;
import com.bizzan.base.ActivityManage;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulToastUtils;

import java.io.IOException;
import java.net.UnknownHostException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/29.
 */

public abstract class StringCallback extends Callback<String> {
    @Override
    public String parseNetworkResponse(Response response) throws IOException {
        return response.body().string();
    }

    @Override
    public void onError(Request request, Exception e) {
        WonderfulLogUtils.logi("miao", e.toString().length()+"-666-");
        MyApplication.getApp().typeBiaoshi1=1+ MyApplication.getApp().typeBiaoshi1;

        if (MyApplication.getApp().typeBiaoshi1==2){
            MyApplication.getApp().typeBiaoshi1=0;
        }else {
            return;
        }

        try {
            if (checkInternet()){
                if (e instanceof UnknownHostException||MyApplication.getApp().typeBiaoshi2==404||e.toString().length()==197) {
                    dialogT(MyApplication.app.getResources().getText(R.string.tv_switch_more)+"");
                }
            }else {
                dialogT(MyApplication.app.getResources().getText(R.string.tv_no_network)+"");
                WonderfulToastUtils.showToast(MyApplication.app.getResources().getText(R.string.tv_examine_network)+"");
            }
        } catch (Resources.NotFoundException ex) {
            ex.printStackTrace();
        }
    }
    private void dialogT(String title){
        if (MyApplication.getApp().typeBiaoshi==0){
            MyApplication.getApp().typeBiaoshi=1;
            AlertDialog dialog = new AlertDialog.Builder(ActivityManage.activities.get(ActivityManage.activities.size()-1), R.style.no_net_dialog)
                    .setTitle(MyApplication.app.getResources().getText(R.string.Warm_prompt)+"")
                    .setNegativeButton(MyApplication.app.getResources().getText(R.string.tv_quit)+"", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MyApplication.getApp().typeBiaoshi=0;
                    dialog.cancel();
                    System.exit(0);
                }
            }).create();
            dialog.setMessage(title);
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
        }
    }

    private boolean checkInternet() {
        ConnectivityManager cm = (ConnectivityManager) MyApplication.getApp().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        NetworkInfo.State mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        if ((wifiState != null && wifiState == NetworkInfo.State.CONNECTED) || (mobileState != null && mobileState == NetworkInfo.State.CONNECTED)) {
           return true;
        }
        return false;
    }
}
