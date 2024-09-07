package com.bizzan.ui.home.presenter;

import com.bizzan.ui.home.MainContract;
import com.bizzan.data.DataSource;
import com.bizzan.entity.CoinInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/2/24.
 */

public class FourPresenter implements MainContract.FourPresenter {
    private MainContract.FourView view;
    private DataSource dataRepository;

    public FourPresenter(DataSource dataRepository, MainContract.FourView view) {
        this.view = view;
        this.dataRepository = dataRepository;
        this.view.setPresenter(this);
    }

    @Override
    public void all() {
        dataRepository.all(new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.allSuccess((List<CoinInfo>) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.allFail(code, toastMessage);
            }
        });
    }
}
