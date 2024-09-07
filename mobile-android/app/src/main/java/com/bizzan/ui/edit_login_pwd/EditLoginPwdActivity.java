package com.bizzan.ui.edit_login_pwd;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.ui.home.MainActivity;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.User;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;

import butterknife.BindView;
import com.bizzan.app.Injection;

public class EditLoginPwdActivity extends BaseActivity implements EditLoginPwdContract.View {

    @BindView(R.id.ibBack)
    ImageButton ibBack;
//    @BindView(R.id.ibForgotPwd)
//    TextView ibForgotPwd;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.etOldPwd)
    EditText etOldPwd;
    @BindView(R.id.etNewPwd)
    EditText etNewPwd;
    @BindView(R.id.etRepeatPwd)
    EditText etRepeatPwd;
    @BindView(R.id.tvConfirm)
    TextView tvConfirm;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.tvSend)
    TextView tvSend;
    @BindView(R.id.tvNumber)
    TextView tvNumber;
    private EditLoginPwdContract.Presenter presenter;
    private String phone;
    private CountDownTimer timer;
    @BindView(R.id.yan)
    ImageView yan;
    @BindView(R.id.yan1)
    ImageView yan1;
    @BindView(R.id.yan2)
    ImageView yan2;
    @BindView(R.id.view_back)
    View view_back;

    private boolean isYan=false;
    private boolean isYan1=false;
    private boolean isYan2=false;

    public static void actionStart(Context context, String phone) {
        Intent intent = new Intent(context, EditLoginPwdActivity.class);
        intent.putExtra("phone", phone);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_edit_login_pwd;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        new EditLoginPwdPresenter(Injection.provideTasksRepository(getApplicationContext()), this);
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
//        ibForgotPwd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ForgotPwdActivity.actionStart(EditLoginPwdActivity.this);
//            }
//        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPwd();
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
                isYan=!isYan;
                Drawable no = getResources().getDrawable(R.drawable.yan_no);
                Drawable yes = getResources().getDrawable(R.drawable.yan_yes);
                if (isYan){
                    //显示
                    etOldPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    yan.setImageDrawable(no);

                }else {
                    etOldPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    yan.setImageDrawable(yes);
                }
            }
        });
        yan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isYan1=!isYan1;
                Drawable no = getResources().getDrawable(R.drawable.yan_no);
                Drawable yes = getResources().getDrawable(R.drawable.yan_yes);
                if (isYan1){
                    //显示
                    etNewPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    yan1.setImageDrawable(no);

                }else {
                    etNewPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    yan1.setImageDrawable(yes);
                }
            }
        });
        yan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isYan2=!isYan2;
                Drawable no = getResources().getDrawable(R.drawable.yan_no);
                Drawable yes = getResources().getDrawable(R.drawable.yan_yes);
                if (isYan2){
                    //显示
                    etRepeatPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    yan2.setImageDrawable(no);

                }else {
                    etRepeatPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    yan2.setImageDrawable(yes);
                }
            }
        });

    }

    private void sendCode() {
        tvSend.setEnabled(false);
        presenter.sendEditLoginPwdCode(getToken());
    }

    private void editPwd() {
        String oldPassword = etOldPwd.getText().toString();
        String newPassword = etNewPwd.getText().toString();
        String repeatePwd = etRepeatPwd.getText().toString();
        String code = etCode.getText().toString();
        if (WonderfulStringUtils.isEmpty(oldPassword, newPassword, repeatePwd, code)){
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.Incomplete_information)+"！");
            return;
        }
        if (!newPassword.equals(repeatePwd)){
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.pwd_diff));
            return;
        }
        presenter.editPwd(getToken(), oldPassword, newPassword, code);
    }

    @Override
    protected void obtainData() {
        phone = getIntent().getStringExtra("phone");
    }

    @Override
    protected void fillWidget() {
        tvNumber.setText(WonderfulToastUtils.getString(this,R.string.changeLoginPasswordTip1)+ phone.substring(0, 3) + "****" + phone.substring(7));
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            ImmersionBar.setTitleBar(this, llTitle);
            isSetTitle = true;
        }
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
    public void setPresenter(EditLoginPwdContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void sendEditLoginPwdCodeSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        fillCodeView(90L * 1000);
    }

    private void fillCodeView(long time) {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvSend.setText(WonderfulToastUtils.getString(EditLoginPwdActivity.this,R.string.re_send)+"(" + millisUntilFinished / 1000 + "s)");
            }

            @Override
            public void onFinish() {
                tvSend.setText(WonderfulToastUtils.getString(EditLoginPwdActivity.this,R.string.send_code));
                tvSend.setEnabled(true);
                timer.cancel();
                timer = null;
            }
        };
        timer.start();
    }

    @Override
    public void sendEditLoginPwdCodeFail(Integer code, String toastMessage) {
        tvSend.setEnabled(true);
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void editPwdSuccess(String obj) {
        WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.changeLoginPasswordTip2));
        finish();
        MyApplication.getApp().setCurrentUser(new User());
        MainActivity.actionStart(this);
//        LoginActivity.actionStart(this);
    }

    @Override
    public void editPwdFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }
}
