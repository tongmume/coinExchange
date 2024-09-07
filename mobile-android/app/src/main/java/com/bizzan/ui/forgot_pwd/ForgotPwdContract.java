package com.bizzan.ui.forgot_pwd;


import com.bizzan.base.Contract;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface ForgotPwdContract {
    interface View extends Contract.BaseView<Presenter> {

    }

    interface Presenter extends Contract.BasePresenter {

    }

    interface PhoneView extends Contract.BaseView<PhonePresenter> {


        void phoneForgotCodeSuccess(String obj);

        void phoneForgotCodeFail(Integer code, String toastMessage);

        void forgotPwdSuccess(String obj);

        void forgotPwdFail(Integer code, String toastMessage);

        void captchSuccess(JSONObject obj);

        void captchFail(Integer code, String toastMessage);
    }

    interface PhonePresenter extends Contract.BasePresenter {

        void phoneForgotCode(String phone, String challenge, String validate, String seccode);

        void forgotPwd(String account, String code, String mode, String password);

        void capcha();
    }


    interface EmailView extends Contract.BaseView<EmailPresenter> {


        void emailForgotCodeSuccess(String obj);

        void emailForgotCodeFail(Integer code, String toastMessage);

        void forgotPwdSuccess(String obj);

        void forgotPwdFail(Integer code, String toastMessage);

        void captchSuccess(JSONObject obj);

        void captchFail(Integer code, String toastMessage);
    }

    interface EmailPresenter extends Contract.BasePresenter {

        void forgotPwd(String account, String code, String mode, String password);

        void emailForgotCode(String email, String challenge, String validate, String seccode);

        void captch();
    }

}
