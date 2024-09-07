package com.bizzan.ui.bind_email;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.base.BaseActivity;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;

import butterknife.BindView;
import com.bizzan.app.Injection;

public class BindEmailActivity extends BaseActivity implements BindEmailContract.View {

    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.tvSend)
    TextView tvSend;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.tvBind)
    TextView tvBind;
    @BindView(R.id.view_back)
    View view_back;

    private BindEmailContract.Presenter presenter;
    private CountDownTimer timer;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, BindEmailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_bind_email;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        new BindEmailPresenter(Injection.provideTasksRepository(getApplicationContext()), this);
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
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCode();
            }
        });
        tvBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindEmail();
            }
        });
    }

    private void sendCode() {
        String email = etEmail.getText().toString();
        if (WonderfulStringUtils.isEmpty(email)) return;
        presenter.sendEmailCode(getToken(), /*tvAreaCode.getText().toString() +*/ email);
        tvSend.setEnabled(false);
    }

    private void bindEmail() {
        String password = etPwd.getText().toString();
        String phone = etEmail.getText().toString();
        String code = etCode.getText().toString();
        if (WonderfulStringUtils.isEmpty(password, phone, code)) return;
        presenter.bindEmail(getToken(), /*tvAreaCode.getText().toString() + "" + */phone, code, password);
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
    public void setPresenter(BindEmailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void bindEmailSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        finish();
    }

    @Override
    public void bindEmailFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void sendEmailCodeSuccess(String obj) {
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
                tvSend.setText(WonderfulToastUtils.getString(BindEmailActivity.this,R.string.re_send)+"(" + millisUntilFinished / 1000 + "s)");
            }

            @Override
            public void onFinish() {
                tvSend.setText(WonderfulToastUtils.getString(BindEmailActivity.this,R.string.send_code));
                tvSend.setEnabled(true);
                timer.cancel();
                timer = null;
            }
        };
        timer.start();
    }

    @Override
    public void sendEmailCodeFail(Integer code, String toastMessage) {
        tvSend.setEnabled(true);
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

}
