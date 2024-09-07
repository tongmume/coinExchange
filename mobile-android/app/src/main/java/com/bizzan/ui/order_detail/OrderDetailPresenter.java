package com.bizzan.ui.order_detail;


import com.bizzan.data.DataSource;
import com.bizzan.entity.OrderDetial;

/**
 * Created by Administrator on 2017/9/25.
 */

public class OrderDetailPresenter implements OrderDetailContract.Presenter {
    private final DataSource dataRepository;
    private final OrderDetailContract.View view;

    public OrderDetailPresenter(DataSource dataRepository, OrderDetailContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void orderDetail(String token, String orderSn) {
        view.displayLoadingPopup();
        dataRepository.orderDetail(token, orderSn, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.orderDetailSuccess((OrderDetial) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.orderDetaileFail(code, toastMessage);
            }
        });
    }

    @Override
    public void payDone(String token, String orderSn) {
        view.displayLoadingPopup();
        dataRepository.payDone(token, orderSn, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.payDoneSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.payDoneFail(code, toastMessage);
            }
        });
    }

    @Override
    public void cancle(String token, String orderSn) {
        view.displayLoadingPopup();
        dataRepository.cancle(token, orderSn, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.cancleSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.cancleFail(code, toastMessage);

            }
        });
    }

    @Override
    public void release(String token, String orderSn, String jyPassword) {
        view.displayLoadingPopup();
        dataRepository.releaseOrder(token, orderSn,jyPassword, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.releaseSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.releaseFail(code, toastMessage);

            }
        });
    }

}
