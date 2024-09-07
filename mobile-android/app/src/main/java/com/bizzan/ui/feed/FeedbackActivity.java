package com.bizzan.ui.feed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.ui.login.LoginActivity;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseActivity;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;

import butterknife.BindView;
import com.bizzan.app.Injection;

public class FeedbackActivity extends BaseActivity implements FeedBackContract.View {

    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.etRemark)
    EditText etRemark;
    @BindView(R.id.tvSubmit)
    TextView tvSubmit;
    @BindView(R.id.ibRegist)
    TextView ibRegist;
    @BindView(R.id.llContainer)
    LinearLayout llContainer;
    @BindView(R.id.view_back)
    View view_back;

    private FeedBackContract.Presenter presenter;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, FeedbackActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == LoginActivity.RETURN_LOGIN) {
                fillWidget();
            }
        }
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        new FeedBackPresenter(Injection.provideTasksRepository(getApplicationContext()), this);
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
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remark();
            }
        });
    }

    private void remark() {
        String remark = etRemark.getText().toString();
        if (WonderfulStringUtils.isEmpty(remark)) return;
        presenter.remark(getToken(), remark);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {
        if (MyApplication.getApp().isLogin()) {
            hideToLoginView();
        } else {
            showToLoginView();
        }
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected ViewGroup getEmptyView() {
        return llContainer;
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
    public void setPresenter(FeedBackContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void remarkSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        finish();
    }

    @Override
    public void remarkFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

}
