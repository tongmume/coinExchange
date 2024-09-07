package com.bizzan.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.bizzan.R;
import com.bizzan.app.MyApplication;
import com.bizzan.app.UrlFactory;
import com.bizzan.base.BaseActivity;
import com.bizzan.base.BaseDialogFragment;
import com.bizzan.entity.SafeSetting;
import com.bizzan.utils.WonderfulDpPxUtils;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulToastUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

import butterknife.BindView;
import okhttp3.Request;

public class SafeValidateDialog extends BaseDialogFragment {
    @BindView(R.id.label)
    TextView label;
    @BindView(R.id.btnclose)
    View btnclose;
    @BindView(R.id.etCode)
    EditText editText;
    @BindView(R.id.tvmobile)
    TextView tvmobile;
    @BindView(R.id.tvGetCode)
    TextView tvGetCode;
    @BindView(R.id.btnSubmit)
    View btnsubmit;
    private SafeSetting safeSetting;
    private TimeCount timeCount;
    private OperateCallback callback;
    private static Context context1;

    public static SafeValidateDialog getInstance(Context context) {
        SafeValidateDialog fragment = new SafeValidateDialog();
        context1 = context;
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.layout_safevalidate;
    }

    @Override
    protected void initLayout() {
        getDialog().setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        getDialog().getWindow().setSoftInputMode(params.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.bottomDialog);
        window.setLayout(MyApplication.getApp().getmWidth(), (WonderfulDpPxUtils.dip2px(getActivity(), 400)) + getBottomStatusHeight(context1));
    }

    public void displayLoadingPopup() {
        if (getActivity() != null) ((BaseActivity) getActivity()).displayLoadingPopup();
    }

    public void hideLoadingPopup() {
        if (getActivity() != null) ((BaseActivity) getActivity()).hideLoadingPopup();
    }

    public void setData(SafeSetting safeSetting, OperateCallback callback) {
        this.safeSetting = safeSetting;
        this.callback = callback;
    }

    @Override
    protected void initView() {
        rootView.setOnClickListener(view -> {
        });
        if (!TextUtils.isEmpty(safeSetting.getMobilePhone())) {
            label.setText("已绑定手机号:");
            tvmobile.setText(safeSetting.getMobilePhone());
        } else {
            label.setText("已绑定邮箱:");
            tvmobile.setText(safeSetting.getEmail());
        }
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tvGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCode();
            }
        });
    }

    void getCode() {
        displayLoadingPopup();
        String url = !TextUtils.isEmpty(safeSetting.getMobilePhone()) ? UrlFactory.getALbMobileCode() : UrlFactory.getALbEmailCode();
        WonderfulOkhttpUtils.post().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                hideLoadingPopup();
                WonderfulLogUtils.logi("账户设置出错", "账户设置出错：" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                hideLoadingPopup();
                Log.e("httppost", "验证码  " + response);
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") != 0) {
                        WonderfulToastUtils.showToast("短信发送失败 " + object.optString("message"));
                    } else {
                        WonderfulToastUtils.showToast(object.getString("message"));
                    }
                    timeCount = new TimeCount(tvGetCode, 60000, 1000);
                    timeCount.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (timeCount != null) {
            timeCount.cancel();
        }
        tvmobile.setText("发送验证码");
        tvmobile.setEnabled(true);
        editText.setText(null);
    }

    private void submit() {
        String code = editText.getText().toString();
        if (TextUtils.isEmpty(code)) {
            WonderfulToastUtils.showToast("请输入验证码");
            return;
        }
        if (callback != null) {
            if (!TextUtils.isEmpty(safeSetting.getMobilePhone())) {
                callback.onMobileSuccess(safeSetting.getMobilePhone(), code);
            } else {
                callback.onEmailSuccess(safeSetting.getEmail(), code);
            }
        }
        dismiss();
    }

    /**
     * 获取 虚拟按键的高度
     *
     * @param context
     * @return
     */
    public static int getBottomStatusHeight(Context context) {
        int totalHeight = getDpi(context);

        int contentHeight = getScreenHeight(context);

        return totalHeight - contentHeight;
    }

    //获取屏幕原始尺寸高度，包括虚拟功能键高度
    public static int getDpi(Context context) {
        int dpi = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, displayMetrics);
            dpi = displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {

    }

    public interface OperateCallback {
        void onMobileSuccess(String mobile, String code);

        void onEmailSuccess(String email, String code);
    }

    public static class TimeCount extends CountDownTimer {

        WeakReference<TextView> textRefer;

        public TimeCount(TextView textView, long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            textRefer = new WeakReference<>(textView);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (textRefer.get() != null) {
                textRefer.get().setEnabled(false);
                textRefer.get().setClickable(false);
                textRefer.get().setText("(" + millisUntilFinished / 1000 + "s)");
            }
        }

        @Override
        public void onFinish() {
            if (textRefer.get() != null) {
                textRefer.get().setEnabled(true);
                textRefer.get().setClickable(true);
                textRefer.get().setText("重新发送");
            }
        }
    }


}
