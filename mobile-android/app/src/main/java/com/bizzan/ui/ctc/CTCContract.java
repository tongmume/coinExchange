package com.bizzan.ui.ctc;

import java.math.BigDecimal;
import java.util.List;

import com.bizzan.base.Contract;
import com.bizzan.entity.CTCOrder;
import com.bizzan.entity.CTCOrderDetail;
import com.bizzan.entity.CTCPrice;
import com.bizzan.entity.SafeSetting;

public interface CTCContract {
    interface View extends Contract.BaseView<Presenter> {
        void safeSettingSuccess(SafeSetting obj);
        void safeSettingFail(Integer code, String toastMessage);

        void ctcOrderListFail(Integer code, String toastMessage);
        void ctcOrderListSuccess(CTCOrder obj);

        void ctcOrderDetailFail(Integer code, String toastMessage);
        void ctcOrderDetailSuccess(CTCOrderDetail obj);

        void ctcOrderPayFail(Integer code, String toastMessage);
        void ctcOrderPaySuccess(CTCOrderDetail obj);

        void ctcOrderCancelFail(Integer code, String toastMessage);
        void ctcOrderCancelSuccess(CTCOrderDetail obj);

        void ctcPriceFail(Integer code, String toastMessage);
        void ctcPriceSuccess(CTCPrice obj);

        void ctcNewOrderFail(Integer code, String toastMessage);
        void ctcNewOrderSuccess(CTCOrderDetail obj);

        void ctcSendNewOrderPhoneCodeFail(Integer code, String toastMessage);
        void ctcSendNewOrderPhoneCodeSuccess(String obj);
    }

    interface Presenter extends Contract.BasePresenter {
        void safeSetting(String token);
        void ctcOrderList(String token, int pageNo, int pageSize);
        void ctcOrderDetail(String token, Long oid);

        void ctcOrderPay(String token, Long oid);
        void ctcOrderCancel(String token, Long oid);

        void ctcPrice();
        void ctcNewOrder(String token, BigDecimal price, BigDecimal amount, String payType, int direction, String unit, String fundpwd, String code);

        void ctcSendNewOrderPhoneCode(String token);
    }
}
