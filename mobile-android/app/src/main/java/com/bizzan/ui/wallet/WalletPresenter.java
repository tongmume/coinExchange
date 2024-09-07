package com.bizzan.ui.wallet;


import com.bizzan.data.DataSource;
import com.bizzan.entity.Coin;
import com.bizzan.entity.CurrentEntrust;
import com.bizzan.entity.GccMatch;
import com.bizzan.entity.WalletConstract;

import java.util.List;

/**
 * Created by Administrator on 2017/9/25.
 */

public class WalletPresenter implements WalletContract.Presenter {
    private final DataSource dataRepository;
    private final WalletContract.View view;

    public WalletPresenter(DataSource dataRepository, WalletContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void myWallet(String token) {
        view.displayLoadingPopup();
        dataRepository.myWallet(token, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.myWalletSuccess((List<Coin>) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.myWalletFail(code, toastMessage);
            }
        });
    }

    @Override
    public void getCheckMatch(String token) {
        view.displayLoadingPopup();
        dataRepository.getCheckMatch(token, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.getCheckMatchSuccess((GccMatch) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.getCheckMatchFail(code, toastMessage);
            }
        });
    }

    @Override
    public void getStartMatch(String token, String amount) {
        view.displayLoadingPopup();
        dataRepository.getStartMatch(token, amount,new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.getStartMatchSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.getStartMatchFail(code, toastMessage);
            }
        });
    }

    @Override
    public void myWallet_Constract(String token) {
        view.displayLoadingPopup();
        dataRepository.myWallet_Constract(token, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.myWalletSuccess_Constract((List<WalletConstract>) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.myWalletFail(code, toastMessage);
            }
        });
    }
}
