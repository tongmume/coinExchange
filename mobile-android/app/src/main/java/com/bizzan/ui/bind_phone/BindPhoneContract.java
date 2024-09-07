package com.bizzan.ui.bind_phone;


import com.bizzan.base.Contract;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface BindPhoneContract {
    interface View extends Contract.BaseView<Presenter> {

        void bindPhoneSuccess(String obj);

        void bindPhoneFail(Integer code, String toastMessage);

        void sendCodeSuccess(String obj);

        void sendCodeFail(Integer code, String toastMessage);
    }

    interface Presenter extends Contract.BasePresenter {
        void bindPhone(String token, String s, String code, String code1);

        void sendCode(String token, String phone);
    }
}
