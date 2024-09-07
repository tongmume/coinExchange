package com.bizzan.ui.activity;

import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bizzan.R;
import com.bizzan.app.MyApplication;
import com.bizzan.app.UrlFactory;
import com.bizzan.base.BaseTransFragment;
import com.bizzan.entity.ActivityBean;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * 活动列表
 */
public class ActivityFragment extends BaseTransFragment {

    @BindView(R.id.refreshLayout)
    com.bizzan.customview.VpSwipeRefreshLayout refreshLayout;
    @BindView(R.id.listview)
    android.support.v7.widget.RecyclerView listview;
    int step;
    int pageNo = 1, pageSize = 10;
    BaseQuickAdapter<ActivityBean.DataBean.ContentBean, BaseViewHolder> mAdapter;

    public static ActivityFragment newInstance(int step) {
        ActivityFragment fragment = new ActivityFragment();
        Bundle argument = new Bundle();
        argument.putInt("step", step);
        fragment.setArguments(argument);
        return fragment;
    }


    @Override
    protected String getmTag() {
        return ActivityFragment.class.getSimpleName();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_activity;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(1);
            }
        });
        int padding = getResources().getDimensionPixelSize(R.dimen.dp_4);
        listview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        listview.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(padding, padding, padding, padding);
            }
        });
        mAdapter = new BaseQuickAdapter<ActivityBean.DataBean.ContentBean, BaseViewHolder>(R.layout.layout_activity_item) {
            @Override
            protected void convert(BaseViewHolder helper, ActivityBean.DataBean.ContentBean item) {
                helper.setText(R.id.tvName, item.getTitle());
                helper.setText(R.id.tvstartdate, item.getStartTime());
                helper.setText(R.id.tvenddate, item.getEndTime());
                helper.setText(R.id.tvStatus, item.getStepStr(getResources()));
                TextView textView = helper.getView(R.id.tvStatus);
                int color;
                if ("0".equals(item.getStep())) {
                    color = R.color.Blue;
                } else if ("1".equals(item.getStep())) {
                    color = R.color.Green;
                } else if ("2".equals(item.getStep())) {
                    color = R.color.yellowDark;
                } else {
                    color = R.color.typeGray;
                }
                textView.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(ActivityFragment.this.getActivity(), color)));
                helper.setText(R.id.tvprogress, WonderfulStringUtils.parseFloatString(item.getProgress(), 2) + "%");
                helper.setText(R.id.tvAmount, WonderfulStringUtils.parseFloatString(item.getTotalSupply(), WonderfulStringUtils.parseInt(item.getAmountScale(), 2)) + " " + item.getUnit());
                ProgressBar progressBar = helper.getView(R.id.progressbar);
                progressBar.setProgress(WonderfulStringUtils.parseInt(item.getProgress(), 0));
                Glide.with(helper.itemView.getContext())
                        .load(item.getSmallImageUrl())
                        .error(R.color.colorTextNormal)
                        .into((ImageView) helper.getView(R.id.imageview));
            }
        };
        mAdapter.bindToRecyclerView(listview);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getData(++pageNo);
            }
        }, listview);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DetailActivity.start(getActivity(), mAdapter.getItem(position));
            }
        });
    }


    @Override
    protected void obtainData() {
        step = getArguments().getInt("step");
        refreshLayout.setRefreshing(true);
        getData(pageNo);
    }

    public String getToken() {
        if (MyApplication.getApp().getCurrentUser() != null)
            return MyApplication.getApp().getCurrentUser().getToken();
        return "";
    }

    /**
     * // 持仓瓜分类型进度处理（未开始：0，进行中：50，派发中：75%，已结束：100
     * for (var i = 0; i < aList.length; i++) {
     * if(aList[i].type ==3){
     * if(aList[i].step == 1){
     * aList[i].progress = 50;
     * }else if(aList[i].step == 2){
     * aList[i].progress = 75;
     * }else if(aList[i].step == 3){
     * aList[i].progress = 100;
     * }else{
     * aList[i].progress = 0;
     * }
     * }
     * }
     *
     * @param page
     */
    private void getData(int page) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getALab())
                .addHeader("x-auth-token", getToken())
                .addParams("step", step + "")
                .addParams("pageNo", String.valueOf(page))
                .addParams("pageSize", String.valueOf(pageSize))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                if (pageNo > 1) {
                    pageNo--;
                }
                if (refreshLayout != null)
                    refreshLayout.setRefreshing(false);
                mAdapter.loadMoreComplete();
//                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                if (refreshLayout != null)
                    refreshLayout.setRefreshing(false);
                try {
                    ActivityBean activityBean = new Gson().fromJson(response, ActivityBean.class);
                    //JSONObject object = new JSONObject(response);
                    if (activityBean != null && activityBean.getData() != null && activityBean.getData().getContent() != null) {
                        List<ActivityBean.DataBean.ContentBean> list = activityBean.getData().getContent();
                        if (list != null) {
                            if (pageNo == 1) {
                                mAdapter.setNewData(list);
                            } else {
                                mAdapter.addData(list);
                            }
                        }
                        if (list == null || list.size() < pageNo) {
                            mAdapter.loadMoreEnd();
                        } else {
                            mAdapter.loadMoreEnd();
                        }
                    } else {
                        mAdapter.loadMoreEnd();
                    }
                } catch (Exception e) {
                    mAdapter.loadMoreEnd();
                    WonderfulLogUtils.logi("创新实验室", e.getMessage());
                    e.printStackTrace();
//                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {

    }

}