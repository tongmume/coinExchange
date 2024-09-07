package com.bizzan.ui.signup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.bizzan.R;
import com.bizzan.base.BaseTransFragmentActivity;

public class SignUpActivity extends BaseTransFragmentActivity implements BaseSignUpFragment.OperateCallback {
    private PhoneSignUpFragment phoneSignUpFragment;
    private EmailSignUpFragment emailSignUpFragment;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void recoverFragment() {
        phoneSignUpFragment = (PhoneSignUpFragment) getSupportFragmentManager().findFragmentByTag(PhoneSignUpFragment.TAG);
        emailSignUpFragment = (EmailSignUpFragment) getSupportFragmentManager().findFragmentByTag(EmailSignUpFragment.TAG);
        fragments.add(phoneSignUpFragment);
        fragments.add(emailSignUpFragment);
    }

    @Override
    protected void initFragments() {
        if (phoneSignUpFragment == null) fragments.add(phoneSignUpFragment = PhoneSignUpFragment.getInstance());
        if (emailSignUpFragment == null) fragments.add(emailSignUpFragment = EmailSignUpFragment.getInstance());
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
        showFragment(phoneSignUpFragment);
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
    public void switchType(BaseSignUpFragment.Type type) {
        switch (type) {
            case PHONE:
                showFragment(phoneSignUpFragment);
                break;
            case EMAIL:
                showFragment(emailSignUpFragment);
                break;

        }
    }
}
