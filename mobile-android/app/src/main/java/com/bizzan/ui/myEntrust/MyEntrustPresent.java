package com.bizzan.ui.myEntrust;

import com.bizzan.data.DataSource;
import com.bizzan.entity.EntrustHistory;
import com.bizzan.entity.MarketSymbol;
import com.bizzan.utils.WonderfulLogUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class MyEntrustPresent implements MyEntrustContract.Present {

    private final DataSource dataRepository;
    private final MyEntrustContract.View view;

    public MyEntrustPresent(DataSource dataRepository, MyEntrustContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getEntrustHistory(String token, String symbol,int pageNo, int pageSize) {
        view.displayLoadingPopup();
        dataRepository.getEntrustHistory(token,symbol, pageNo,pageSize,new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.getEntrustHistorySuccess((List<EntrustHistory>) obj);
                WonderfulLogUtils.logi("getEntrustHistory","obj     "+obj.toString());
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.getEntrustHistoryFail(code, toastMessage);
            }
        });
    }

    @Override
    public void getSymbol() {
        view.displayLoadingPopup();
        dataRepository.getSymbol(new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.getSymbolSucccess((List<MarketSymbol>) obj);
                WonderfulLogUtils.logi("getMarketSymbol","obj     "+obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.getSymbolFailed(code, toastMessage);
            }
        });
    }
}
