package com.bizzan.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.bizzan.R;

/**
 * Created by Administrator on 2017/12/6.
 */

public class WonderfulDialogUtils {

//    /**
//     * 显示成功或是提示的对话框
//     *
//     * @param context Activity对象
//     * @param type    0 提示  1 操作成功
//     */
//    public static void showSuccessPopupDialog(Activity context, int type) {
//        View view;
//        if (type == 0) view = LayoutInflater.from(context).inflate(R.layout.dialog_operate_notice, null);//提示
//        else view = LayoutInflater.from(context).inflate(R.layout.dialog_operate_notice, null);//成功
//        final PopupWindow popupWindow = new PopupWindow(view, MyApplication.getApp().getmWidth(), MyApplication.getApp().getmHeight());
//        TextView tvSure = (TextView) view.findViewById(R.id.tvSure);
//        tvSure.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//            }
//        });
//        ImageView ivClose = (ImageView) view.findViewById(R.id.ivClose);
//        ivClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//            }
//        });
//        RelativeLayout rlRoot = (RelativeLayout) view.findViewById(R.id.rlRoot);
//        rlRoot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//            }
//        });
//        popupWindow.setClippingEnabled(false);
//        popupWindow.setBackgroundDrawable(new ColorDrawable());
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setFocusable(true);
//        popupWindow.showAtLocation(context.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
//    }
//

    /**
     * 显示默认布局的dialog
     */
    public static void showDefaultDialog(Activity context, String title, String message, String cancleText, String sureText, DialogInterface.OnClickListener cancleListeners, DialogInterface.OnClickListener sureListeners) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.custom_dialog);
        if (!WonderfulStringUtils.isEmpty(title)) builder.setTitle(title);
        if (!WonderfulStringUtils.isEmpty(cancleText) || cancleListeners != null) builder.setNegativeButton(cancleText, cancleListeners);
        if (!WonderfulStringUtils.isEmpty(sureText) || sureListeners != null) builder.setPositiveButton(sureText, sureListeners);
        builder.setMessage(message).create().show();
    }

}
