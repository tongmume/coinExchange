package com.bizzan.ui.account_pwd;


import com.bizzan.base.Contract;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface AccountPwdContract {
    interface View extends Contract.BaseView<Presenter> {

        void accountPwdSuccess(String obj);

        void accountPwdFail(Integer code, String toastMessage);
    }

    interface Presenter extends Contract.BasePresenter {
        void accountPwd(String token, String jyPassword);
    }

    interface EditView extends Contract.BaseView<EditPresenter> {

        void editAccountPedSuccess(String obj);

        void editAccountPedFail(Integer code, String toastMessage);
    }

    interface EditPresenter extends Contract.BasePresenter {

        void editAccountPed(String token, String newPassword, String oldPassword);
    }


    interface ResetView extends Contract.BaseView<ResetPresenter> {

        void resetAccountPwdSuccess(String obj);

        void resetAccountPwdFail(Integer code, String toastMessage);

        void resetAccountPwdCodeSuccess(String obj);

        void resetAccountPwdCodeFail(Integer code, String toastMessage);
    }

    interface ResetPresenter extends Contract.BasePresenter {

        void resetAccountPwd(String token, String newPassword, String code);

        void resetAccountPwdCode(String token);
    }

}
