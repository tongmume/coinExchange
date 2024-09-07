package com.bizzan.ui.entrust;

import com.bizzan.entity.EntrustHistory;
import com.bizzan.entity.EntrustHistory_constract;

import java.util.List;

/**
 * author: wuzongjie
 * time  : 2018/4/18 0018 11:21
 * desc  :
 */

public class ITrustContract {
    interface View {

        void errorMes(int e, String meg);

        void getCurrentSuccess(List<EntrustHistory> entrusts);

        void getCancelSuccess(String success);

        void onDataNotAvailable(int code, String message);

        void getHistorySuccess(List<EntrustHistory> success);

        void getHistoryConstractSuccess(List<EntrustHistory_constract.ContentBean> success);
    }

    interface Presenter {


        /**
         * 获取当前的委托
         */
        void getCurrentOrder(String token, int pageNo, int pageSize, String symbol,String type,String direction,String startTime,String endTime);

        /**
         * 获取历史委托
         */
        void getOrderHistory(String token, int pageNo, int pageSize, String symbol,String type,String direction,String startTime,String endTime);
        void onDetach();

        /**
         * 取消委托
         */
        void getCancelEntrust(String token, String orderId);
        //合约
        /**
         * 获取历史委托
         */
        void getOrderHistory_constract(String token, String contractCoinId, String pageNo, String pageSize);
    }
}
