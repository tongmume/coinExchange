package com.bizzan.ui.login;


import com.bizzan.data.DataSource;
import com.bizzan.entity.User;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/9/25.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private final DataSource dataRepository;
    private final LoginContract.View view;

    public LoginPresenter(DataSource dataRepository, LoginContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }
    
    @Override
    public void login(String username, String password, String challenge, String validate, String seccode) {
        view.displayLoadingPopup();
        dataRepository.login(username, password, challenge, validate, seccode, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.loginSuccess((User) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.loginFail(code, toastMessage);
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
