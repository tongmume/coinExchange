package com.bizzan.ui.account_pwd;


import com.bizzan.data.DataSource;

/**
 * Created by Administrator on 2017/9/25.
 */

public class ResetAccountPwdPresenter implements AccountPwdContract.ResetPresenter {
    private final DataSource dataRepository;
    private final AccountPwdContract.ResetView view;

    public ResetAccountPwdPresenter(DataSource dataRepository, AccountPwdContract.ResetView view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void resetAccountPwd(String token, String newPassword, String code) {
        view.displayLoadingPopup();
        dataRepository.resetAccountPwd(token, newPassword, code, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.resetAccountPwdSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.resetAccountPwdFail(code, toastMessage);

            }
        });
    }

    @Override
    public void resetAccountPwdCode(String token) {
        view.displayLoadingPopup();
        dataRepository.resetAccountPwdCode(token, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.resetAccountPwdCodeSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.resetAccountPwdCodeFail(code, toastMessage);

            }
        });
    }
}
