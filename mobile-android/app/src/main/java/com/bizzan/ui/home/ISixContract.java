package com.bizzan.ui.home;

import java.util.List;

import com.bizzan.entity.CurrentEntrust;
import com.bizzan.entity.Detail;
import com.bizzan.entity.EntrustHistory;
import com.bizzan.entity.Exchange;
import com.bizzan.entity.Money;
import com.bizzan.entity.SixInfo;
import com.bizzan.entity.ThreeTextInfo;
import com.bizzan.entity.modifyleverage;
import com.bizzan.entity.switchpattern;

/**
 * author: wuzongjie
 * time  : 2018/4/17 0017 19:12
 * desc  :
 */

public interface ISixContract {
    interface View {

        void errorMes(int e, String meg);

        /**
         * 盘口信息
         *
         * @param ask 买
         * @param bid 卖
         */
        void getSuccess(List<Exchange> ask, List<Exchange> bid);

        /**
         * 当前委托
         */
        void getDataLoaded(CurrentEntrust entrusts);

        /**
         * 历史委托
         */
//        void getHistorySuccess(List<EntrustHistory> success);

        /**
         * 提交委托成功(买入或者卖出成功)
         */
        void getDataLoad(int code, String message);

        /**
         * 取消委托
         */
        void getCancelSuccess(String success);


        void getAccuracy(int one, int two, SixInfo info);

        void showDialog();

        void hideDialog();

        void getCancelFail();

        void getswitch(switchpattern switchpattern);
        void getdetail(Detail detail);
        void GetModifyLeverage(modifyleverage modifyleverage);
    }

    interface Presenter {
        /**
         * 获取盘口的信息
         */
        void getExchange(String symbol);

        /**
         * 获取当前的委托
         */
        void  getCurrentOrder(String token, int getCurrentOrder,int pageNo, int pageSize);

        /**
         * 获取历史委托
         */
//        void getOrderHistory(String token, int pageNo, int pageSize, String symbol, String type, String direction, String startTime, String endTime);

        /**
         * 提交委托  开仓
         */
        void getAddOrder(String token, String type, String direction, String contractCoinId, String triggerPrice, String entrustPrice, String leverage, String volume);

        /**
         * 提交委托  开仓
         */
        void getcloseOrder(String token, String type, String direction, String contractCoinId, String triggerPrice, String entrustPrice, String leverage, String volume);


        /**
         * 取消委托  合约
         */
        void getCancelEntrust(String token, String entrustId);


        /**
         * 获取精确度
         */
        void getSymbolInfo(String symbol);

        /**
         * 修改全仓逐仓模式
         */
        void getSwitchPattern(String contractCoinId, String targetPattern);


        void getDetail(String contractCoinId, String token);

        void getModifyLeverage(String contractCoinId, String leverage, String direction);
    }
}
