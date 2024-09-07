package com.bizzan.ui.appeal;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.base.BaseActivity;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulDialogUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;

import butterknife.BindView;
import com.bizzan.app.Injection;

public class AppealActivity extends BaseActivity implements AppealContract.View {

    public static final int RETURN_FROM_APPEAL = 0;
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.ibRegist)
    TextView ibRegist;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.etRemark)
    EditText etRemark;
    @BindView(R.id.tvSubmit)
    TextView tvSubmit;
    private String orderSn;
    private AppealContract.Presenter presenter;
    @BindView(R.id.view_back)
    View view_back;

    public static void actionStart(Activity activity, String orderSn) {
        Intent intent = new Intent(activity, AppealActivity.class);
        intent.putExtra("orderSn", orderSn);
        activity.startActivityForResult(intent, RETURN_FROM_APPEAL);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_appeal;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        new AppealPresenter(Injection.provideTasksRepository(getApplicationContext()), this);
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
                appeal();
            }
        });
    }

    private void appeal() {
        final String remark = etRemark.getText().toString();
        if (WonderfulStringUtils.isEmpty(remark, orderSn)) return;
        WonderfulDialogUtils.showDefaultDialog(this, WonderfulToastUtils.getString(this,R.string.Warm_prompt), WonderfulToastUtils.getString(this,R.string.paymentTip9), WonderfulToastUtils.getString(this,R.string.dialog_one_cancel), WonderfulToastUtils.getString(this,R.string.paymentTip10), null, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.appeal(getToken(), remark, orderSn);
            }
        });
    }

    @Override
    protected void obtainData() {
        orderSn = getIntent().getStringExtra("orderSn");
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

    @Override
    public void setPresenter(AppealContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void appealSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void appealFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }
}
