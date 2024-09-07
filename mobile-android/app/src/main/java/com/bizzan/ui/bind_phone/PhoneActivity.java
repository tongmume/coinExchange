package com.bizzan.ui.bind_phone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.ui.change_phone.ChangeLeadActivity;
import com.bizzan.base.BaseActivity;

import butterknife.BindView;

public class PhoneActivity extends BaseActivity {

    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvEdit)
    TextView tvEdit;
    private String phone;
    @BindView(R.id.view_back)
    View view_back;

    public static void actionStart(Context context, String phone) {
        Intent intent = new Intent(context, PhoneActivity.class);
        intent.putExtra("phone", phone);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_phone;
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
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit();
            }
        });
    }

    private void edit() {
        ChangeLeadActivity.actionStart(this,phone);
    }

    @Override
    protected void obtainData() {
        phone = getIntent().getStringExtra("phone");
    }

    @Override
    protected void fillWidget() {
        tvPhone.setText(phone);
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
