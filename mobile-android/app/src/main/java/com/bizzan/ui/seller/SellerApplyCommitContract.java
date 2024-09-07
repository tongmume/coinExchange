package com.bizzan.ui.seller;

import com.bizzan.base.Contract;
import com.bizzan.entity.DepositInfo;

import java.util.List;


public class SellerApplyCommitContract {

    interface View extends Contract.BaseView<SellerApplyCommitContract.Presenter> {

        void getDepositListSuccess(List<DepositInfo> obj);

        void getDepositListFail(String message);

        void commitSellerApplySuccess(String message);

        void commitSellerApplyFail(String message);

        void uploadBase64PicSuccess(String path, int type);

        void uploadBase64PicFail(int code, String toastMessage);

    }

    interface Presenter extends Contract.BasePresenter {

        void commitSellerApply(String token, String coinId,String json );

        void getDepositList(String token);

        void uploadBase64Pic(String token, String base64Data, final int type);
    }
}
