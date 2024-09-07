package com.bizzan.ui.releaseAd;

import com.bizzan.base.Contract;
import com.bizzan.entity.Ads;
import com.bizzan.entity.CoinInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface ReleaseAdContract {
    interface View extends Contract.BaseView<Presenter> {

        void allSuccess(List<CoinInfo> obj);

        void allFail(Integer code, String toastMessage);

        void createSuccess(String obj);

        void createFail(Integer code, String toastMessage);

        void adDetailSuccess(Ads obj);

        void adDetailFail(Integer code, String toastMessage);

        void updateSuccess(String obj);

        void updateFail(Integer code, String toastMessage);
    }
    interface Presenter extends Contract.BasePresenter {

        void all();

        void create(String token, String price, String advertiseType, String coinId, String minLimit, String maxLimit, int timeLimit, String countryZhName, String priceType, String premiseRate, String remark, String number, String pay, String jyPassword, String auto, String autoword);

        void adDetail(String token, int id);

        void updateAd(String token, int id, String price, String advertiseType, String coinId, String minLimit, String maxLimit, int timeLimit, String countryZhName, String priceType, String premiseRate, String remark, String number, String pay, String jyPassword, String auto, String autoword);
    }
}
