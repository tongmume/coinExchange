package com.bizzan.ui.forgot_pwd;


import com.bizzan.data.DataSource;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/9/25.
 */

public class PhoneForgotPresenter implements ForgotPwdContract.PhonePresenter {
    private final DataSource dataRepository;
    private final ForgotPwdContract.PhoneView view;

    public PhoneForgotPresenter(DataSource dataRepository, ForgotPwdContract.PhoneView view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void phoneForgotCode(String phone, String challenge, String validate, String seccode) {
        view.displayLoadingPopup();
        dataRepository.phoneForgotCode(phone, challenge, validate, seccode, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.phoneForgotCodeSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.phoneForgotCodeFail(code, toastMessage);
            }
        });
    }

    @Override
    public void forgotPwd(String account, String code, String mode, String password) {
        view.displayLoadingPopup();
        dataRepository.forgotPwd(account, code, mode, password, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.forgotPwdSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.forgotPwdFail(code, toastMessage);

            }
        });
    }

    @Override
    public void capcha() {
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
