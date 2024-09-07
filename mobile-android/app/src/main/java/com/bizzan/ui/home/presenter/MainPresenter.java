package com.bizzan.ui.home.presenter;

import com.bizzan.ui.home.MainContract;
import com.bizzan.data.DataSource;
import com.bizzan.entity.Favorite;

import java.util.List;

/**
 * Created by Administrator on 2018/2/25.
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private DataSource dataRepository;

    public MainPresenter(DataSource dataRepository, MainContract.View view) {
        this.view = view;
        this.dataRepository = dataRepository;
        this.view.setPresenter(this);
    }

    @Override
    public void allCurrency() {
        dataRepository.allHomeCurrency(new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.allCurrencySuccess(obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.allCurrencyFail(code, toastMessage);
            }
        });
    }

    @Override
    public void find(String token) {
        dataRepository.find(token, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.findSuccess((List<Favorite>) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.findFail(code, toastMessage);
            }
        });
    }

    @Override
    public void startTCP(short cmd, byte[] body) {
//        MarketSocket.getInstance();
//        MarketSocket.sendRequest(cmd, body);
    }
}
