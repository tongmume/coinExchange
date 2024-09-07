package com.bizzan.ui.change_phone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.base.BaseActivity;
import com.bizzan.utils.WonderfulToastUtils;

import butterknife.BindView;

public class ChangeLeadActivity extends BaseActivity {

    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ibRegist)
    TextView ibRegist;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.llCanReceive)
    LinearLayout llCanReceive;
    @BindView(R.id.llCannotReceive)
    LinearLayout llCannotReceive;
    private String phone;
    @BindView(R.id.view_back)
    View view_back;

    public static void actionStart(Context context,String phone) {
        Intent intent = new Intent(context, ChangeLeadActivity.class);
        intent.putExtra("phone",phone);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_change_lead;
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
        llCanReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canReceive();
            }
        });
        llCannotReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cannotReceive();
            }
        });
    }

    private void cannotReceive() {
        Toast.makeText(this, WonderfulToastUtils.getString(this,R.string.unReceivePhoneCodeTip), Toast.LENGTH_LONG).show();
    }

    private void canReceive() {
        ChangePhoneActivity.actionStart(this,phone);
    }

    @Override
    protected void obtainData() {
        phone = getIntent().getStringExtra("phone");
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
            isSetTitle = true;
            ImmersionBar.setTitleBar(this, llTitle);
        }
    }
}
