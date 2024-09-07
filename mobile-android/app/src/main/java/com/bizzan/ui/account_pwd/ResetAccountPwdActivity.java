package com.bizzan.ui.account_pwd;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bizzan.R;
import com.bizzan.app.Injection;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseActivity;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;

public class ResetAccountPwdActivity extends BaseActivity implements AccountPwdContract.ResetView {
    public static final int RETURN_FROM_RESETACCOUNT_PWD = 0;
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    //    @BindView(R.id.ibRegist)
//    TextView ibRegist;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.etLoginPwd)
    EditText etLoginPwd;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.tvSend)
    TextView tvSend;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.etRepeatePwd)
    EditText etRepeatePwd;
    @BindView(R.id.tvEdit)
    TextView tvEdit;
    @BindView(R.id.yan)
    ImageView yan;
    @BindView(R.id.yan1)
    ImageView yan1;
    @BindView(R.id.view_back)
    View view_back;
    private boolean isYan = true;
    private boolean isYan1 = true;
    private String re_send = "ReSend";
    private CountDownTimer timer;
    private AccountPwdContract.ResetPresenter presenter;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, ResetAccountPwdActivity.class);
        activity.startActivityForResult(intent, RETURN_FROM_RESETACCOUNT_PWD);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_reset_account_pwd;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        new ResetAccountPwdPresenter(Injection.provideTasksRepository(getApplicationContext()), this);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCode();
            }
        });
        yan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isYan = !isYan;
                Drawable no = getResources().getDrawable(R.drawable.yan_no);
                Drawable yes = getResources().getDrawable(R.drawable.yan_yes);
                if (isYan) {
                    //显示
                    etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    yan.setImageDrawable(no);

                } else {
                    etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    yan.setImageDrawable(yes);
                }
            }
        });
        yan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isYan1 = !isYan1;
                Drawable no = getResources().getDrawable(R.drawable.yan_no);
                Drawable yes = getResources().getDrawable(R.drawable.yan_yes);
                if (isYan1) {
                    //显示
                    etRepeatePwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    yan1.setImageDrawable(no);

                } else {
                    etRepeatePwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    yan1.setImageDrawable(yes);
                }
            }
        });
        re_send = MyApplication.getApp().getString(R.string.re_send);
    }

    private void sendCode() {
        presenter.resetAccountPwdCode(getToken());
    }

    private void reset() {
        String newPassword = etPwd.getText().toString();
        String re = etRepeatePwd.getText().toString();
        String code = etCode.getText().toString();
        if (WonderfulStringUtils.isEmpty(newPassword, re, code)) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this, R.string.Incomplete_information));
            return;
        }
        if (!re.equals(newPassword))
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this, R.string.pwd_diff));
        else presenter.resetAccountPwd(getToken(), newPassword, code);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            isSetTitle = true;
            ImmersionBar.setTitleBar(this, llTitle);
        }
    }

    @Override
    public void setPresenter(AccountPwdContract.ResetPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void resetAccountPwdSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void resetAccountPwdFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void resetAccountPwdCodeSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        fillCodeView(90 * 1000);
    }

    private void fillCodeView(long time) {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvSend.setText(re_send + "（" + millisUntilFinished / 1000 + "）");
            }

            @Override
            public void onFinish() {
                tvSend.setText(R.string.send_code);
                tvSend.setEnabled(true);
                timer.cancel();
                timer = null;
            }
        };
        timer.start();
    }

    @Override
    public void resetAccountPwdCodeFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }
}
