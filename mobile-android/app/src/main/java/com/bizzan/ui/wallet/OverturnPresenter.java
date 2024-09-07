package com.bizzan.ui.wallet;


import java.util.List;

import com.bizzan.data.DataSource;
import com.bizzan.entity.Coin;
import com.bizzan.entity.GccMatch;
import com.bizzan.entity.WalletConstract;
import com.bizzan.entity.Wallet_Coin;

/**
 * Created by Administrator on 2017/9/25.
 */

public class OverturnPresenter implements OverturnContract.Presenter {
    private final DataSource dataRepository;
    private final OverturnContract.View view;

    public OverturnPresenter(DataSource dataRepository, OverturnContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void myWalletUsdt(String token) {
        view.displayLoadingPopup();
        dataRepository.myWalletUsdt(token, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.myWalletUsdtSuccess((Wallet_Coin) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.myWalletFail(code, toastMessage);
            }
        });
    }

    @Override
    public void myWalletList(String token) {
        view.displayLoadingPopup();
        dataRepository.myWallet_Constract(token, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.myWalletListSuccess((List<WalletConstract>) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.myWalletFail(code, toastMessage);
            }
        });
    }

    @Override
    public void RequesTrans(String token, String unit, String from, String to, String fromWalletId, String toWalletId, String amount) {
        view.displayLoadingPopup();
        dataRepository.RequesTrans(token, unit, from, to, fromWalletId, toWalletId, amount, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.myTransSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.myWalletFail(code, toastMessage);
            }
        });
    }


}
