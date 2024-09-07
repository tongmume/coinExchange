package com.bizzan.ui.country;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.adapter.CountryAdapter;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.Country;
import com.bizzan.utils.WonderfulCodeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.bizzan.app.Injection;

public class CountryActivity extends BaseActivity implements CountryContract.View {

    public static final int RETURN_COUNTRY = 0;

    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ibHelp)
    ImageButton ibHelp;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.rvCountry)
    RecyclerView rvCountry;
    private List<Country> countries = new ArrayList<>();
    private CountryAdapter adapter;
    @BindView(R.id.view_back)
    View view_back;

    private CountryContract.Presenter presenter;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, CountryActivity.class);
        activity.startActivityForResult(intent, RETURN_COUNTRY);
    }

    public static void actionStart(Fragment fragment) {
        Intent intent = new Intent(fragment.getActivity(), CountryActivity.class);
        fragment.startActivityForResult(intent, RETURN_COUNTRY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_country;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        new CountryPresenter(Injection.provideTasksRepository(getApplicationContext()), this);
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
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {
        initRvCountry();
    }

    private void initRvCountry() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvCountry.setLayoutManager(manager);
        adapter = new CountryAdapter(R.layout.adapter_country, countries);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("country", countries.get(position));
                CountryActivity.this.setResult(RESULT_OK, intent);
                finish();
            }
        });
        rvCountry.setAdapter(adapter);
    }

    @Override
    protected void loadData() {
        presenter.country();
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
    public void setPresenter(CountryContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void countrySuccess(List<Country> obj) {
        if (obj == null) return;
        this.countries.clear();
        this.countries.addAll(obj);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void countryFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }
}
