package com.bizzan.ui.my_ads;

import com.bizzan.data.DataSource;
import com.bizzan.entity.Ads;

import java.util.List;

/**
 * Created by Administrator on 2018/3/1.
 */

public class AdsPresenter implements AdsContract.Presenter {
    private final DataSource dataRepository;
    private final AdsContract.View view;

    public AdsPresenter(DataSource dataRepository, AdsContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void allAds(String token) {
        view.displayLoadingPopup();
        dataRepository.allAds(token, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.allAdsSuccess((List<Ads>) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.allAdsFail(code, toastMessage);
            }
        });
    }

    @Override
    public void release(String token, int id) {
        view.displayLoadingPopup();
        dataRepository.release(token, id, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.releaseSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.releaseFail(code, toastMessage);

            }
        });
    }

    @Override
    public void offShelf(String token, int id) {
        view.displayLoadingPopup();
        dataRepository.offShelf(token, id, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.offShelfSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.offShelfFail(code, toastMessage);

            }
        });
    }

    @Override
    public void delete(String token, int id) {
        view.displayLoadingPopup();
        dataRepository.deleteAds(token, id, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.deleteSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.deleteFail(code, toastMessage);

            }
        });
    }
}
