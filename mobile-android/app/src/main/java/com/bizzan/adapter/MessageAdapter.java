package com.bizzan.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.bizzan.R;
import com.bizzan.entity.Message;
import com.bizzan.utils.WonderfulStringUtils;

import java.util.List;
    
/**
 * Created by Administrator on 2018/2/6.
 */

public class MessageAdapter extends BaseQuickAdapter<Message, BaseViewHolder> {
    private Context context;

    public MessageAdapter(@LayoutRes int layoutResId, @Nullable List<Message> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Message item) {
        helper.setText(R.id.tvTitle, item.getTitle()).setText(R.id.tvContent, Html.fromHtml(item.getContent())).setText(R.id.tvTime, item.getCreateTime());
        if (WonderfulStringUtils.isEmpty(item.getImgUrl()))
            Glide.with(context).load(R.mipmap.icon_banner).into((ImageView) helper.getView(R.id.ivHeader));
        else Glide.with(context).load(item.getImgUrl()).into((ImageView) helper.getView(R.id.ivHeader));
    }
}
