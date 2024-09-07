package com.bizzan.ui.myEntrust;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.adapter.EntrustHistoryAdapter;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.EntrustHistory;
import com.bizzan.entity.MarketSymbol;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulToastUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import com.bizzan.app.Injection;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class MyEntrustActivity extends BaseActivity implements MyEntrustContract.View {

    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.rlEntrust)
    RecyclerView rlEntrust;
    @BindView(R.id.rlEmpty)
    RelativeLayout rlEmpty;
    @BindView(R.id.view_back)
    View view_back;
    private int pageNo = 0;
    private int pageSize = 10;
    private MyEntrustContract.Present presenter;
    private List<MarketSymbol> symbolList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    private EntrustHistoryAdapter adapter;
    private List<String> symbol = new ArrayList<>();
    private List<EntrustHistory> list = new ArrayList<>();


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MyEntrustActivity.class);
        context.startActivity(intent);
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
    protected int getActivityLayoutId() {
        return R.layout.activity_my_entrust;
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
        if (MyApplication.getApp().isLogin()) {
            initEv();
            hideToLoginView();
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


            //将可选内容与ArrayAdapter连接起来
            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, symbol);
            //设置下拉列表的风格
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    list.clear();
                    adapter.notifyDataSetChanged();
                    pageNo = 0;
                    presenter.getEntrustHistory(MyEntrustActivity.this.getToken(), symbol.get(position), pageNo, pageSize);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner.setSelection(0);


        } else {
            WonderfulLogUtils.logi("MyEntrustActivity", "未登录");
            showToLoginView();
        }
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayLoadingPopup();
                finish();
            }
        });
        view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayLoadingPopup();
                finish();
            }
        });
    }

    private void refresh() {
        pageNo = 0;
        refreshLayout.setRefreshing(true);
        presenter.getEntrustHistory(this.getToken(), spinner.getSelectedItem().toString(), pageNo, pageSize);
    }

    private void initEv() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        manager.setStackFromEnd(false);
        rlEntrust.setLayoutManager(manager);
        adapter = new EntrustHistoryAdapter(MyEntrustActivity.this, list);
        rlEntrust.setAdapter(adapter);
        rlEntrust.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
//                Log.i("miao",pageNo+"");
                pageNo=pageNo+1;
                presenter.getEntrustHistory(MyEntrustActivity.this.getToken(), spinner.getSelectedItem().toString(), pageNo, pageSize);
            }
        });
        //presenter.getEntrustHistory(this.getToken(), pageNo, pageSize);

    }


    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {
        new MyEntrustPresent(Injection.provideTasksRepository(getApplicationContext()), this);
        if (MyApplication.getApp().isLogin()) {
            presenter.getSymbol();
        } else {
            showToLoginView();
        }
    }

    @Override
    public void setPresenter(MyEntrustContract.Present presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getEntrustHistoryFail(Integer code, String toastMessage) {
        try {
            refreshLayout.setEnabled(true);
            refreshLayout.setRefreshing(false);
            WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
        }catch (Exception e){

        }

    }

    @Override
    public void getEntrustHistorySuccess(List<EntrustHistory> obj) {
        try {
            refreshLayout.setEnabled(true);
            refreshLayout.setRefreshing(false);
            if (obj.size() == 0 || obj == null) {
                if (list.size() != 0 || list != null) {
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.no_data));
                    --pageNo;
                    adapter.setLoadState(adapter.LOADING_END);
                } else {
                    rlEmpty.setVisibility(View.VISIBLE);
                    refreshLayout.setVisibility(View.GONE);
                }
            } else {
                ++pageNo;
                rlEmpty.setVisibility(View.GONE);
                refreshLayout.setVisibility(View.VISIBLE);
                //list.clear();
                list.addAll(obj);
                //rlEntrust.smoothScrollToPosition();
                adapter.setLoadState(adapter.LOADING_COMPLETE);
                WonderfulLogUtils.logi("MyEntrustActivity", list.size() + "     " + list.get(0).toString());
                adapter.notifyDataSetChanged();
            }
        }catch (Exception e){

        }

    }

    @Override
    public void getSymbolSucccess(List<MarketSymbol> objs) {
        if (objs != null && objs.size() != 0) {
            symbolList.clear();
            symbolList.addAll(objs);

            for (int i = 0; i < symbolList.size(); i++) {
                symbol.add(symbolList.get(i).getSymbol());
            }
            arrayAdapter.notifyDataSetChanged();
        } else {

        }
    }

    @Override
    public void getSymbolFailed(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    // 删除ArrayList中重复元素，保持顺序
    public static List removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
        return list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

        //用来标记是否正在向上滑动
        private boolean isSlidingUpward = false;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            // 当不滑动时
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                //获取最后一个完全显示的itemPosition
                int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
                int itemCount = manager.getItemCount();

                // 判断是否滑动到了最后一个item，并且是向上滑动
                if (lastItemPosition == (itemCount - 1) && isSlidingUpward) {
                    //加载更多
                    onLoadMore();
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            // 大于0表示正在向上滑动，小于等于0表示停止或向下滑动
            isSlidingUpward = dy > 0;
        }

        /**
         * 加载更多回调
         */
        public abstract void onLoadMore();
    }

}
