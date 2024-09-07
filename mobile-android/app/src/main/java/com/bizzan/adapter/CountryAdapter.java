package com.bizzan.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.bizzan.R;
import com.bizzan.entity.Country;

import java.util.List;

/**
 * Created by Administrator on 2018/3/1.
 */

public class CountryAdapter extends BaseQuickAdapter<Country, BaseViewHolder> {

    public CountryAdapter(int layoutResId, @Nullable List<Country> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Country item) {
        helper.setText(R.id.tvEnName, item.getEnName()).setText(R.id.tvZhName, item.getZhName());
    }
}
