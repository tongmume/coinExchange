package com.bizzan.ui.my_ads;


import com.bizzan.base.Contract;
import com.bizzan.entity.Ads;

import java.util.List;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface AdsContract {
    interface View extends Contract.BaseView<Presenter> {


        void allAdsFail(Integer code, String toastMessage);

        void allAdsSuccess(List<Ads> obj);

        void releaseSuccess(String obj);

        void releaseFail(Integer code, String toastMessage);

        void deleteSuccess(String obj);

        void deleteFail(Integer code, String toastMessage);

        void offShelfFail(Integer code, String toastMessage);

        void offShelfSuccess(String obj);
    }

    interface Presenter extends Contract.BasePresenter {
        void allAds(String token);

        void release(String token, int id);

        void offShelf(String token, int id);

        void delete(String token, int id);
    }
}
