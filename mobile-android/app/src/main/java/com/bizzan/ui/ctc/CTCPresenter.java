package com.bizzan.ui.ctc;

import com.bizzan.data.DataSource;
import com.bizzan.entity.CTCOrder;
import com.bizzan.entity.CTCOrderDetail;
import com.bizzan.entity.CTCPrice;
import com.bizzan.entity.SafeSetting;

import java.math.BigDecimal;
import java.util.List;

public class CTCPresenter  implements CTCContract.Presenter {
    private final DataSource dataRepository;
    private final CTCContract.View view;

    public CTCPresenter(DataSource dataRepository, CTCContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void safeSetting(String token) {
        view.displayLoadingPopup();
        dataRepository.safeSetting(token, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.safeSettingSuccess((SafeSetting) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.safeSettingFail(code, toastMessage);
            }
        });
    }

    @Override
    public void ctcOrderList(String token, int pageNo, int pageSize) {
        dataRepository.ctcOrderList(token, pageNo, pageSize, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.ctcOrderListSuccess((CTCOrder) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.ctcOrderListFail(code, toastMessage);
            }
        });
    }

    @Override
    public void ctcOrderDetail(String token, Long oid) {
        view.displayLoadingPopup();
        dataRepository.ctcOrderDetail(token, oid, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.ctcOrderDetailSuccess((CTCOrderDetail) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.ctcOrderDetailFail(code, toastMessage);
            }
        });
    }

    @Override
    public void ctcOrderPay(String token, Long oid) {
        view.displayLoadingPopup();
        dataRepository.ctcOrderPay(token, oid, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.ctcOrderPaySuccess((CTCOrderDetail) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.ctcOrderPayFail(code, toastMessage);
            }
        });
    }

    @Override
    public void ctcOrderCancel(String token, Long oid) {
        view.displayLoadingPopup();
        dataRepository.ctcOrderCancel(token, oid, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.ctcOrderCancelSuccess((CTCOrderDetail) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.ctcOrderCancelFail(code, toastMessage);
            }
        });
    }

    @Override
    public void ctcPrice() {
        dataRepository.ctcPrice(new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.ctcPriceSuccess((CTCPrice) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.ctcPriceFail(code, toastMessage);
            }
        });
    }

    @Override
    public void ctcNewOrder(String token, BigDecimal price, BigDecimal amount, String payType, int direction, String unit, String fundpwd, String code) {
        view.displayLoadingPopup();
        dataRepository.ctcNewOrder(token, price, amount, payType, direction, unit, fundpwd, code, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.ctcNewOrderSuccess((CTCOrderDetail) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.ctcNewOrderFail(code, toastMessage);
            }
        });
    }

    @Override
    public void ctcSendNewOrderPhoneCode(String token) {
        view.displayLoadingPopup();
        dataRepository.ctcSendNewOrderPhoneCode(token, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.ctcSendNewOrderPhoneCodeSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.ctcSendNewOrderPhoneCodeFail(code, toastMessage);
            }
        });
    }

}
