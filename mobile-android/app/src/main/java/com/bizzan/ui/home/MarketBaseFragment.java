package com.bizzan.ui.home;

import android.content.Context;

import com.bizzan.base.BaseLazyFragment;
import com.bizzan.entity.Currency;
import com.bizzan.entity.OptionBean;

/**
 * Created by Administrator on 2018/2/26.
 */

public abstract class MarketBaseFragment extends BaseLazyFragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(getActivity() instanceof MarketOperateCallback)) {
            throw new RuntimeException("The Activity which this fragment is located must implement the MarketOperateCallback interface!");
        }
    }


    public interface MarketOperateCallback {
        int TYPE_SWITCH_SYMBOL = 0;
        int TYPE_TO_KLINE = 1;

        void itemClick(Currency currency, int type);

        void itemClick2(OptionBean.DataBean dataBean, int type);
    }

}
