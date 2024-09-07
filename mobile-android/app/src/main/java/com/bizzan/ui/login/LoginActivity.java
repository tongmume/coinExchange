package com.bizzan.ui.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bizzan.R;
import com.bizzan.app.Injection;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.Captcha;
import com.bizzan.entity.User;
import com.bizzan.ui.forgot_pwd.ForgotPwdActivity;
import com.bizzan.ui.home.MainActivity;
import com.bizzan.ui.setting.LanguageActivity;
import com.bizzan.ui.signup.SignUpActivity;
import com.bizzan.utils.EncryUtils;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulCommonUtils;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;
import com.geetest.sdk.GT3ConfigBean;
import com.geetest.sdk.GT3ErrorBean;
import com.geetest.sdk.GT3GeetestUtils;
import com.geetest.sdk.GT3Listener;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements LoginContract.View {
    public static final int RETURN_LOGIN = 0;
    @BindView(R.id.ibBack)
    TextView ibBack;
    @BindView(R.id.ibRegist)
    TextView ibRegist;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.tvLogin)
    TextView tvLogin;
    @BindView(R.id.tvForgetPas)
    TextView tvForgetPas;
    @BindView(R.id.tvToRegist)
    TextView tvToRegist;
    @BindView(R.id.yan)
    ImageView yan;
    @BindView(R.id.llLanguage)
    View llLanguage;

    private boolean isYan = false;
    private LoginContract.Presenter presenter;
    private GT3GeetestUtils gt3GeetestUtils;
    private Handler handler = new Handler();

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        gt3GeetestUtils.destory();
        super.onDestroy();
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        gt3GeetestUtils = new GT3GeetestUtils(this);


        new LoginPresenter(Injection.provideTasksRepository(getApplicationContext()), this);
        llLanguage.setOnClickListener(view -> {
            LanguageActivity.actionStart(LoginActivity.this);
        });
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvToRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpActivity.actionStart(LoginActivity.this);
            }
        });
        ibRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpActivity.actionStart(LoginActivity.this);
            }
        });
        tvForgetPas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgotPwdActivity.actionStart(LoginActivity.this);
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
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
    }

    private void login() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if (WonderfulStringUtils.isEmpty(username, password)) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this, R.string.input_account_password));
            return;
        }
        presenter.login(username, password, "", "", "");
//        presenter.captch();
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
    public void setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loginFail(Integer code, String toastMessage) {
        gt3GeetestUtils.dismissGeetestDialog();
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void loginSuccess(User obj) {
        MyApplication.getApp().setCurrentUser(null);
        MainActivity.isAgain = true;
        String key = WonderfulCommonUtils.getSerialNumber() + etUsername.getText().toString() + etPassword.getText().toString();
        String md5Key = getMD5(key);
        SharedPreferenceInstance.getInstance().saveToken(EncryUtils.getInstance().encryptString(md5Key, MyApplication.getApp().getPackageName()));
        MyApplication.getApp().setLoginStatusChange(true);
        gt3GeetestUtils.showSuccessDialog();
        SharedPreferenceInstance.getInstance().saveLockPwd("");
        MyApplication.getApp().setCurrentUser(obj);
        SharedPreferenceInstance.getInstance().saveID(obj.getId());
        SharedPreferenceInstance.getInstance().saveTOKEN(obj.getToken());
        SharedPreferenceInstance.getInstance().saveaToken(EncryUtils.getInstance().decryptString(SharedPreferenceInstance.getInstance().getToken(), MyApplication.getApp().getPackageName()));
        setResult(RESULT_OK);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 600);
    }

    public String getMD5(String info) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();
            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++) {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                } else {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }
            return strBuf.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        }
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
                WonderfulLogUtils.logi("LoginActivity", "challenge  " + challenge + "   validate   " + validate + "   seccode   " + seccode);
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                presenter.login(username, password, challenge, validate, seccode);
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
        //do nothing
    }
}
