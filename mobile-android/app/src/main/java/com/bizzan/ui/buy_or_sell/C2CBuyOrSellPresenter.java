package com.bizzan.ui.buy_or_sell;


import com.bizzan.data.DataSource;
import com.bizzan.entity.C2CExchangeInfo;

/**
 * Created by Administrator on 2017/9/25.
 */

public class C2CBuyOrSellPresenter implements C2CBuyOrSellContract.Presenter {
    private final DataSource dataRepository;
    private final C2CBuyOrSellContract.View view;

    public C2CBuyOrSellPresenter(DataSource dataRepository, C2CBuyOrSellContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void c2cInfo(int id) {
        view.displayLoadingPopup();
        dataRepository.c2cInfo(id, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.c2cInfoSuccess((C2CExchangeInfo) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.c2cInfoFail(code, toastMessage);

            }
        });
    }

    @Override
    public void c2cBuy(String token, String id, String coinId, String price, String money, String amount, String remark,String mode) {
        view.displayLoadingPopup();
        dataRepository.c2cBuy(token, id, coinId, price, money, amount, remark, mode,new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.c2cBuySuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.c2cBuyFail(code, toastMessage);

            }
        });
    }

    @Override
    public void c2cSell(String token, String id, String coinId, String price, String money, String amount, String remark,String mode) {
        view.displayLoadingPopup();
        dataRepository.c2cSell(token, id, coinId, price, money, amount, remark, mode,new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.c2cSellSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.c2cSellFail(code, toastMessage);

            }
        });
    }
}
