package com.bizzan.ui.forgot_pwd;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bizzan.R;
import com.bizzan.app.Injection;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseTransFragment;
import com.bizzan.entity.Captcha;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;
import com.geetest.sdk.GT3ConfigBean;
import com.geetest.sdk.GT3ErrorBean;
import com.geetest.sdk.GT3GeetestUtils;
import com.geetest.sdk.GT3Listener;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;

import org.json.JSONObject;

import butterknife.BindView;

public class PhoneForgotFragment extends BaseTransFragment implements ForgotPwdContract.PhoneView {
    public static final String TAG = PhoneForgotFragment.class.getSimpleName();
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvChangeType)
    TextView tvChangeType;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etRePassword)
    EditText etRePassword;
    @BindView(R.id.tvBack)
    TextView tvBack;
    @BindView(R.id.tvGetCode)
    TextView tvGetCode;
    @BindView(R.id.tvSubmit)
    TextView tvSubmit;
    @BindView(R.id.yan)
    ImageView yan;
    private boolean isYan = false;
    private boolean isYan1 = false;

    @BindView(R.id.yan1)
    ImageView yan1;

    private CountDownTimer timer;
    private ForgotPwdContract.PhonePresenter presenter;
    private GT3GeetestUtils gt3GeetestUtils;
    private String re_send = "ReSend";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(getActivity() instanceof BaseForgotFragment.OperateCallback)) {
            throw new RuntimeException("fragment所在的Activity必须实现OperateCallback接口！");
        }
    }

    public static PhoneForgotFragment getInstance() {
        PhoneForgotFragment phoneForgotFragment = new PhoneForgotFragment();
        return phoneForgotFragment;
    }

    @Override
    public void onDestroyView() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        super.onDestroyView();
        gt3GeetestUtils.destory();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_phone_forgot;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        gt3GeetestUtils = new GT3GeetestUtils(getActivity());
        new PhoneForgotPresenter(Injection.provideTasksRepository(getActivity().getApplicationContext()), this);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvChangeType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseForgotFragment.OperateCallback) getActivity()).switchType(BaseForgotFragment.Type.EMAIL);
            }
        });
        tvGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCode();
            }
        });
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
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
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    yan.setImageDrawable(no);

                } else {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
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
                    etRePassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    yan1.setImageDrawable(no);

                } else {
                    etRePassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    yan1.setImageDrawable(yes);
                }
            }
        });
        re_send = MyApplication.getApp().getString(R.string.re_send);
    }

    private void submit() {
        String account = etUsername.getText().toString();
        String code = etCode.getText().toString();
        String mode = "0";
        String password = etPassword.getText().toString();
        String passwordRe = etRePassword.getText().toString();

        if (WonderfulStringUtils.isEmpty(account, code, mode, password, passwordRe)) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(getActivity(), R.string.tv_message_deficiency));
            return;
        }
        if (!password.equals(passwordRe)) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(getActivity(), R.string.pwd_diff));
            return;
        }
        presenter.forgotPwd(account, code, mode, password);
    }

    private void getCode() {
        String phone = etUsername.getText().toString();
        if (WonderfulStringUtils.isEmpty(phone) || phone.length() < 11) {
            String phone_not_correct = MyApplication.getApp().getString(R.string.phone_not_correct);
            WonderfulToastUtils.showToast(phone_not_correct);
            gt3GeetestUtils.dismissGeetestDialog();
            return;
        }
        tvGetCode.setEnabled(false);
        presenter.phoneForgotCode(phone, "", "", "");
//        presenter.capcha();
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
            ImmersionBar.setTitleBar(getActivity(), llTitle);
            isSetTitle = true;
        }
    }

    @Override
    public void setPresenter(ForgotPwdContract.PhonePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void phoneForgotCodeSuccess(String obj) {
        gt3GeetestUtils.showSuccessDialog();
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
                tvGetCode.setText(re_send + "（" + millisUntilFinished / 1000 + "）");
            }

            @Override
            public void onFinish() {
                tvGetCode.setText(R.string.send_code);
                tvGetCode.setEnabled(true);
                timer.cancel();
                timer = null;
            }
        };
        timer.start();
    }

    @Override
    public void phoneForgotCodeFail(Integer code, String toastMessage) {
        gt3GeetestUtils.dismissGeetestDialog();
        tvGetCode.setEnabled(true);
        WonderfulCodeUtils.checkedErrorCode(getmActivity(), code, toastMessage);
    }

    @Override
    public void forgotPwdSuccess(String obj) {

        WonderfulToastUtils.showToast(obj);
        finish();
    }

    @Override
    public void forgotPwdFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(getmActivity(), code, toastMessage);
    }

    @Override
    public void captchSuccess(JSONObject obj) {
        GT3ConfigBean gt3ConfigBean = new GT3ConfigBean();
        gt3ConfigBean.setPattern(1);
        // 设置点击灰色区域是否消失，默认不消失
        gt3ConfigBean.setCanceledOnTouchOutside(true);
        int code = SharedPreferenceInstance.getInstance().getLanguageCode();
        String language = "en";
        switch (code) {
            case -1:
                language = "zh-hk";
                break;
            case 1:
                language = "zh";
                break;
            case 3:
                language = "ja";
                break;
            case 4:
                language = "ko";
                break;
            case 5:
                language = "de";
                break;
            case 6:
                language = "fr";
                break;
        }
        gt3ConfigBean.setLang(language);
        gt3ConfigBean.setApi1Json(obj);
        gt3ConfigBean.setListener(new GT3Listener() {
            @Override
            public void onReceiveCaptchaCode(int i) {

            }

            @Override
            public void onStatistics(String result) {
                Log.d("chenxi", "onStatistics：" + result);
            }

            @Override
            public void onClosed(int i) {

            }

            @Override
            public void onSuccess(String result) {
                Log.d("chenxi", "onSuccess：" + result);
            }

            @Override
            public void onFailed(GT3ErrorBean gt3ErrorBean) {
                Log.d("chenxi", "onFailed：" + gt3ErrorBean.errorDesc);
            }

            @Override
            public void onButtonClick() {
                Log.d("chenxi", "button被点击，验证开始");
            }

            /**
             * 自定义api2回调
             * @param result，api2请求上传参数
             */
            @Override
            public void onDialogResult(String result) {
                Captcha captcha = new Gson().fromJson(result, Captcha.class);
                if (captcha == null) return;
                String challenge = captcha.getGeetest_challenge();
                String validate = captcha.getGeetest_validate();
                String seccode = captcha.getGeetest_seccode();
                String phone = etUsername.getText().toString();
                presenter.phoneForgotCode(phone, challenge, validate, seccode);
                tvGetCode.setEnabled(false);
            }

        });
        gt3GeetestUtils.init(gt3ConfigBean);
        // 开启验证
        gt3GeetestUtils.startCustomFlow();
        //继续验证
        gt3GeetestUtils.getGeetest();
    }

    @Override
    public void captchFail(Integer code, String toastMessage) {

    }

    @Override
    protected String getmTag() {
        return TAG;
    }
}
