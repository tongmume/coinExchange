package com.bizzan.ui.forgot_pwd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.bizzan.R;
import com.bizzan.base.BaseTransFragmentActivity;

public class ForgotPwdActivity extends BaseTransFragmentActivity implements BaseForgotFragment.OperateCallback {
    private PhoneForgotFragment phoneForgotFragment;
    private EmailForgotFragment emailForgotFragment;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ForgotPwdActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void recoverFragment() {
        phoneForgotFragment = (PhoneForgotFragment) getSupportFragmentManager().findFragmentByTag(PhoneForgotFragment.TAG);
        emailForgotFragment = (EmailForgotFragment) getSupportFragmentManager().findFragmentByTag(EmailForgotFragment.TAG);
        fragments.add(phoneForgotFragment);
        fragments.add(emailForgotFragment);
    }

    @Override
    protected void initFragments() {
        if (phoneForgotFragment == null) fragments.add(phoneForgotFragment = PhoneForgotFragment.getInstance());
        if (emailForgotFragment == null) fragments.add(emailForgotFragment = EmailForgotFragment.getInstance());
    }

    @Override
    public int getContainerId() {
        return R.id.flContainer;
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showFragment(phoneForgotFragment);
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
    public void switchType(BaseForgotFragment.Type type) {
        switch (type) {
            case PHONE:
                showFragment(phoneForgotFragment);
                break;
            case EMAIL:
                showFragment(emailForgotFragment);
                break;
        }
    }
}
