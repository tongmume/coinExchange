package com.bizzan.ui.credit;


import com.bizzan.base.Contract;
import com.bizzan.entity.Credit;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface CreditContract {
    interface View extends Contract.BaseView<Presenter> {

        void uploadBase64PicFail(Integer code, String toastMessage);

        void uploadBase64PicSuccess(String obj, int type);

        void nameSuccess(String obj);

        void nameFail(Integer code, String toastMessage);

        void getCreditInfoSuccess(Credit.DataBean obj);

        void getCreditInfoFail(Integer code, String toastMessage);
    }




    interface Presenter extends Contract.BasePresenter {

        void uploadBase64Pic(String token, String base64, int type);

        void name(String token, String realName, String idCard, String idCardFront, String idCardBack, String handHeldIdCard);

        void getCreditInfo(String token);
    }


    interface InfoView extends Contract.BaseView<InfoPresenter> {

        void commitSuccess(String result);

        void commitError(Integer code, String toastMessage );

    }

    interface InfoPresenter extends Contract.BasePresenter {

        void commitRealName(String token, String realName, String idCard, String idCardFront, String idCardBack, String handHeldIdCard);

    }

}
