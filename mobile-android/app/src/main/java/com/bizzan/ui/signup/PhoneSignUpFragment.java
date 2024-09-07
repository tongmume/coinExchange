package com.bizzan.ui.signup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bizzan.app.MyApplication;
import com.geetest.sdk.GT3GeetestUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.tencent.captchasdk.TCaptchaDialog;
import com.tencent.captchasdk.TCaptchaVerifyListener;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.bizzan.R;
import com.bizzan.app.GlobalConstant;
import com.bizzan.app.Injection;
import com.bizzan.base.BaseTransFragment;
import com.bizzan.entity.Country;
import com.bizzan.ui.country.CountryActivity;
import com.bizzan.ui.login.LoginActivity;
import com.bizzan.ui.message_detail.MessageHelpActivity;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;

/**
 * Created by Administrator on 2018/2/2.
 */

public class PhoneSignUpFragment extends BaseTransFragment implements SignUpContract.PhoneView {

    public static final String TAG = PhoneSignUpFragment.class.getSimpleName();
    public static String token = "";
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvChangeType)
    TextView tvChangeType;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
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
    @BindView(R.id.tvSignUp)
    TextView tvSignUp;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.tvToRegist)
    TextView tvToRegist;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.text_yonghu)
    TextView text_yonghu;
    @BindView(R.id.yan)
    ImageView yan;
    @BindView(R.id.tvAreaCode)
    TextView tvAreaCode;
    Unbinder unbinder;
    private boolean isYan = false;
    private boolean isYan1 = false;

    @BindView(R.id.yan1)
    ImageView yan1;
    @BindView(R.id.tuijian)
    EditText tuijian;
    private Country country;
    private CountDownTimer timer;
    private SignUpContract.PhonePresenter presenter;
    private GT3GeetestUtils gt3GeetestUtils;

    private String challenge;
    private String validate;
    private String seccode;
    private String re_send = "ReSend";
    private TCaptchaDialog dialog;
    private TCaptchaVerifyListener listener = new TCaptchaVerifyListener() {
        @Override
        public void onVerifyCallback(JSONObject jsonObject) {
            int ret = 0;
            try {
                ret = jsonObject.getInt("ret");
                if (ret == 0) {
                    //验证成功回调
                    //jsonObject.getInt("ticket")为验证码票据
                    //jsonObject.getString("appid")为appid
                    //jsonObject.getString("randstr")为随机串
                    WonderfulLogUtils.logi("miao", countryStr + "----" + phone);
                    presenter.phoneCode(phone, countryStr);
                    challenge = jsonObject.getString("ticket");
                    validate = jsonObject.getString("randstr");
                    seccode = "";
                    tvGetCode.setEnabled(false);
                } else if (ret == -1001) {
                    //验证码首个TCaptcha.js加载错误，业务可以根据需要重试
                    //jsonObject.getString("info")为错误信息
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                } else {
                    //验证失败回调，一般为用户关闭验证码弹框
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(getActivity() instanceof BaseSignUpFragment.OperateCallback)) {
            throw new RuntimeException("The Activity which fragment is located must implement the OperateCallback interface!");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        gt3GeetestUtils.destory();
        unbinder.unbind();
    }

    public static PhoneSignUpFragment getInstance() {
        PhoneSignUpFragment phoneSignUpFragment = new PhoneSignUpFragment();
        return phoneSignUpFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_phone_sign_up;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        gt3GeetestUtils = new GT3GeetestUtils(getActivity());
        new PhoneSignUpPresenter(Injection.provideTasksRepository(getActivity().getApplicationContext()), this);
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
                ((BaseSignUpFragment.OperateCallback) getActivity()).switchType(BaseSignUpFragment.Type.EMAIL);
            }
        });
        tvGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCode();
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpByPhone();
            }
        });
        tvAreaCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountryActivity.actionStart(PhoneSignUpFragment.this);
            }
        });
        tvToRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.actionStart(getActivity());
            }
        });
        text_yonghu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageHelpActivity.actionStart(getmActivity(), GlobalConstant.USER_AGREEMENT_ID);
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

    private void signUpByPhone() {
        String phone = etPhone.getText().toString();
        String username = etPhone.getText().toString();
        String code = etCode.getText().toString();
        String password = etPassword.getText().toString();
        String rePassword = etRePassword.getText().toString();
        String country = "";
        String tuijianma = tuijian.getText().toString().trim();
        if (this.country == null) country = "日本";
        else country = this.country.getZhName();
        if (WonderfulStringUtils.isEmpty(phone, code, username, password, rePassword, country)) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(getActivity(), R.string.Incomplete_information));
            return;
        }
        if (!checkbox.isChecked()) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(getActivity(), R.string.xieyi));
            return;
        }
        if (!password.equals(rePassword)) {
            WonderfulToastUtils.showToast(R.string.pwd_diff);
            return;
        }
        WonderfulLogUtils.logi("miao", challenge + "-----" + validate);
        presenter.signUpByPhone(phone, username, password, country, code, tuijianma, challenge, validate, seccode);
//        presenter.signUpByPhone(phone, username, password, country, code,tuijianma,challenge,validate,seccode);
        /*if (WonderfulStringUtils.isStandard(password,rePassword)) {
            presenter.signUpByPhone(phone, username, password, country, code);
        }else WonderfulToastUtils.showToast(R.string.pwd_diff);*/
    }

    private String countryStr;
    private String phone;

    private void getCode() {
        countryStr = "";
        phone = "";
        if (country == null) countryStr = "日本";
        else countryStr = country.getZhName();
        phone = etPhone.getText().toString();
        if (WonderfulStringUtils.isEmpty(phone) || phone.length() < 11) {
            String phone_not_correct = MyApplication.getApp().getString(R.string.phone_not_correct);
            WonderfulToastUtils.showToast(phone_not_correct);
        }
        tvGetCode.setEnabled(false);
        presenter.phoneCode(phone, countryStr);
        if (1 == 1)
            return;


//        presenter.captch();
        /**
         @param context，上下文
         @param appid，业务申请接入验证码时分配的appid
         @param listener，验证码验证结果回调
         @param jsonString，业务自定义参数
         */
        dialog = new TCaptchaDialog(getmActivity(), "2031827463", listener, null);
        dialog.show();
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CountryActivity.RETURN_COUNTRY:
                if (data == null) return;
                country = (Country) data.getSerializableExtra("country");
                tvAreaCode.setText("+" + country.getAreaCode());
                break;
        }
    }

    @Override
    public void setPresenter(SignUpContract.PhonePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void phoneCodeSuccess(String obj) {
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
    public void phoneCodeFail(Integer code, String toastMessage) {
        gt3GeetestUtils.dismissGeetestDialog();
        tvGetCode.setEnabled(true);
        WonderfulCodeUtils.checkedErrorCode(getmActivity(), code, toastMessage);
    }

    @Override
    public void signUpByPhoneSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        finish();

    }

    @Override
    public void signUpByPhoneFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(getmActivity(), code, toastMessage);
    }

    @Override
    public void captchSuccess(JSONObject obj) {

//        gt3GeetestUtils.gtSetApi1Json(obj);
//        gt3GeetestUtils.getGeetest(getActivity(), UrlFactory.getCaptchaUrl(), null, null, new GT3GeetestBindListener() {
//            @Override
//            public boolean gt3SetIsCustom() {
//                return true;
//            }
//
//            @Override
//            public void gt3GetDialogResult(boolean status, String result) {
//
//                if (status) {
//                    Captcha captcha = new Gson().fromJson(result, Captcha.class);
//                    if (captcha == null) return;
//                     challenge = captcha.getchallenge();
//                     validate = captcha.getvalidate();
//                     seccode = captcha.getseccode();
//                    presenter.phoneCode(phone, countryStr, challenge, validate, seccode);
//                    tvGetCode.setEnabled(false);
//
//                }
//            }
//        });
//        gt3GeetestUtils.setDialogTouch(true);

    }

    @Override
    public void captchFail(Integer code, String toastMessage) {
        // do nothing

    }

    @Override
    protected String getmTag() {
        return TAG;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
