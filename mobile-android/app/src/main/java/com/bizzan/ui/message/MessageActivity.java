package com.bizzan.ui.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.ui.message_detail.MessageDetailActivity;
import com.bizzan.adapter.MessageAdapter;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.Message;
import com.bizzan.utils.WonderfulCodeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.bizzan.app.Injection;

public class MessageActivity extends BaseActivity implements MessageContract.View {
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.ibDetail)
    TextView ibDetail;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.rvMessage)
    RecyclerView rvMessage;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private int pageNo = 1;
    private int pageSize = 20;
    private MessageContract.Presenter presenter;
    private List<Message> messages = new ArrayList<>();
    private MessageAdapter adapter;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MessageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_message;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            displayLoadingPopup();
            finish();
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }
    @Override
    protected void initViews(Bundle savedInstanceState) {
        new MessagePresenter(Injection.provideTasksRepository(getApplicationContext()), this);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayLoadingPopup();
                finish();
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh() {
        adapter.setEnableLoadMore(false);
        presenter.message(pageNo = 1, pageSize);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {
        initRv();
    }

    private void initRv() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvMessage.setLayoutManager(manager);
        adapter = new MessageAdapter(R.layout.adapter_message, messages, this);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MessageDetailActivity.actionStart(MessageActivity.this, messages.get(position).getId());
            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                presenter.message(++pageNo, pageSize);
                refreshLayout.setEnabled(false);
            }
        }, rvMessage);
        adapter.setEmptyView(R.layout.empty_no_message);
        rvMessage.setAdapter(adapter);
    }

    @Override
    protected void loadData() {
        presenter.message(pageNo, pageSize);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            ImmersionBar.setTitleBar(this, llTitle);
            isSetTitle = true;
        }
    }

    @Override
    public void setPresenter(MessageContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void messageSuccess(List<Message> obj) {
        refreshLayout.setEnabled(true);
        refreshLayout.setRefreshing(false);
        adapter.setEnableLoadMore(true);
        adapter.loadMoreComplete();
        if (obj == null) return;
        if (pageNo == 1) {
            messages.clear();
            if (obj.size() == 0) adapter.loadMoreEnd();
            else messages.addAll(obj);
        } else {
            if (obj.size() != 0) messages.addAll(obj);
            else adapter.loadMoreEnd();
        }
        adapter.notifyDataSetChanged();
        adapter.disableLoadMoreIfNotFullPage();
    }

    @Override
    public void messageFail(Integer code, String toastMessage) {
        refreshLayout.setEnabled(true);
        refreshLayout.setRefreshing(false);
        adapter.setEnableLoadMore(true);
        adapter.loadMoreComplete();
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }
}
