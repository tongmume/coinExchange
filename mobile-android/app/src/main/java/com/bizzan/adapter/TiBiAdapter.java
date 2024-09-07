package com.bizzan.adapter;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bizzan.R;
import com.bizzan.app.MyApplication;
import com.bizzan.base.LinAdapter;
import com.bizzan.entity.TiBiBean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/3.
 */
public class TiBiAdapter extends LinAdapter<TiBiBean> {
    private List<TiBiBean> beanss;
    /**
     * LinAdapter通用的构造方法
     *
     * @param context 传入的上下文
     * @param beans   要显示的数据源封装好的列表
     */
    public TiBiAdapter(Activity context, List<TiBiBean> beans) {
        super(context, beans);
        beanss=beans;
    }

    @Override
    protected View LgetView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_tibi, parent, false);
        }
        TiBiBean bean = beanss.get(position);
        TextView text_coin=ViewHolders.get(convertView,R.id.text_coin);
        text_coin.setText(bean.name);

        TextView text_time=ViewHolders.get(convertView,R.id.text_time);
        text_time.setText(bean.time);

        TextView text_dizhi=ViewHolders.get(convertView,R.id.text_dizhi);
        text_dizhi.setText(bean.dizhi);

        TextView text_number=ViewHolders.get(convertView,R.id.text_number);
        text_number.setText(bean.number);

        TextView text_zhuangtai=ViewHolders.get(convertView,R.id.text_zhuangtai);
        text_zhuangtai.setText(bean.zhuangtai);
        if(bean.zhuangtai.equals("审核中")) text_zhuangtai.setTextColor(ContextCompat.getColor(MyApplication.getApp(),
                R.color.baseBlue));
        if(bean.zhuangtai.equals("成功")) text_zhuangtai.setTextColor(ContextCompat.getColor(MyApplication.getApp(),
                R.color.typeGreen));
        if(bean.zhuangtai.equals("失败")) text_zhuangtai.setTextColor(ContextCompat.getColor(MyApplication.getApp(), R.color.typeRed));
        TextView text_shouxufei=ViewHolders.get(convertView,R.id.text_shouxufei);

        text_shouxufei.setText(bean.shouxufei);


        return convertView;
    }
}
