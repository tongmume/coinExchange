package com.bizzan.ui.kline;


import com.bizzan.data.DataSource;
import com.bizzan.entity.Currency;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by Administrator on 2018/1/17.
 */

public class KlinePresenter implements KlineContract.Presenter {
    private DataSource dataRepository;
    private KlineContract.View view;

    public KlinePresenter(DataSource dataRepository, KlineContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    //type  1 表示第一次请求  2表示加载数据
    @Override
    public void KData(String symbol, Long from, Long to, String resolution, final String type) {
        dataRepository.KData(symbol, from, to, resolution, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                if (type.equals("1")){
                    view.KDataSuccess((JSONArray) obj);
                }else if (type.equals("2")){
                    view.KDataSuccess2((JSONArray) obj);
                }
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.KDataFail(code, toastMessage);
            }
        });
    }

    @Override
    public void KData_Constract(String symbol, Long from, Long to, String resolution, final String type) {
        dataRepository.KData_Constract(symbol, from, to, resolution, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                if (type.equals("1")){
                    view.KDataSuccess((JSONArray) obj);
                }else if (type.equals("2")){
                    view.KDataSuccess2((JSONArray) obj);
                }
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.KDataFail(code, toastMessage);
            }
        });
    }

    @Override
    public void allCurrency() {
        dataRepository.allCurrency(new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.allCurrencySuccess((List<Currency>) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.allCurrencyFail(code, toastMessage);

            }
        });
    }
}
