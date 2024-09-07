package com.bizzan.ui.myinfo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bizzan.ui.credit.CreditActivity;
import com.bumptech.glide.Glide;
import com.gyf.barlibrary.ImmersionBar;
import com.kyleduo.switchbutton.SwitchButton;
import com.bizzan.R;
import com.bizzan.ui.account_pwd.AccountPwdActivity;
import com.bizzan.ui.account_pwd.EditAccountPwdActivity;
import com.bizzan.ui.bind_account.BindAccountActivity;
import com.bizzan.ui.bind_email.BindEmailActivity;
import com.bizzan.ui.bind_email.EmailActivity;
import com.bizzan.ui.bind_phone.BindPhoneActivity;
import com.bizzan.ui.bind_phone.PhoneActivity;
import com.bizzan.ui.credit.CreditInfoActivity;
import com.bizzan.ui.edit_login_pwd.EditLoginPwdActivity;
import com.bizzan.ui.set_lock.SetLockActivity;
import com.bizzan.app.GlobalConstant;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseActivity;
import com.bizzan.ui.dialog.HeaderSelectDialogFragment;
import com.bizzan.entity.SafeSetting;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.customview.CircleImageView;
import com.bizzan.utils.WonderfulBitmapUtils;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulFileUtils;
import com.bizzan.utils.WonderfulPermissionUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;
import com.bizzan.utils.WonderfulUriUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import com.bizzan.app.Injection;

public class MyInfoActivity extends BaseActivity implements MyInfoContract.View, HeaderSelectDialogFragment.OperateCallback {
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.ibRegist)
    TextView ibRegist;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.llAccountPwd)
    LinearLayout llAccountPwd;
    @BindView(R.id.ivHeader)
    CircleImageView ivHeader;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvLoginPwd)
    TextView tvLoginPwd;
    @BindView(R.id.tvAcountPwd)
    TextView tvAcountPwd;
    @BindView(R.id.tvIdCard)
    TextView tvIdCard;
    @BindView(R.id.llPhone)
    LinearLayout llPhone;
    @BindView(R.id.llEmail)
    LinearLayout llEmail;
    @BindView(R.id.llLoginPwd)
    LinearLayout llLoginPwd;
    @BindView(R.id.llIdCard)
    LinearLayout llIdCard;
    @BindView(R.id.tvAccount)
    TextView tvAccount;
    @BindView(R.id.llAccount)
    LinearLayout llAccount;
    @BindView(R.id.llLockSet)
    LinearLayout llLockSet;
    @BindView(R.id.switchButton)
    SwitchButton switchButton;
    @BindView(R.id.view_back)
    View view_back;
    private CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            SetLockActivity.actionStart(MyInfoActivity.this, isChecked ? 0 : 1);
        }
    };
    private File imageFile;
    private String filename = "header.jpg";
    private Uri imageUri;
    private String url;
    private MyInfoContract.Presenter presenter;
    private SafeSetting safeSetting;
    private HeaderSelectDialogFragment headerSelectDialogFragment;

    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            switch (requestCode) {
                case GlobalConstant.PERMISSION_CAMERA:
                    startCamera();
                    break;
                case GlobalConstant.PERMISSION_STORAGE:
                    chooseFromAlbum();
                    break;
            }
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            switch (requestCode) {
                case GlobalConstant.PERMISSION_CAMERA:
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(MyInfoActivity.this,R.string.camera_permission));
                    break;
                case GlobalConstant.PERMISSION_STORAGE:
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(MyInfoActivity.this,R.string.storage_permission));
                    break;
            }
        }
    };

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MyInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        switchButton.setOnCheckedChangeListener(null);
        String password = SharedPreferenceInstance.getInstance().getLockPwd();
        switchButton.setChecked(!WonderfulStringUtils.isEmpty(password));
        switchButton.setOnCheckedChangeListener(listener);
    }


    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_my_info;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        new MyInfoPresenter(Injection.provideTasksRepository(getApplicationContext()), this);
        imageFile = WonderfulFileUtils.getCacheSaveFile(this, filename);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayLoadingPopup();
                finish();
            }
        });
        view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayLoadingPopup();
                finish();
            }
        });
        ivHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHeaderSelectDialog();
            }
        });
        llPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneClick();
            }
        });
        llEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailClick();
            }
        });
        llAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountClick();
            }
        });
        llLoginPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPwdClick();
            }
        });
        llAccountPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountPwdClick();
            }
        });
        llIdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idCardClick();
            }
        });
        switchButton.setChecked(!WonderfulStringUtils.isEmpty(SharedPreferenceInstance.getInstance().getLockPwd()));
        switchButton.setOnCheckedChangeListener(listener);
    }

    private void accountClick() {
        if (safeSetting == null) return;
        if (safeSetting.getRealVerified() == 1 && safeSetting.getFundsVerified() == 1) {
            BindAccountActivity.actionStart(this);
        } else
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.password_realname));
    }

    // 身份认证状态判断   付款完成订阅socket  同时跳转页面  委托市价判断
    private void idCardClick() {
        startActivityForResult(new Intent(this, CreditActivity.class), 0);
        try {
            if (safeSetting.getRealVerified() == 0) {
                if (safeSetting.getRealAuditing() == 1) {//审核中
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.autonym1));
                } else {
                    if (safeSetting.getRealNameRejectReason() != null) {//失败
                        CreditInfoActivity.actionStart(this, CreditInfoActivity.AUDITING_FILED, safeSetting.getRealNameRejectReason());
                    } else {//未认证
                        CreditInfoActivity.actionStart(this, CreditInfoActivity.UNAUDITING, safeSetting.getRealNameRejectReason());
                    }
                }
            } else {

                WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.autonym2));
            }
        } catch (Exception e) {

        }
    }

    private void accountPwdClick() {
        if (safeSetting == null) return;
        if (safeSetting.getFundsVerified() == 0) AccountPwdActivity.actionStart(this);
        else if (safeSetting.getFundsVerified() == 1) EditAccountPwdActivity.actionStart(this);
    }

    private void loginPwdClick() {
        if (safeSetting == null) return;
        if (safeSetting.getPhoneVerified() == 0) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.binding_phone_first));
            return;
        }
        EditLoginPwdActivity.actionStart(this, safeSetting.getMobilePhone());
    }

    private void emailClick() {
        if (safeSetting == null) return;
        if (safeSetting.getEmailVerified() == 0) BindEmailActivity.actionStart(this);
        else EmailActivity.actionStart(this, safeSetting.getEmail());
    }

    private void phoneClick() {
        if (safeSetting == null) return;
        if (safeSetting.getPhoneVerified() == 0) BindPhoneActivity.actionStart(this);
        else PhoneActivity.actionStart(this, safeSetting.getMobilePhone());
    }

    private void showHeaderSelectDialog() {
        if (headerSelectDialogFragment == null)
            headerSelectDialogFragment = HeaderSelectDialogFragment.getInstance(MyInfoActivity.this);
        headerSelectDialogFragment.show(getSupportFragmentManager(), "header_select");
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {
        if (getToken() == null) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.autonym3));
            return;
        }
        presenter.safeSetting(getToken());
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
    public void setPresenter(MyInfoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void safeSettingSuccess(SafeSetting obj) {
        this.safeSetting = obj;
        fillViews();
    }

    private void fillViews() {
        if (ivHeader == null) {
            return;
        }
        Glide.with(getApplicationContext()).load(WonderfulStringUtils.isEmpty(safeSetting.getAvatar()) ? R.mipmap.icon_default_header : safeSetting.getAvatar()).into(ivHeader);
        tvPhone.setText(safeSetting.getPhoneVerified() == 0 ? R.string.unbound : R.string.bound);
        tvPhone.setEnabled(safeSetting.getPhoneVerified() == 0);
        tvEmail.setText(safeSetting.getEmailVerified() == 0 ? R.string.unbound : R.string.bound2);
        tvEmail.setEnabled(safeSetting.getEmailVerified() == 0);
        tvAcountPwd.setText(safeSetting.getFundsVerified() == 0 ? R.string.not_set : R.string.had_set);
        tvAcountPwd.setEnabled(safeSetting.getFundsVerified() == 0);
        tvAccount.setText(safeSetting.getAccountVerified() == 0 ? R.string.not_set : R.string.had_set);
        tvAccount.setEnabled(safeSetting.getAccountVerified() == 0);
        tvLoginPwd.setText(safeSetting.getLoginVerified() == 0 ? R.string.not_set : R.string.had_set);
        if (safeSetting.getRealVerified() == 0) {
            if (safeSetting.getRealAuditing() == 1) {
                tvIdCard.setText(R.string.creditting);
            } else {
                if (safeSetting.getRealNameRejectReason() != null)
                    tvIdCard.setText(R.string.creditfail);
                else
                    tvIdCard.setText(R.string.unverified);
            }
        } else {
            tvIdCard.setText(R.string.verification);
            //WonderfulToastUtils.showToast("实名身份已验证！");
        }
        tvIdCard.setEnabled(safeSetting.getRealVerified() == 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case GlobalConstant.TAKE_PHOTO:
                takePhotoReturn(resultCode, data);
                break;
            case GlobalConstant.CHOOSE_ALBUM:
                choseAlbumReturn(resultCode, data);
                break;
            case SetLockActivity.RETURN_SET_LOCK:
                switchButton.setOnCheckedChangeListener(null);
                String password = SharedPreferenceInstance.getInstance().getLockPwd();
                switchButton.setChecked(!WonderfulStringUtils.isEmpty(password));
                switchButton.setOnCheckedChangeListener(listener);
                break;
            default:
        }
    }

    private void choseAlbumReturn(int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;
        imageUri = data.getData();
        if (Build.VERSION.SDK_INT >= 19)
            imageFile = WonderfulUriUtils.getUriFromKitKat(this, imageUri);
        else
            imageFile = WonderfulUriUtils.getUriBeforeKitKat(this, imageUri);
        if (imageFile == null) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.library_file_exception));
            return;
        }
        Bitmap bm = WonderfulBitmapUtils.zoomBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()), ivHeader.getWidth(), ivHeader.getHeight());
        presenter.uploadBase64Pic(MyApplication.getApp().getCurrentUser().getToken(), "data:image/jpeg;base64," + WonderfulBitmapUtils.imgToBase64(bm));
        //ivHeader.setImageBitmap(bm);

    }

    private void takePhotoReturn(int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;
        //Glide.with(this).load(imageFile).override(ivHeader.getWidth(), ivHeader.getHeight()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(ivHeader);
        Bitmap bitmap = WonderfulBitmapUtils.zoomBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()), ivHeader.getWidth(), ivHeader.getHeight());
        presenter.uploadBase64Pic(getToken(), "data:image/jpeg;base64," + WonderfulBitmapUtils.imgToBase64(bitmap));
    }

    private void startCamera() {
        if (imageFile == null) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.unknown_error));
            return;
        }
        imageUri = WonderfulFileUtils.getUriForFile(this, imageFile);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, GlobalConstant.TAKE_PHOTO);
    }

    private void chooseFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
//        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, GlobalConstant.CHOOSE_ALBUM);
    }

    private void checkPermission(int requestCode, String[] permissions) {
        AndPermission.with(this).requestCode(requestCode).permission(permissions).callback(permissionListener).start();
    }

    @Override
    public void safeSettingFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void uploadBase64PicSuccess(String obj) {
        url = obj;

        presenter.avatar(getToken(), obj);
    }

    @Override
    public void uploadBase64PicFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void avatarSuccess(String obj) {
        MyApplication.getApp().getCurrentUser().setAvatar(url);
        MyApplication.getApp().saveCurrentUser();
        Glide.with(this).load(url).into(ivHeader);
    }

    @Override
    public void avatarFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void toTakePhoto() {
        if (!WonderfulPermissionUtils.isCanUseCamera(this))
            checkPermission(GlobalConstant.PERMISSION_CAMERA, Permission.CAMERA);
        else startCamera();
    }

    @Override
    public void toChooseFromAlbum() {
        if (!WonderfulPermissionUtils.isCanUseStorage(this))
            checkPermission(GlobalConstant.PERMISSION_STORAGE, Permission.STORAGE);
        else chooseFromAlbum();
    }


}
