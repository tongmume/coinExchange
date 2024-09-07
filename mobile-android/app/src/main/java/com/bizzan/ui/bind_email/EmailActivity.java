package com.bizzan.ui.bind_email;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.base.BaseActivity;

import butterknife.BindView;

public class EmailActivity extends BaseActivity {
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    private String email;
    @BindView(R.id.view_back)
    View view_back;

    public static void actionStart(Context context, String email) {
        Intent intent = new Intent(context, EmailActivity.class);
        intent.putExtra("email", email);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_email;
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
        email = getIntent().getStringExtra("email");
    }

    @Override
    protected void fillWidget() {
        tvEmail.setText(email);
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
