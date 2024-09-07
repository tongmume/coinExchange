package com.bizzan.ui.credit;

import android.content.Context;
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
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;

import butterknife.BindView;
import com.bizzan.app.Injection;

public class CreditInfoActivity extends BaseActivity implements View.OnClickListener, CreditContract.InfoView {

    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tv_save)
    TextView tv_save;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_id_card_no)
    EditText et_id_card_no;
    @BindView(R.id.ll_id_card_photo)
    LinearLayout ll_id_card_photo;
    @BindView(R.id.tv_photo_state)
    TextView tv_photo_state;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.text_yuanyin)
    TextView text_yuanyin;
    @BindView(R.id.line_wei)
    LinearLayout line_wei;
    @BindView(R.id.view_back)
    View view_back;
    public static final int AUDITING_SUCCESS = 6;//已实名
    public static final int AUDITING_ING = 7;//审核中
    public static final int AUDITING_FILED = 8;//认证失败
    public static final int UNAUDITING = 9;//未实名
    private CreditContract.InfoPresenter presenter;

    public static void actionStart(Context context, int type, String notice) {
        Intent intent = new Intent(context, CreditInfoActivity.class);
        intent.putExtra("notice",notice);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_credit_info;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        new CreditInfoPresenter(Injection.provideTasksRepository(CreditInfoActivity.this), this);
        int type = getIntent().getExtras().getInt("type");
        if (type==8){
            line_wei.setVisibility(View.VISIBLE);
            String notice = getIntent().getExtras().getString("notice");
            text_yuanyin.setText(notice);
        }else {
            line_wei.setVisibility(View.GONE);
        }

        view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ibBack.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        ll_id_card_photo.setOnClickListener(this);


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

    static int REQUEST_CODE_UPLOAD_PHOTO = 1001;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_id_card_photo:
                startActivityForResult(new Intent(this, CreditActivity.class), REQUEST_CODE_UPLOAD_PHOTO);
                break;
            case R.id.ibBack:
                finish();
                break;
            case R.id.tv_save:
                commitRealName();
                break;
            default:
        }
    }

    String idCardFront = "";
    String idCardBack = "";
    String handHeldIdCard = "";

    private void commitRealName() {
        String realName = et_name.getText().toString();
        String idCard = et_id_card_no.getText().toString();
        if (WonderfulStringUtils.isEmpty(realName, idCard, idCardFront, idCardBack, handHeldIdCard)) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.tishi));
            return;
        }
        presenter.commitRealName(getToken(), realName, idCard, idCardFront, idCardBack, handHeldIdCard);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == REQUEST_CODE_UPLOAD_PHOTO) {
            tv_photo_state.setText(WonderfulToastUtils.getString(this,R.string.tv_uploading));
            idCardFront = data.getStringExtra("idCardFront");
            idCardBack = data.getStringExtra("idCardBack");
            handHeldIdCard = data.getStringExtra("handHeldIdCard");
        } else {
            tv_photo_state.setText("");
            idCardFront = "";
            idCardBack = "";
            handHeldIdCard = "";
        }
    }


    @Override
    public void setPresenter(CreditContract.InfoPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void commitSuccess(String result) {
        WonderfulToastUtils.showToast(result);
        finish();
    }

    @Override
    public void commitError(Integer code, String toastMessage) {
        WonderfulToastUtils.showToast(toastMessage);
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