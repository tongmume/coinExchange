package com.bizzan.ui.wallet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.LinearLayout;

import com.gyf.barlibrary.ImmersionBar;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import com.bizzan.R;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.WalletConstract;

public class SelectAccountActivity extends BaseActivity {

    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    private List<WalletConstract> constracts;

    public static void actionStart(Context context, List<WalletConstract> constracts) {
        Intent intent = new Intent(context, SelectAccountActivity.class);
        intent.putExtra("constract", (Serializable) constracts);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_select_account;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        constracts = (List<WalletConstract>)getIntent().getSerializableExtra("constract");
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

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            ImmersionBar.setTitleBar(this, llTitle);
            isSetTitle = true;
        }
    }

    @OnClick({R.id.ibBack, R.id.ll_constract})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibBack:
                finish();
                break;
            case R.id.ll_constract:
                SelectCoinActivity.actionStart(this,constracts);
                break;
        }
    }
}