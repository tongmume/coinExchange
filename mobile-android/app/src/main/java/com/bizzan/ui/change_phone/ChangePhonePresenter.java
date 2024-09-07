package com.bizzan.ui.change_phone;


import com.bizzan.data.DataSource;

/**
 * Created by Administrator on 2017/9/25.
 */

public class ChangePhonePresenter implements ChangePhoneContract.Presenter {
    private final DataSource dataRepository;
    private final ChangePhoneContract.View view;

    public ChangePhonePresenter(DataSource dataRepository, ChangePhoneContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void sendChangePhoneCode(String token) {
        view.displayLoadingPopup();
        dataRepository.sendChangePhoneCode(token, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.sendChangePhoneCodeSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.sendChangePhoneCodeFail(code, toastMessage);
            }
        });
    }

    @Override
    public void changePhone(String token, String password, String phone, String code) {
        view.displayLoadingPopup();
        dataRepository.changePhone(token, password, phone, code, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.changePhoneSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.changePhoneFail(code, toastMessage);

            }
        });
    }
}
