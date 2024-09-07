package com.bizzan.ui.bind_account;

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
import com.bizzan.entity.AccountSetting;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulToastUtils;

import butterknife.BindView;
import com.bizzan.app.Injection;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class BindAccountActivity extends BaseActivity implements BindAccountContact.View {

    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvAli)
    TextView tvAli;
    @BindView(R.id.tvWeiChat)
    TextView tvWeiChat;
    @BindView(R.id.tvUnionPay)
    TextView tvUnionPay;
    @BindView(R.id.llAliAccount)
    LinearLayout llAliAccount;
    @BindView(R.id.llWeiChatAccount)
    LinearLayout llWeiChatAccount;
    @BindView(R.id.llUnionPayAccount)
    LinearLayout llUnionPayAccount;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.view_back)
    View view_back;

    private BindAccountContact.Presenter presenter;
    private AccountSetting accountSetting;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, BindAccountActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_bind_account;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        new BindAccountPresenter(Injection.provideTasksRepository(getApplicationContext()), this);
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
        llAliAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aliClick();
            }
        });
        llWeiChatAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weichatClick();
            }
        });
        llUnionPayAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bankClick();
            }
        });
    }

    private void weichatClick() {
        if (accountSetting == null) return;
        BindWeiChatActivity.actionStart(this,accountSetting);
    }

    private void bankClick() {
        if (accountSetting == null) return;
        BindBankActivity.actionStart(this,accountSetting);
    }

    private void aliClick() {
        if (accountSetting == null) return;
        BindAliActivity.actionStart(this,accountSetting);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    protected void loadData() {
        presenter.getAccountSetting(getToken());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setPresenter(BindAccountContact.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getAccountSettingSuccess(AccountSetting obj) {
        this.accountSetting = obj;
        if (accountSetting.getAliVerified() == 0) {
            tvAli.setText(WonderfulToastUtils.getString(this,R.string.unbound));

        } else {
            tvAli.setText(WonderfulToastUtils.getString(this,R.string.bound));

        }

        if (accountSetting.getWechatVerified() == 0) {
            tvWeiChat.setText(WonderfulToastUtils.getString(this,R.string.unbound));

        } else {
            tvWeiChat.setText(WonderfulToastUtils.getString(this,R.string.bound));

        }

        if (accountSetting.getBankVerified() == 0) {
            tvUnionPay.setText(WonderfulToastUtils.getString(this,R.string.unbound));

        } else {
            tvUnionPay.setText(WonderfulToastUtils.getString(this,R.string.bound));

        }
    }

    @Override
    public void getAccountSettingFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
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
