package com.bizzan.ui.home;

import com.bizzan.base.Contract;
import com.bizzan.entity.BannerEntity;
import com.bizzan.entity.C2C;
import com.bizzan.entity.Coin;
import com.bizzan.entity.CoinInfo;
import com.bizzan.entity.EntrustHistory;
import com.bizzan.entity.Favorite;
import com.bizzan.entity.Plate;
import com.bizzan.entity.SafeSetting;
import com.bizzan.entity.WalletConstract;

import java.util.List;
import org.json.JSONObject;
/**
 * Created by Administrator on 2018/2/24.
 */

public interface MainContract {
    interface View extends Contract.BaseView<Presenter> {

        void allCurrencySuccess(Object obj);

        void allCurrencyFail(Integer code, String toastMessage);

        void findSuccess(List<Favorite> obj);

        void findFail(Integer code, String toastMessage);
    }

    interface Presenter extends Contract.BasePresenter {

        void allCurrency();

        void find(String token);

        void startTCP(short cmd, byte[] body);
    }

    interface OnePresenter extends Contract.BasePresenter {
        void myWallet(String token);
        void myWallet_Constract(String token);
        void banners(String sysAdvertiseLocation);
    }

    interface OneView extends Contract.BaseView<OnePresenter> {

        void bannersSuccess(List<BannerEntity> obj);

        void bannersFail(Integer code, String toastMessage);

        void myWalletSuccess(List<Coin> coins);

        void myWalletSuccess_Constract(List<WalletConstract> obj);

        void myWalletFail(Integer code, String toastMessage);
    }

    interface TwoPresenter extends Contract.BasePresenter {

    }

    interface TwoView extends Contract.BaseView<TwoPresenter> {

    }

    interface ThreePresenter extends Contract.BasePresenter {

    }

    interface ThreeView extends Contract.BaseView<ThreePresenter> {
    }

    interface FourPresenter extends Contract.BasePresenter {

        void all();
    }

    interface FourView extends Contract.BaseView<FourPresenter> {

        void allSuccess(List<CoinInfo> obj);

        void allFail(Integer code, String toastMessage);
    }

    interface FivePresenter extends Contract.BasePresenter {

        void myWallet(String token);

        void safeSetting(String token);

        void myPromotion(String token);
    }

    interface FiveView extends Contract.BaseView<FivePresenter> {

        void myWalletSuccess(List<Coin> obj);

        void myWalletFail(Integer code, String toastMessage);

        void safeSettingSuccess(SafeSetting obj);

        void safeSettingFail(Integer code, String toastMessage);

        void myPromotionSuccess(JSONObject obj);

        void myPromotionFail(Integer code, String toastMessage);
    }

    interface SixPresenter extends Contract.BasePresenter {
    }

    interface SixView extends Contract.BaseView<SixPresenter> {
    }

    interface SevenPresenter extends Contract.BasePresenter {
    }

    interface SevenView extends Contract.BaseView<SevenPresenter> {
    }

    interface BuyPresenter extends Contract.BasePresenter {

        void exChange(String token, String symbol, String price, String amount, String direction, String type);

        void walletBase(String token, String s);

        void walletOther(String token, String s);

        void plate(String symbol);
    }

    interface BuyView extends Contract.BaseView<BuyPresenter> {

        void exChangeSuccess(String obj);

        void exChangeFail(Integer code, String toastMessage);

        void walletBaseSuccess(Coin obj);

        void walletBaseFail(Integer code, String toastMessage);

        void walletOtherSuccess(Coin obj);

        void walletOtherFail(Integer code, String toastMessage);

        void plateSuccess(Plate obj);

        void plateFail(Integer code, String toastMessage);
    }

    interface SellPresenter extends Contract.BasePresenter {

        void exChange(String token, String symbol, String price, String amount, String direction, String type);

        void walletBase(String token, String baseCoin);

        void walletOther(String token, String otherCoin);

        void plate(String symbol);
    }

    interface SellView extends Contract.BaseView<SellPresenter> {

        void exChangeSuccess(String obj);

        void exChangeFail(Integer code, String toastMessage);

        void walletBaseSuccess(Coin obj);

        void walletBaseFail(Integer code, String toastMessage);

        void walletOtherSuccess(Coin obj);

        void walletOtherFail(Integer code, String toastMessage);

        void plateSuccess(Plate obj);

        void plateFail(Integer code, String toastMessage);
    }

    interface C2CPresenter extends Contract.BasePresenter {

        void advertise(int pageNo, int pageSize, String advertiseType, String id);
    }

    interface C2CView extends Contract.BaseView<C2CPresenter> {

        void advertiseSuccess(C2C obj);

        void advertiseFail(Integer code, String toastMessage);
    }

    interface EntrustPresenter extends Contract.BasePresenter {

        void entrust(String token, int pageSize, int pageNo, String symbol);

        void cancleEntrust(String token, String orderId);
    }

    interface EntrustView extends Contract.BaseView<EntrustPresenter> {

        void entrustSuccess(List<EntrustHistory> obj);

        void entrustFail(Integer code, String toastMessage);

        void cancleEntrustSuccess(String obj);

        void cancleEntrustFail(Integer code, String toastMessage);
    }

}
