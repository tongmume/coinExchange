package com.bizzan.ui.signup;


import com.bizzan.data.DataSource;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/9/25.
 */

public class EmailSignUpPresenter implements SignUpContract.EmailPresenter {
    private final DataSource dataRepository;
    private final SignUpContract.EmailView view;

    public EmailSignUpPresenter(DataSource dataRepository, SignUpContract.EmailView view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }


    @Override
    public void signUpByEmail(String email, String password,String tuijian2, String country, String code, String validate, String seccode) {
        view.displayLoadingPopup();
        dataRepository.signUpByEmail(email,  password, tuijian2,country,code,validate,seccode, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.signUpByEmailSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.signUpByEmailFail(code, toastMessage);
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

    @Override
    public void EmailCode(String email) {
        dataRepository.emailCode(email, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.emailCodeSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.emailCodeFail(code, toastMessage);
            }
        });
    }

}
