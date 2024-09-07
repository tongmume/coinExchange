package com.bizzan.ui.account_pwd;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.base.BaseActivity;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;

import butterknife.BindView;
import com.bizzan.app.Injection;

public class EditAccountPwdActivity extends BaseActivity implements AccountPwdContract.EditView {

    @BindView(R.id.ibBack)
    ImageButton ibBack;

    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.etLoginPwd)
    EditText etLoginPwd;
    @BindView(R.id.etOld)
    EditText etOld;
    @BindView(R.id.sellPrice)
    EditText etNew;
    @BindView(R.id.etRepeate)
    EditText etRepeate;
    @BindView(R.id.tvForgot)
    TextView tvForgot;
    @BindView(R.id.tvEdit)
    TextView tvEdit;
    @BindView(R.id.yan)
    ImageView yan;
    @BindView(R.id.yan1)
    ImageView yan1;
    @BindView(R.id.yan2)
    ImageView yan2;
    private boolean isYan=false;
    private boolean isYan1=false;
    private boolean isYan2=false;
    @BindView(R.id.view_back)
    View view_back;

    private AccountPwdContract.EditPresenter presenter;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, EditAccountPwdActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == ResetAccountPwdActivity.RETURN_FROM_RESETACCOUNT_PWD) {
                finish();
            }
        }
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_edit_account_pwd;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        new EditPwdPresenter(Injection.provideTasksRepository(getApplicationContext()), this);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPwd();
            }
        });
        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toReset();
            }
        });
        view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        yan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isYan=!isYan;
                Drawable no = getResources().getDrawable(R.drawable.yan_no);
                Drawable yes = getResources().getDrawable(R.drawable.yan_yes);
                if (isYan){
                    //显示
                    etOld.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    yan.setImageDrawable(no);

                }else {
                    etOld.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    yan.setImageDrawable(yes);
                }
            }
        });
        yan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isYan1=!isYan1;
                Drawable no = getResources().getDrawable(R.drawable.yan_no);
                Drawable yes = getResources().getDrawable(R.drawable.yan_yes);
                if (isYan1){
                    //显示
                    etNew.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    yan1.setImageDrawable(no);

                }else {
                    etNew.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    yan1.setImageDrawable(yes);
                }
            }
        });
        yan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isYan2=!isYan2;
                Drawable no = getResources().getDrawable(R.drawable.yan_no);
                Drawable yes = getResources().getDrawable(R.drawable.yan_yes);
                if (isYan2){
                    //显示
                    etRepeate.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    yan2.setImageDrawable(no);

                }else {
                    etRepeate.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    yan2.setImageDrawable(yes);
                }
            }
        });

    }

    private void toReset() {
        ResetAccountPwdActivity.actionStart(this);
    }

    private void editPwd() {
        String newPassword = etNew.getText().toString();
        String oldPassword = etOld.getText().toString();
        String re = etRepeate.getText().toString();
        if (WonderfulStringUtils.isEmpty(newPassword, oldPassword, re)) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.Incomplete_information));
            return;
        }
        if (!re.equals(newPassword)) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.pwd_diff));
            return;
        }
        presenter.editAccountPed(getToken(), newPassword, oldPassword);
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
            isSetTitle = true;
            ImmersionBar.setTitleBar(this, llTitle);
        }
    }

    @Override
    public void setPresenter(AccountPwdContract.EditPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void editAccountPedSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        finish();
    }

    @Override
    public void editAccountPedFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }
}
