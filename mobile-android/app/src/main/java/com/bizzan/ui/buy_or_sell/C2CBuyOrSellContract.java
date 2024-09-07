package com.bizzan.ui.buy_or_sell;


import com.bizzan.base.Contract;
import com.bizzan.entity.C2CExchangeInfo;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface C2CBuyOrSellContract {
    interface View extends Contract.BaseView<Presenter> {

        void c2cInfoSuccess(C2CExchangeInfo obj);

        void c2cInfoFail(Integer code, String toastMessage);

        void c2cBuySuccess(String obj);

        void c2cBuyFail(Integer code, String toastMessage);

        void c2cSellSuccess(String obj);

        void c2cSellFail(Integer code, String toastMessage);
    }

    interface Presenter extends Contract.BasePresenter {
        void c2cInfo( int advertiseId);

        void c2cBuy(String token, String id, String coinId, String price, String money, String amount, String remark,String mode);

        void c2cSell(String token, String id, String coinId, String price, String money, String amount, String remark,String mode);
    }
}
