package com.bizzan.ui.account_pwd;


import com.bizzan.data.DataSource;

/**
 * Created by Administrator on 2017/9/25.
 */

public class AccountPwdPresenter implements AccountPwdContract.Presenter {
    private final DataSource dataRepository;
    private final AccountPwdContract.View view;

    public AccountPwdPresenter(DataSource dataRepository, AccountPwdContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void accountPwd(String token, String jyPassword) {
        view.displayLoadingPopup();
        dataRepository.accountPwd(token, jyPassword, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.accountPwdSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.accountPwdFail(code, toastMessage);

            }
        });
    }
}
