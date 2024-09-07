package com.bizzan.ui.signup;


import com.bizzan.data.DataSource;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/9/25.
 */

public class PhoneSignUpPresenter implements SignUpContract.PhonePresenter {
    private final DataSource dataRepository;
    private final SignUpContract.PhoneView view;

    public PhoneSignUpPresenter(DataSource dataRepository, SignUpContract.PhoneView view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void phoneCode(String phone, String country) {
        dataRepository.phoneCode(phone, country, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.phoneCodeSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.phoneCodeFail(code, toastMessage);
            }
        });
    }

    @Override
    public void signUpByPhone(String phone, String username, String password, String country, String code,String tuijianma,String challenge, String validate, String seccode) {
        view.displayLoadingPopup();
        dataRepository.signUpByPhone(phone, username, password, country, code,tuijianma, challenge,validate,seccode,new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.signUpByPhoneSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.signUpByPhoneFail(code, toastMessage);
            }
        });
    }

    @Override
    public void captch() {
        view.displayLoadingPopup();
        dataRepository.captch(new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.captchSuccess((JSONObject) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.captchFail(code, toastMessage);
            }
        });
    }

}
