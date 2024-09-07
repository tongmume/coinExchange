package com.bizzan.ui.dialog;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.bizzan.R;

public class CommonDialog {

    public static AlertDialog getInstance(Activity activity, String message, String buttonTextYes, String buttonTextNo, boolean showLeftButton, View.OnClickListener listener) {
        View contentView = View.inflate(activity, R.layout.dialog_common, null);
        final AlertDialog dialog = new AlertDialog.Builder(activity,R.style.custom_dialog).setView(contentView).create();
        dialog.show();
        ((TextView) contentView.findViewById(R.id.tv_dialog_message)).setText(message);
        ((TextView) contentView.findViewById(R.id.tv_dialog_yes)).setText(buttonTextYes);
        if (!showLeftButton) {
            contentView.findViewById(R.id.tv_dialog_no).setVisibility(View.GONE);
            contentView.findViewById(R.id.dialog_middle_line).setVisibility(View.GONE);
        }else {
            ((TextView) contentView.findViewById(R.id.tv_dialog_no)).setText(buttonTextNo);
            contentView.findViewById(R.id.tv_dialog_no).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });
        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        contentView.findViewById(R.id.tv_dialog_yes).setOnClickListener(listener);
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = (int)(display.getWidth() * 0.7);
        dialog.getWindow().setAttributes(layoutParams);
        return dialog;
    }



}