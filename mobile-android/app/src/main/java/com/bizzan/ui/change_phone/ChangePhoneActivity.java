package com.bizzan.ui.change_phone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bizzan.app.MyApplication;
import com.bizzan.utils.SharedPreferenceInstance;
import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.ui.myinfo.MyInfoActivity;
import com.bizzan.base.BaseActivity;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;

import butterknife.BindView;

import com.bizzan.app.Injection;

public class ChangePhoneActivity extends BaseActivity implements ChangePhoneContract.View {

    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.tvSend)
    TextView tvSend;
    @BindView(R.id.tvAreaCode)
    TextView tvAreaCode;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.tvSubmit)
    TextView tvSubmit;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    private CountDownTimer timer;
    private ChangePhoneContract.Presenter presenter;
    private String phone;
    @BindView(R.id.view_back)
    View view_back;
    private String re_send = "ReSend";

    public static void actionStart(Context context, String phone) {
        Intent intent = new Intent(context, ChangePhoneActivity.class);
        intent.putExtra("phone", phone);
        context.startActivity(intent);
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
        return R.layout.activity_change_phone;
    }


    @Override
    protected void initViews(Bundle savedInstanceState) {
        new ChangePhonePresenter(Injection.provideTasksRepository(getApplicationContext()), this);
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
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        re_send = MyApplication.getApp().getString(R.string.re_send);

    }

    private void submit() {
        String password = etPwd.getText().toString();
        String phone = etPhone.getText().toString();
        String code = etCode.getText().toString();
        if (WonderfulStringUtils.isEmpty(password, phone, code)) return;
        presenter.changePhone(getToken(), password, phone, code);
    }

    private void sendCode() {
        presenter.sendChangePhoneCode(getToken());
    }

    @Override
    protected void obtainData() {
        phone = getIntent().getStringExtra("phone");
        tvPhone.setText(WonderfulToastUtils.getString(this, R.string.handsetTailNumber) + phone.substring(phone.length() - 4) + WonderfulToastUtils.getString(this, R.string.receiveMessageCode));
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
    public void setPresenter(ChangePhoneContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void sendChangePhoneCodeSuccess(String obj) {
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
                tvSend.setEnabled(false);
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
    public void sendChangePhoneCodeFail(Integer code, String toastMessage) {
        tvSend.setEnabled(true);
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void changePhoneSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        MyInfoActivity.actionStart(this);
    }

    @Override
    public void changePhoneFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
