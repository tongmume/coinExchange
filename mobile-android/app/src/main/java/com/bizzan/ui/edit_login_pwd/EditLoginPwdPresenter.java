package com.bizzan.ui.edit_login_pwd;


import com.bizzan.data.DataSource;

/**
 * Created by Administrator on 2017/9/25.
 */

public class EditLoginPwdPresenter implements EditLoginPwdContract.Presenter {
    private final DataSource dataRepository;
    private final EditLoginPwdContract.View view;

    public EditLoginPwdPresenter(DataSource dataRepository, EditLoginPwdContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void sendEditLoginPwdCode(String token) {
        view.displayLoadingPopup();
        dataRepository.sendEditLoginPwdCode(token, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.sendEditLoginPwdCodeSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.sendEditLoginPwdCodeFail(code, toastMessage);

            }
        });
    }

    @Override
    public void editPwd(String token, String oldPassword, String newPassword, String code) {
        view.displayLoadingPopup();
        dataRepository.editPwd(token, oldPassword, newPassword, code, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.editPwdSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.editPwdFail(code, toastMessage);

            }
        });
    }
}
