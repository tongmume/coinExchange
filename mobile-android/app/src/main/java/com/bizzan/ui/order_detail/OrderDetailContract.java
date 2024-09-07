package com.bizzan.ui.order_detail;


import com.bizzan.base.Contract;
import com.bizzan.entity.OrderDetial;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface OrderDetailContract {
    interface View extends Contract.BaseView<Presenter> {

        void orderDetailSuccess(OrderDetial obj);

        void orderDetaileFail(Integer code, String toastMessage);

        void payDoneSuccess(String obj);

        void payDoneFail(Integer code, String toastMessage);

        void cancleSuccess(String obj);

        void cancleFail(Integer code, String toastMessage);

        void releaseSuccess(String obj);

        void releaseFail(Integer code, String toastMessage);
    }

    interface Presenter extends Contract.BasePresenter {
        void orderDetail(String token, String orderSn);

        void payDone(String token, String orderSn);

        void cancle(String token, String orderSn);

        void release(String token, String orderSn, String jyPassword);
    }
}
