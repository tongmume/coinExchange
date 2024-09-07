package com.bizzan.ui.home;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bizzan.R;
import com.bizzan.adapter.TrustCurrentAdapter;
import com.bizzan.adapter.TrustHistoryAdapter;
import com.bizzan.base.BaseFragment;
import com.bizzan.entity.EntrustHistory;
import com.bizzan.customview.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TrustFragment extends BaseFragment {
    @BindView(R.id.rv_trust)
    RecyclerView rvTrust;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    TrustCurrentAdapter trustCurrentAdapter;
    TrustHistoryAdapter trustHistoryAdapter;
    private List<EntrustHistory> mCurrentData = new ArrayList<>();
    private List<EntrustHistory> mHistoryData = new ArrayList<>();

    public static int TRUST_TYPE_CURRENT = 0;
    public static int TRUST_TYPE_HISTORY = 1;

    public static TrustFragment getInstance(int type) {
        TrustFragment trustFragment = new TrustFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        trustFragment.setArguments(bundle);
        return trustFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_trust;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int type = bundle.getInt("type");
        rvTrust.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        if (TRUST_TYPE_CURRENT == type) {
            trustCurrentAdapter = new TrustCurrentAdapter(mCurrentData);
            rvTrust.setAdapter(trustCurrentAdapter);
        } else {
            trustHistoryAdapter = new TrustHistoryAdapter(mHistoryData);
            rvTrust.setAdapter(trustHistoryAdapter);
        }

    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {

    }

    public void setListData(int type, List list) {
        if (list != null && list.size() > 0) {
            tvNoData.setVisibility(View.GONE);
            rvTrust.setVisibility(View.VISIBLE);
            if (TRUST_TYPE_CURRENT == type) {
                mCurrentData.clear();
                mCurrentData.addAll(list);
                trustCurrentAdapter.notifyDataSetChanged();
            } else {
                mHistoryData.clear();
                mHistoryData.addAll(list);
                trustHistoryAdapter.notifyDataSetChanged();
            }
        } else {
            rvTrust.setVisibility(View.GONE);
            tvNoData.setVisibility(View.VISIBLE);
        }
        Log.i("height", "" + rvTrust.getLayoutParams().height + "--" + rvTrust.getMeasuredHeight() + "");

    }


}