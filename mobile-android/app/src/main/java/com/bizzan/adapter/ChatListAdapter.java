package com.bizzan.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.bizzan.R;
import com.bizzan.entity.ChatTable;
import com.bizzan.customview.CircleImageView;
import com.bizzan.utils.WonderfulDateUtils;
import com.bizzan.utils.WonderfulToastUtils;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Administrator on 2018/4/12.
 */

public class ChatListAdapter extends BaseQuickAdapter<ChatTable, BaseViewHolder> {
    private Context context;

    public ChatListAdapter(int layoutResId, @Nullable List<ChatTable> data,Context context) {
        super(layoutResId, data);
        this.context = context;

    }

    @Override
    protected void convert(BaseViewHolder helper, ChatTable item) {
        helper.setText(R.id.tvName,item.getNameFrom()).setText(R.id.tvMessage,item.getContent());
        Glide.with(context.getApplicationContext()).load(item.getFromAvatar()).placeholder(R.mipmap.icon_default_header).into((CircleImageView) helper.getView(R.id.ivHeader));
        if (!item.isRead) helper.setVisible(R.id.ivChatTip,true);
        else helper.setVisible(R.id.ivChatTip,false);
        long currentTime = System.currentTimeMillis();
        if (currentTime-item.getSendTime() <= 300000) {
            helper.setText(R.id.tvTime, mContext.getResources().getText(R.string.recently));
        }else try {
            if(WonderfulDateUtils.IsToday(item.getSendTimeStr())){
                helper.setText(R.id.tvTime, item.getSendTimeStr().split(" ")[1]);
            }else {
                helper.setText(R.id.tvTime, item.getSendTimeStr().split(" ")[0]);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
