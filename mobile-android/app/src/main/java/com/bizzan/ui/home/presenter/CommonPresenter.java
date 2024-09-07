package com.bizzan.ui.home.presenter;

import com.bizzan.data.DataSource;

/**
 * Created by Administrator on 2018/2/25.
 */

public class CommonPresenter {
    private ICommonView view;
    private DataSource dataRepository;

    public CommonPresenter(DataSource dataRepository, ICommonView view) {
        this.view = view;
        this.dataRepository = dataRepository;
        this.view.setPresenter(this);
    }

    public void delete(String token, String symbol, final int position) {
        dataRepository.delete(token, symbol, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.deleteSuccess((String) obj, position);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.deleteFail(code, toastMessage);
            }
        });
    }

    public void add(String token, String symbol, final int position) {
        dataRepository.add(token, symbol, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.addSuccess((String) obj, position);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.addFail(code, toastMessage);
            }
        });
    }
}
