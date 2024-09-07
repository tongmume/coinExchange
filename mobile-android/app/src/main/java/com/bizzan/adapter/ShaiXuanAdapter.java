package com.bizzan.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bizzan.R;
import com.bizzan.base.LinAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/7/3.
 */
public class ShaiXuanAdapter extends LinAdapter<String> {
    private List<String> beanss;
    /**
     * LinAdapter通用的构造方法
     *
     * @param context 传入的上下文
     * @param beans   要显示的数据源封装好的列表
     */
    public ShaiXuanAdapter(Activity context, List<String> beans) {
        super(context, beans);
        beanss=beans;
    }

    @Override
    protected View LgetView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_shaixuan, parent, false);
        }

        TextView text_star=ViewHolders.get(convertView,R.id.text_star);
        text_star.setText(beanss.get(position));


        return convertView;
    }
}
