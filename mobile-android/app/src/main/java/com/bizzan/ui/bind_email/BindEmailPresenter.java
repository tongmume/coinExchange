package com.bizzan.ui.bind_email;


import com.bizzan.data.DataSource;

/**
 * Created by Administrator on 2017/9/25.
 */

public class BindEmailPresenter implements BindEmailContract.Presenter {
    private final DataSource dataRepository;
    private final BindEmailContract.View view;

    public BindEmailPresenter(DataSource dataRepository, BindEmailContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void bindEmail(String token, String email, String code, String passwrd) {
        view.displayLoadingPopup();
        dataRepository.bindEmail(token, email, code, passwrd, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.bindEmailSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.bindEmailFail(code, toastMessage);

            }
        });
    }

    @Override
    public void sendEmailCode(String token, String email) {
        view.displayLoadingPopup();
        dataRepository.sendEmailCode(token, email, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.sendEmailCodeSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.sendEmailCodeFail(code, toastMessage);

            }
        });
    }

}
