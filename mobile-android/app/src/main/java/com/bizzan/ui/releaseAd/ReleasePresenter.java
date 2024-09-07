package com.bizzan.ui.releaseAd;

import com.bizzan.data.DataSource;
import com.bizzan.entity.Ads;
import com.bizzan.entity.CoinInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/3/1.
 */

public class ReleasePresenter implements ReleaseAdContract.Presenter {
    private final DataSource dataRepository;
    private final ReleaseAdContract.View view;

    public ReleasePresenter(DataSource dataRepository, ReleaseAdContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void all() {
        view.displayLoadingPopup();
        dataRepository.all(new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.allSuccess((List<CoinInfo>) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.allFail(code, toastMessage);

            }
        });
    }

    @Override
    public void create(String token, String price, String advertiseType, String coinId, String minLimit, String maxLimit, int timeLimit, String countryZhName, String priceType, String premiseRate, String remark, String number, String pay, String jyPassword, String auto, String autoword) {
        view.displayLoadingPopup();
        dataRepository.create(token, price, advertiseType, coinId, minLimit, maxLimit, timeLimit, countryZhName, priceType, premiseRate, remark, number, pay, jyPassword, auto, autoword, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.createSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.createFail(code, toastMessage);

            }
        });
    }

    @Override
    public void adDetail(String token, int id) {
        view.displayLoadingPopup();
        dataRepository.adDetail(token, id, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.adDetailSuccess((Ads) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.adDetailFail(code, toastMessage);

            }
        });
    }

    @Override
    public void updateAd(String token, int id, String price, String advertiseType, String coinId, String minLimit, String maxLimit, int timeLimit, String countryZhName, String priceType, String premiseRate, String remark, String number, String pay, String jyPassword, String auto, String autoword) {
        view.displayLoadingPopup();
        dataRepository.updateAd(
                token, id, price, advertiseType, coinId, minLimit, maxLimit, timeLimit, countryZhName, priceType, premiseRate, remark, number, pay, jyPassword, auto, autoword, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.updateSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.updateFail(code, toastMessage);

            }
        });
    }
}
