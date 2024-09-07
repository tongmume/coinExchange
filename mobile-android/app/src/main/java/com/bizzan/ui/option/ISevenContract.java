package com.bizzan.ui.option;

import org.json.JSONArray;

import java.util.List;

import com.bizzan.entity.CurrentBean;
import com.bizzan.entity.CurrentEntrust;
import com.bizzan.entity.Detail;
import com.bizzan.entity.Exchange;
import com.bizzan.entity.ForecaseBean;
import com.bizzan.entity.Money;
import com.bizzan.entity.OptionAddBean;
import com.bizzan.entity.OptionIconBean;
import com.bizzan.entity.OptionOrderHistoryBean;
import com.bizzan.entity.SixInfo;
import com.bizzan.entity.modifyleverage;
import com.bizzan.entity.switchpattern;

/**
 * author: wuzongjie
 * time  : 2018/4/17 0017 19:12
 * desc  :
 */

public interface ISevenContract {
    interface View {

        void errorMes(int e, String meg);

        void option_History(OptionIconBean.DataBean objs);

        void option_Order_History(OptionOrderHistoryBean.DataBean objs);

        void option_Starting(List<ForecaseBean.DataBean> objs);

        void option_Add(String msg);

        void option_Opening(List<ForecaseBean.DataBean> objs);

        void option_Current(List<CurrentBean.DataBean> objs);
        void option_Current2(List<CurrentBean.DataBean> objs);

        void option_WalletUrl(Money money);
        void showDialog();

        void hideDialog();


        void KDataSuccess(JSONArray jsonArray);

        void KDataSuccess2(JSONArray jsonArray);
    }

    interface Presenter {
        /**
         * 获取往期结果信息
         */
        void OptionHistory(String symbol, String token);
        //获取历史交割数据
        void OrderHistory(String symbol, String pageNo,String pageSize,String token);
        //正在要开始的预测
        void OptionStarting(String symbol, String token);
        //正在进行中的预测
        void OptionOpening(String symbol, String token);
        //获取我的开仓数据
        void OptionCurrent(String symbol, String optionId, String token,String type);
        //获取我的开仓数据
        void getWallet(String token,String coinName);
        //k线数据
        void KData(String symbol, Long from, Long to, String resolution,String type);
        //看涨或看跌
        void Add(String symbol,String direction,String optionId,String amount,String token);
    }
}
