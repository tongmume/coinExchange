package com.bizzan.ui.bind_phone;


import com.bizzan.data.DataSource;

/**
 * Created by Administrator on 2017/9/25.
 */

public class BindPhonePresenter implements BindPhoneContract.Presenter {
    private final DataSource dataRepository;
    private final BindPhoneContract.View view;

    public BindPhonePresenter(DataSource dataRepository, BindPhoneContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void bindPhone(String token, String phone, String code, String passwrd) {
        view.displayLoadingPopup();
        dataRepository.bindPhone(token, phone, code, passwrd, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.bindPhoneSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.bindPhoneFail(code, toastMessage);

            }
        });
    }

    @Override
    public void sendCode(String token, String phone) {
        view.displayLoadingPopup();
        dataRepository.sendCode(token, phone, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.sendCodeSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.sendCodeFail(code, toastMessage);

            }
        });
    }
}
