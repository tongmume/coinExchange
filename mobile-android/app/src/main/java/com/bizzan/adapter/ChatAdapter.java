package com.bizzan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bizzan.R;
import com.bizzan.app.MyApplication;
import com.bizzan.entity.ChatEntity;
import com.bizzan.utils.WonderfulLogUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/4/12.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<ChatEntity> datas;
    private String myId;
    private Context context;

    public ChatAdapter(Context context, List<ChatEntity> datas, String myId) {
        this.datas = datas;
        this.myId = myId;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_chat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WonderfulLogUtils.logi("ChatActivity", "onBindViewHolder()  myId  " + myId + "    data " + datas.get(position).getUidFrom());
        if (myId.equals(datas.get(position).getUidFrom())) {
            holder.llLeft.setVisibility(View.GONE);
            holder.llRight.setVisibility(View.VISIBLE);
            if (MyApplication.app.getCurrentUser().getAvatar() == null) {

            } else {
                Glide.with(context).load(MyApplication.app.getCurrentUser().getAvatar())
                        .placeholder(R.mipmap.icon_default_header).into(holder.ivHeaderRight);
            }
            holder.tvMessageRight.setText(datas.get(position).getContent());
        } else {
            holder.llLeft.setVisibility(View.VISIBLE);
            holder.llRight.setVisibility(View.GONE);
            Glide.with(context).load(datas.get(position).getFromAvatar())
                    .placeholder(R.mipmap.icon_default_header).into(holder.ivHeaderLeft);
            holder.tvMessageLeft.setText(datas.get(position).getContent());
            //WonderfulLogUtils.logi("ChatActivity","onBindViewHolder()"+holder.tvMessageRight.getText());
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivHeaderLeft;
        public TextView tvMessageLeft;
        public ImageView ivHeaderRight;
        public TextView tvMessageRight;
        public LinearLayout llLeft;
        public LinearLayout llRight;

        public ViewHolder(View itemView) {
            super(itemView);
            ivHeaderLeft = itemView.findViewById(R.id.ivHeaderLeft);
            tvMessageLeft = itemView.findViewById(R.id.tvMessageLeft);
            ivHeaderRight = itemView.findViewById(R.id.ivHeaderRight);
            tvMessageRight = itemView.findViewById(R.id.tvMessageRight);
            llLeft = itemView.findViewById(R.id.llLeft);
            llRight = itemView.findViewById(R.id.llRight);
        }
    }
}
