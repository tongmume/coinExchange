package com.bizzan.ui.extract;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.adapter.AddressAdapter;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.Address;

import java.util.ArrayList;

import butterknife.BindView;

public class AddressActivity extends BaseActivity {
    public static final int RETURN_ADDRESS = 0;
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ibRegist)
    TextView ibRegist;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.rvAddress)
    RecyclerView rvAddress;
    private AddressAdapter adapter;
    private ArrayList<Address> addresses;
    @BindView(R.id.view_back)
    View view_back;

    public static void actionStart(Context context, ArrayList<Address> addresses) {
        Intent intent = new Intent(context, AddressActivity.class);
        intent.putExtra("addresses", addresses);
        ((Activity) context).startActivityForResult(intent, RETURN_ADDRESS);
    }


    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
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
        addresses = (ArrayList<Address>) getIntent().getSerializableExtra("addresses");

    }

    @Override
    protected void fillWidget() {
        initRvAddress();
    }

    private void initRvAddress() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvAddress.setLayoutManager(manager);
        adapter = new AddressAdapter(R.layout.adapter_address, addresses);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                itemClick(position);
            }
        });
        adapter.bindToRecyclerView(rvAddress);
        adapter.setEmptyView(R.layout.empty_address);
    }

    private void itemClick(int position) {
        Intent intent = new Intent();
        intent.putExtra("address", addresses.get(position));
        setResult(RESULT_OK, intent);
        finish();
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
}
