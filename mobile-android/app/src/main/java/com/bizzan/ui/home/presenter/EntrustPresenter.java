package com.bizzan.ui.home.presenter;

import com.bizzan.ui.home.MainContract;
import com.bizzan.data.DataSource;
import com.bizzan.entity.EntrustHistory;

import java.util.List;

/**
 * Created by Administrator on 2018/3/12.
 */

public class EntrustPresenter implements MainContract.EntrustPresenter {
    private MainContract.EntrustView view;
    private DataSource dataRepository;

    public EntrustPresenter(DataSource dataRepository, MainContract.EntrustView view) {
        this.view = view;
        this.dataRepository = dataRepository;
        this.view.setPresenter(this);
    }

    @Override
    public void entrust(String token, int pageSize, int pageNo, String symbol) {
        view.displayLoadingPopup();
        dataRepository.entrust(token, pageSize, pageNo, symbol, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.entrustSuccess((List<EntrustHistory>) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.entrustFail(code, toastMessage);

            }
        });
    }

    @Override
    public void cancleEntrust(String token, String orderId) {
        view.displayLoadingPopup();
        dataRepository.cancleEntrust(token, orderId, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.cancleEntrustSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.cancleEntrustFail(code, toastMessage);
            }
        });
    }
}
