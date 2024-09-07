package com.bizzan.ui.home.presenter;

import com.bizzan.ui.home.MainContract;
import com.bizzan.data.DataSource;
import com.bizzan.entity.Coin;
import com.bizzan.entity.Plate;

/**
 * Created by Administrator on 2018/2/27.
 */

public class BuyPresenter implements MainContract.BuyPresenter {
    private MainContract.BuyView view;
    private DataSource dataRepository;

    public BuyPresenter(DataSource dataRepository, MainContract.BuyView view) {
        this.view = view;
        this.dataRepository = dataRepository;
        this.view.setPresenter(this);
    }

    @Override
    public void exChange(String token, String symbol, String price, String amount, String direction, String type) {
        view.displayLoadingPopup();
        dataRepository.exChange(token, symbol, price, amount, direction, type, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.exChangeSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.exChangeFail(code, toastMessage);

            }
        });
    }

    @Override
    public void walletBase(String token, String coinName) {
        dataRepository.wallet(token, coinName, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.walletBaseSuccess((Coin) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.walletBaseFail(code, toastMessage);
            }
        });
    }

    @Override
    public void walletOther(String token, String coinName) {
        dataRepository.wallet(token, coinName, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.walletOtherSuccess((Coin) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.walletOtherFail(code, toastMessage);
            }
        });
    }

    @Override
    public void plate(String symbol) {
        dataRepository.plate(symbol, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.plateSuccess((Plate) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.plateFail(code, toastMessage);

            }
        });
    }
}
