package com.bizzan.ui.edit_login_pwd;


import com.bizzan.base.Contract;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface EditLoginPwdContract {
    interface View extends Contract.BaseView<Presenter> {

        void sendEditLoginPwdCodeSuccess(String obj);

        void sendEditLoginPwdCodeFail(Integer code, String toastMessage);

        void editPwdSuccess(String obj);

        void editPwdFail(Integer code, String toastMessage);
    }

    interface Presenter extends Contract.BasePresenter {

        void sendEditLoginPwdCode(String token);

        void editPwd(String token, String oldPassword, String newPassword, String code);
    }
}
