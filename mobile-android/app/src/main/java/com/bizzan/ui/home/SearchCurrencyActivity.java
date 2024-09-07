package com.bizzan.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import com.bizzan.R;
import com.bizzan.adapter.HomesAdapter;
import com.bizzan.adapter.TextWatcher;
import com.bizzan.app.UrlFactory;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.Currency;
import com.bizzan.ui.kline.KlineActivity;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

//搜索币种
public class SearchCurrencyActivity extends BaseActivity {

    private HomesAdapter mAdapter;
    private RecyclerView rvContent;
    private com.bizzan.widget.ClearableEditTextWithIcon inputview;
    private List<Currency> allCurrency;

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_search_currency;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            ImmersionBar.setTitleBar(this, findViewById(R.id.llTitle));
            isSetTitle = true;
        }
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        inputview = findViewById(R.id.inputview);
        rvContent = findViewById(R.id.rvContent);
        findViewById(R.id.ibBack).setOnClickListener(view -> finish());
        inputview.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                search();
            }
        });
    }

    void search() {
        if (allCurrency == null || allCurrency.isEmpty()) {
            getHttpCode();
            return;
        }
        String key = inputview.getText().toString();
        List<Currency> list = new ArrayList<>();
        if (TextUtils.isEmpty(key)) {
            mAdapter.setNewData(list);
        } else {
            for (Currency currency : allCurrency) {
                try {
                    if (currency.getSymbol().toUpperCase().contains(key.toUpperCase())) {
                        list.add(currency);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        mAdapter.setNewData(list);
    }

    private void getHttpCode() {
        WonderfulOkhttpUtils.post().url(UrlFactory.getAllCurrency()).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        super.onError(request, e);
                    }

                    @Override
                    public void onResponse(String response) {
                        WonderfulLogUtils.logi("miao", "所有币种" + response.toString());
                        // 获取所有币种
                        try {
                            allCurrency = new Gson().fromJson(response, new TypeToken<List<Currency>>() {
                            }.getType());
                            Currency.buildCurrency(allCurrency);
                            search();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    protected void obtainData() {
        getHttpCode();
    }

    @Override
    protected void fillWidget() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvContent.setLayoutManager(manager);
        mAdapter = new HomesAdapter(null, 1);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                KlineActivity.actionStart(SearchCurrencyActivity.this, mAdapter.getItem(position).getSymbol(), "1");
            }
        });
        mAdapter.setLoad(true);
        rvContent.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {

    }

}