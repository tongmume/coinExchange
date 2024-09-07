package com.bizzan.ui.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.ui.order_detail.OrderDetailActivity;
import com.bizzan.adapter.ChatAdapter;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.ChatEntity;
import com.bizzan.entity.ChatEvent;
import com.bizzan.entity.ChatTable;
import com.bizzan.entity.ChatTipEvent;
import com.bizzan.entity.OrderDetial;
import com.bizzan.socket.ISocket;
import com.bizzan.socket.SocketFactory;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulDatabaseUtils;
import com.bizzan.utils.WonderfulDateUtils;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulStringUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import butterknife.BindView;
import com.bizzan.app.Injection;

public class ChatActivity extends BaseActivity implements ISocket.TCPCallback, ChatContact.View {

    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvOrder)
    TextView tvOrder;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.rvMessage)
    RecyclerView rvMessage;
    @BindView(R.id.etContent)
    EditText etContent;
    @BindView(R.id.tvSend)
    TextView tvSend;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.view_back)
    View view_back;

    private OrderDetial orderDetial;
    private List<ChatEntity> chatEntities = new ArrayList<>();
    private Gson gson = new Gson();
    private ChatAdapter adapter;
    private ChatContact.Presenter presenter;
    private int pageNo = 0;
    private int pageSize = 100;
    private Boolean isSendSuccess = false;
    private Intent intent;
    //private C2CSocket c2CSocket;
    private boolean hasNew = false;
    private WonderfulDatabaseUtils databaseUtils = new WonderfulDatabaseUtils();
    private List<ChatTable> findByOrderList;
    private List<ChatTable> list;

    @Override
    protected void onResume() {
        super.onResume();
        //c2CSocket = MyBindService.getInstance();
        //WonderfulLogUtils.logi("ChatActivity",String.valueOf(c2CSocket.hashCode()));
        EventBus.getDefault().register(this);
        //JSONObject obj = buildGetBodyJson("");
        //SocketFactory.produceSocket(ISocket.C2C).run();
        //SocketFactory.produceSocket(ISocket.C2C).sendRequest(ISocket.CMD.SUBSCRIBE_CHAT, obj.toString().getBytes(), this);
        // SocketFactory.produceSocket(ISocket.GETCHAT).sendRequest(ISocket.CMD.PUSH_CHAT, obj.toString().getBytes(), this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //JSONObject obj = buildGetBodyJson("");
        // stopService(intent);

        EventBus.getDefault().unregister(this);
        //SocketFactory.produceSocket(ISocket.C2C).sendRequest(ISocket.CMD.UNSUBSCRIBE_CHAT, obj.toString().getBytes(), this);
    }

    public static void actionStart(Context context, OrderDetial orderDetial) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra("orderDetial", orderDetial);
        context.startActivity(intent);
    }


    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        new ChatPresenter(Injection.provideTasksRepository(getApplicationContext()), this);

        isNeedhide = false;
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
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

        refreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light);
        refreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        tvOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderDetailActivity.actionStart(ChatActivity.this,orderDetial.getOrderSn());
            }
        });

    }


    private void refresh() {
        refreshLayout.setRefreshing(true);
        presenter.getHistoryMessage(getToken(), orderDetial.getOrderSn(), 1, pageSize);
        WonderfulLogUtils.logi("ChatActivity", "refresh()   pageNo" + pageNo);
        /*adapter.setEnableLoadMore(false);
        adapter.loadMoreEnd(false);*/

    }

    private void send() {
        String content = etContent.getText().toString();
        if (WonderfulStringUtils.isEmpty(content)) return;
        JSONObject obj = buildBodyJson(content);
        WonderfulLogUtils.logi("ChatActivity", "buildBodyJson" + obj.toString());
        SocketFactory.produceSocket(ISocket.C2C).sendRequest(ISocket.CMD.SEND_CHAT, obj.toString().getBytes(), this);
        //SocketFactory.produceSocket(ISocket.GROUP).sendRequest(ISocket.CMD.SEND_CHAT, obj.toString().getBytes(), this);
        addChatEntity(obj.toString(), ChatEntity.Type.RIGHT);
        etContent.setText("");
        storageData(obj.toString());

    }

    private void storageData(String response) {
        ChatEntity chatEntity = gson.fromJson(response, ChatEntity.class);
        WonderfulLogUtils.logi("ChatActivity","chatEntity  "+chatEntity.toString());
        if (chatEntity == null) return;
        ChatTable table;
        hasNew = false;
        SharedPreferenceInstance.getInstance().saveHasNew(hasNew);
        list = databaseUtils.findAll();
        findByOrderList = databaseUtils.findByOrder(chatEntity.getOrderId());
        if (findByOrderList == null || findByOrderList.size() == 0) {
            WonderfulLogUtils.logi("ChatActivity","初次建立");
            table = new ChatTable();
            table.setContent(chatEntity.getContent());
            table.setFromAvatar(chatEntity.getFromAvatar());
            table.setNameFrom(chatEntity.getNameTo());
            table.setNameTo(chatEntity.getNameFrom());
            table.setUidFrom(chatEntity.getUidTo());
            table.setUidTo(chatEntity.getUidFrom());
            table.setOrderId(chatEntity.getOrderId());
            table.setRead(true);
            table.setHasNew(false);
            table.setSendTimeStr(WonderfulDateUtils.getDateTimeFromMillisecond(System.currentTimeMillis()));
            table.setSendTime(System.currentTimeMillis());
            databaseUtils.saveChat(table);
            ChatTipEvent tipEvent = new ChatTipEvent();
            tipEvent.setHasNew(hasNew);
            tipEvent.setOrderId(chatEntity.getOrderId());
            EventBus.getDefault().post(tipEvent);
            return;
        }else {
            //ChatTable table = new ChatTable();
            table = findByOrderList.get(findByOrderList.size() - 1);
            table.setContent(chatEntity.getContent());
            table.setSendTimeStr(chatEntity.getSendTimeStr());
            table.setSendTime(chatEntity.getSendTime());
            //WonderfulLogUtils.logi("MyService","  content  "+chatEntity.getContent());
            databaseUtils.update(table);
            ChatTipEvent tipEvent = new ChatTipEvent();
            tipEvent.setHasNew(hasNew);
            tipEvent.setOrderId(chatEntity.getOrderId());
            EventBus.getDefault().post(tipEvent);
            WonderfulLogUtils.logi("ChatActivity","再次进入");

            return;
        }
        /*for (int i = 0; i < list.size(); i++) {
            if (chatEntity.getOrderId().equals(list.get(i).getOrderId())) {
                findByOrderList = databaseUtils.findByOrder(chatEntity.getOrderId());
                table = findByOrderList.get(findByOrderList.size() - 1);
                table.setContent(chatEntity.getContent());
                //WonderfulLogUtils.logi("MyService","  content  "+chatEntity.getContent());
                databaseUtils.update(table);
                ChatTipEvent tipEvent = new ChatTipEvent();
                tipEvent.setHasNew(hasNew);
                tipEvent.setOrderId(chatEntity.getOrderId());
                EventBus.getDefault().post(tipEvent);
                WonderfulLogUtils.logi("ChatActivity","再次进入");
                return;
            }
        }*/
        /*table.setContent(chatEntity.getContent());
        table.setFromAvatar(chatEntity.getFromAvatar());
        table.setNameFrom(chatEntity.getNameFrom());
        table.setNameTo(chatEntity.getNameTo());
        table.setUidFrom(chatEntity.getUidFrom());
        table.setUidTo(chatEntity.getUidTo());
        table.setOrderId(chatEntity.getOrderId());
        table.setRead(true);
        table.setHasNew(false);
        databaseUtils.saveChat(table);
        ChatTipEvent tipEvent = new ChatTipEvent();
        tipEvent.setHasNew(false);
        tipEvent.setOrderId(chatEntity.getOrderId());
        EventBus.getDefault().post(tipEvent);
        WonderfulLogUtils.logi("ChatActivity","storageData完成");*/
    }
    Timer timer=null;
    TimerTask task=null;
    @Override
    protected void obtainData() {
        orderDetial = (OrderDetial) getIntent().getSerializableExtra("orderDetial");
        adapter = new ChatAdapter(ChatActivity.this, chatEntities, String.valueOf(MyApplication.app.getCurrentUser().getId()));
        // WonderfulLogUtils.logi("ChatActivity","   id    "+String.valueOf(MyApplication.app.getCurrentUser().getId()));
        rvMessage.setAdapter(adapter);

    }

    @Override
    protected void fillWidget() {
        tvTitle.setText(orderDetial.getOtherSide());
        initRvChat();
    }

    private void initRvChat() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        manager.setStackFromEnd(true);
        rvMessage.setLayoutManager(manager);

        /*adapter = new ChatAdapter(R.layout.adapter_chat, chatEntities);
        rvMessage.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, rvMessage);
        adapter.setEnableLoadMore(false);*/
    }

   /* private void loadMore() {
        WonderfulLogUtils.logi("ChatActivity","loadMore()");
        refreshLayout.setEnabled(false);
        presenter.getHistoryMessage(getToken(),orderDetial.getOrderSn(), pageNo++, pageSize);
    }*/

    @Override
    protected void loadData() {
        refresh();
        /*intent = new Intent(ChatActivity.this, MyBindService.class);
        intent.putExtra("type", MyBindService.CHAT);
        intent.putExtra("orderId", orderDetial.getOrderSn());
        intent.putExtra("uid", String.valueOf(MyApplication.getApp().getCurrentUser().getId()));
        startService(intent);*/
    }

    private JSONObject buildGetBodyJson(String content) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("orderId", orderDetial.getOrderSn());
            //obj.put("uid", orderDetial.getMyId());
            obj.put("uid", MyApplication.getApp().getCurrentUser().getId());
            return obj;
        } catch (Exception ex) {
            return null;
        }
    }

    private JSONObject buildBodyJson(String content) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("orderId", orderDetial.getOrderSn());
            //obj.put("uid", orderDetial.getMyId());
            obj.put("uidFrom", MyApplication.getApp().getCurrentUser().getId());
            obj.put("uidTo", orderDetial.getHisId());
            obj.put("nameTo", orderDetial.getOtherSide());
            obj.put("nameFrom", MyApplication.getApp().getCurrentUser().getUsername());
            obj.put("messageType", 1);
            obj.put("avatar", MyApplication.getApp().getCurrentUser().getAvatar());
            if (!WonderfulStringUtils.isEmpty(content)) obj.put("content", content);
            return obj;
        } catch (Exception ex) {
            return null;
        }
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
    public void getChatEvent(ChatEvent chatEvent) {
        if (chatEvent.getCmd().equals(ISocket.CMD.PUSH_GROUP_CHAT)) {
            ChatEntity chatEntity = gson.fromJson(chatEvent.getResonpce(), ChatEntity.class);
            if (chatEntity.getOrderId().equals(orderDetial.getOrderSn())) {
                addChatEntity(chatEvent.getResonpce(), ChatEntity.Type.LEFT);
            }
        } else{
            /*String content = etContent.getText().toString();
            JSONObject obj = buildBodyJson(content);
            addChatEntity(obj.toString(), ChatEntity.Type.RIGHT);
            etContent.setText("");*/
        }
    }

    @Override
    public void dataSuccess(ISocket.CMD cmd, String response) {
        switch (cmd) {
            /*case SUBSCRIBE_CHAT:
                WonderfulLogUtils.logi(cmd+"  订阅聊天的成功的回执：", response);
                //thumbSuccess(cmd, response);
                break;*/
            case SEND_CHAT:
                WonderfulLogUtils.logi(cmd + "  发送聊天成功的回执：", response);
                break;
            /*case UNSUBSCRIBE_CHAT:
                WonderfulLogUtils.logi(cmd+"  取消聊天订阅成功的回执:", response);
            case PUSH_CHAT:
                WonderfulLogUtils.logi(cmd+"  获取聊天成功的回执:", response);
                addChatEntity(response, ChatEntity.Type.LEFT);
                break;*/
        }
    }

    private void addChatEntity(String response, ChatEntity.Type type) {
        try {
            ChatEntity chatEntity = gson.fromJson(response, ChatEntity.class);
            chatEntity.setType(type);
            chatEntities.add(chatEntity);
            adapter.notifyDataSetChanged();
            rvMessage.smoothScrollToPosition(chatEntities.size() - 1);
        } catch (Exception ex) {

        }
    }

    @Override
    public void dataFail(int code, ISocket.CMD cmd, String errorInfo) {
        switch (cmd) {
            /*case SUBSCRIBE_CHAT:
                WonderfulLogUtils.logi(cmd+"  订阅聊天的出错的回执：", errorInfo);
                break;*/
            case SEND_CHAT:
                WonderfulLogUtils.logi(cmd + "  发送聊天出错的回执：", errorInfo);
                break;
            /*case UNSUBSCRIBE_CHAT:
                WonderfulLogUtils.logi(cmd+"  取消聊天订阅出错的回执:", errorInfo);
            case PUSH_CHAT:
                WonderfulLogUtils.logi(cmd+"  获取聊天出错的回执:", errorInfo);
                break;*/
        }
    }

    @Override
    public void setPresenter(ChatContact.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getHistoryMessageSuccess(List<ChatEntity> entityList) {
        /*adapter.setEnableLoadMore(false);
        adapter.loadMoreComplete();*/
        if (refreshLayout==null){
            return;
        }
        refreshLayout.setEnabled(true);
        refreshLayout.setRefreshing(false);
        //WonderfulLogUtils.logi("ChatActivity","getHistoryMessageSuccess    "+entityList.size());
        if (entityList == null) return;
        if (pageNo == 0) chatEntities.clear();
        Collections.reverse(chatEntities);
        chatEntities.addAll(entityList);
//        chatEntities.remove(0);
        Collections.reverse(chatEntities);
        adapter.notifyDataSetChanged();
        refreshLayout.setRefreshing(false);
       /* new Handler().postDelayed(new Runnable() {
                           @Override
                    public void run() {
                               try {
                                   presenter.getHistoryMessage(getToken(), orderDetial.getOrderSn(), pageNo, pageSize);
                               }catch (Exception e){

                               }

                           }
                    }, 10000);
*/
        /*if (pageNo == 0) this.chatEntities.clear();
        else if (entityList.size() == 0) adapter.loadMoreEnd();*/
        /*if (entityList.size() == 0) adapter.loadMoreEnd();
        this.chatEntities.addAll(entityList);
        adapter.notifyDataSetChanged();
        adapter.disableLoadMoreIfNotFullPage();*/
    }

    @Override
    public void getHistoryMessageFail(Integer code, String toastMessage) {
        //adapter.setEnableLoadMore(false);
        refreshLayout.setEnabled(true);
        refreshLayout.setRefreshing(false);
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }


}
