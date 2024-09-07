package com.bizzan.ui.myinfo;


import com.bizzan.base.Contract;
import com.bizzan.entity.SafeSetting;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface MyInfoContract {
    interface View extends Contract.BaseView<Presenter> {

        void safeSettingSuccess(SafeSetting obj);

        void safeSettingFail(Integer code, String toastMessage);

        void uploadBase64PicSuccess(String obj);

        void uploadBase64PicFail(Integer code, String toastMessage);

        void avatarSuccess(String obj);

        void avatarFail(Integer code, String toastMessage);
    }

    interface Presenter extends Contract.BasePresenter {

        void safeSetting(String token);

        void uploadBase64Pic(String token, String s);

        void avatar(String token, String url);
    }
}
