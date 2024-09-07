package com.bizzan.ui.my_order;


import com.bizzan.data.DataSource;
import com.bizzan.entity.Order;

import java.util.List;

/**
 * Created by Administrator on 2017/9/25.
 */

public class OrderPresenter implements OrderContract.Presenter {
    private final DataSource dataRepository;
    private final OrderContract.View view;

    public OrderPresenter(DataSource dataRepository, OrderContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void myOrder(String token, int status, int pageNo, int pageSize) {
        view.displayLoadingPopup();
        dataRepository.myOrder(token, status, pageNo, pageSize, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.myOrderSuccess((List<Order>) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.myOrderFail(code, toastMessage);

            }
        });
    }
}
