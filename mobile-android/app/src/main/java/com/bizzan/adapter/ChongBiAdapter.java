package com.bizzan.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bizzan.R;
import com.bizzan.base.LinAdapter;
import com.bizzan.entity.ChongBiBean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/3.
 */
public class ChongBiAdapter extends LinAdapter<ChongBiBean.ContentBean> {
    private List<ChongBiBean.ContentBean> beanss;
    /**
     * LinAdapter通用的构造方法
     *
     * @param context 传入的上下文
     * @param beans   要显示的数据源封装好的列表
     */
    public ChongBiAdapter(Activity context, List<ChongBiBean.ContentBean> beans) {
        super(context, beans);
        beanss=beans;
    }

    @Override
    protected View LgetView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_chongbi, parent, false);
        }
        ChongBiBean.ContentBean bean = beanss.get(position);
        TextView text_coin=ViewHolders.get(convertView,R.id.text_coin);
        text_coin.setText(bean.getSymbol());

        TextView text_time=ViewHolders.get(convertView,R.id.text_time);
        text_time.setText(bean.getCreateTime());

        TextView text_dizhi=ViewHolders.get(convertView,R.id.text_dizhi);
        text_dizhi.setText(bean.getAddress());

        TextView text_number=ViewHolders.get(convertView,R.id.text_number);
        text_number.setText(bean.getAmount()+"");

        return convertView;
    }
}
