package com.bizzan.ui.chatlist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.ui.chat.ChatActivity;
import com.bizzan.adapter.ChatListAdapter;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.ChatTable;
import com.bizzan.entity.ChatTipEvent;
import com.bizzan.entity.OrderDetial;
import com.bizzan.utils.WonderfulDatabaseUtils;
import com.bizzan.utils.WonderfulDpPxUtils;
import com.bizzan.utils.WonderfulToastUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

public class ChatListActivity extends BaseActivity {

    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.ibRegist)
    TextView ibRegist;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.rvChat)
    SwipeMenuRecyclerView rvChat;
    @BindView(R.id.rlEmpty)
    RelativeLayout rlEmpty;
    private ChatListAdapter adapter;
    private List<ChatTable> chatLists = new ArrayList<>();
    private WonderfulDatabaseUtils databaseUtils;
    private boolean hasNew = false;
    private List<ChatTable> findByOrderList;
    @BindView(R.id.view_back)
    View view_back;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ChatListActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_chat_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        databaseUtils = new WonderfulDatabaseUtils();
        addData();
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        EventBus.getDefault().register(this);

//        OrderDetial orderDetial = new OrderDetial();
//        ChatActivity.actionStart(ChatListActivity.this,orderDetial);
    }

    private void addData() {
        if (databaseUtils.findAll() == null || databaseUtils.findAll().size() == 0) {
            rvChat.setVisibility(View.GONE);
            rlEmpty.setVisibility(View.VISIBLE);
        } else {
            chatLists.clear();
            chatLists.addAll(databaseUtils.findAll());

            rvChat.setVisibility(View.VISIBLE);
            rlEmpty.setVisibility(View.GONE);
            initRvChat();
        }
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {

    }

    private void initRvChat() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvChat.setLayoutManager(manager);
        rvChat.setSwipeMenuCreator(new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(ChatListActivity.this)
                        .setBackgroundColorResource(R.color.typeRed)
                        //.setImage(R.mipmap.ic_action_delete) // 图标。
                        .setText(WonderfulToastUtils.getString(ChatListActivity.this,R.string.delete)) // 文字。
                        .setTextColor(Color.WHITE) // 文字颜色。
                        .setTextSize(16) // 文字大小。
                        .setWidth(WonderfulDpPxUtils.dip2px(ChatListActivity.this,80))
                        .setHeight(WonderfulDpPxUtils.dip2px(ChatListActivity.this,80));
                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。.
            }
        });
        //rvChat.setItemViewSwipeEnabled(true);
        rvChat.setSwipeMenuItemClickListener(new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                menuBridge.closeMenu();
                int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
                int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
                int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
                databaseUtils.deleteByOrderId(chatLists.get(adapterPosition).getOrderId());
                chatLists.remove(adapterPosition);
                adapter.notifyDataSetChanged();
            }
        });
        Collections.reverse(chatLists);
        adapter = new ChatListAdapter(R.layout.adapter_chat_list, chatLists, this);
        //adapter.setEmptyView(R.layout.empty_no_message);
        rvChat.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ChatTable table = chatLists.get(position);
                table.setHasNew(false);
                table.setRead(true);
                databaseUtils.update(table);
                adapter.notifyDataSetChanged();
                OrderDetial orderDetial = new OrderDetial();
                orderDetial.setOrderSn(table.getOrderId());
                orderDetial.setMyId(table.getUidTo());
                orderDetial.setHisId(table.getUidFrom());
                orderDetial.setOtherSide(table.getNameFrom());
                ChatActivity.actionStart(ChatListActivity.this,orderDetial);
            }
        });
    }


    @Override
    protected void loadData() {

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            ImmersionBar.setTitleBar(this, llTitle);
            isSetTitle = true;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getGroupChatEvent(ChatTipEvent tipEvent){
        hasNew = tipEvent.isHasNew();
        if (hasNew) {
            chatLists.clear();
            chatLists.addAll(databaseUtils.findAll());
            //Collections.reverse(chatLists);
            for (ChatTable table : chatLists){
                if (table.getOrderId().equals(tipEvent.getOrderId())) {
                    table.setHasNew(true);
                    databaseUtils.deleteByOrderId(tipEvent.getOrderId());
                    databaseUtils.saveChat(table);
                }
            }
            chatLists.clear();
            chatLists.addAll(databaseUtils.findAll());
            Collections.reverse(chatLists);
            adapter.notifyDataSetChanged();
            /*WonderfulLogUtils.logi("ChatListActivity","  getGroupChatEvent   success"
            +databaseUtils.findByOrder(tipEvent.getOrderId()).get(0).getContent());*/
        }
        //WonderfulLogUtils.logi("ChatListActivity","  getGroupChatEvent   failed");
    }



}
