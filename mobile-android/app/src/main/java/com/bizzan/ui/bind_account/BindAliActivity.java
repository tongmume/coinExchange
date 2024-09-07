package com.bizzan.ui.bind_account;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.app.GlobalConstant;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseActivity;
import com.bizzan.ui.dialog.HeaderSelectDialogFragment;
import com.bizzan.entity.AccountSetting;
import com.bizzan.utils.WonderfulBitmapUtils;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulFileUtils;
import com.bizzan.utils.WonderfulLogUtils;
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

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class BindAliActivity extends BaseActivity implements BindAccountContact.AliView, HeaderSelectDialogFragment.OperateCallback {


    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.tvAccount)
    TextView tvAccount;
    @BindView(R.id.etAccount)
    EditText etAccount;
    @BindView(R.id.etNewPwd)
    EditText etNewPwd;
    @BindView(R.id.llQRCode)
    LinearLayout llQRCode;
    @BindView(R.id.tvConfirm)
    TextView tvConfirm;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.ivIdFace)
    ImageView ivIdFace;
    @BindView(R.id.ivIdFace1)
    ImageView ivIdFace1;
    @BindView(R.id.view_back)
    View view_back;


    private BindAccountContact.AliPresenter presenter;
    private AccountSetting setting;
    private HeaderSelectDialogFragment headerSelectDialogFragment;
    private File imageFile;
    private Uri imageUri;
    private String filename = "header.jpg";
    private String url = "";

    public static void actionStart(Context context, AccountSetting accountSetting) {
        Intent intent = new Intent(context, BindAliActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("accountSetting", accountSetting);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_bind_ali;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        new BindAliPresenter(Injection.provideTasksRepository(getApplicationContext()), this);
        imageFile = WonderfulFileUtils.getCacheSaveFile(this, filename);
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
        setting = (AccountSetting) getIntent().getSerializableExtra("accountSetting");
        etName.setText(setting.getRealName());
        etName.setEnabled(false);
        if (setting.getAliVerified() == 1) {
            etAccount.setText(setting.getAlipay().getAliNo());
            tvTitle.setText(WonderfulToastUtils.getString(this,R.string.text_change)+WonderfulToastUtils.getString(this,R.string.ali_account));
        }else tvTitle.setText(WonderfulToastUtils.getString(this,R.string.binding)+WonderfulToastUtils.getString(this,R.string.ali_account));
        llQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHeaderSelectDialog();
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm();
            }
        });
    }

    private void confirm() {
        String name = etName.getText().toString();
        String account = etAccount.getText().toString();
        String pwd = etNewPwd.getText().toString();
        if (!WonderfulStringUtils.isEmpty(name,account,pwd,url)) {
            if (setting.getAliVerified() == 1) {
             presenter.getUpdateAli(getToken(),account,pwd,name,url);
            }else presenter.getBindAli(getToken(),account,pwd,name,url);
        }else WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.Incomplete_information));
        WonderfulLogUtils.logi("BindAliActivity","confirm");
    }

    private void showHeaderSelectDialog() {
        if (headerSelectDialogFragment == null)
            headerSelectDialogFragment = HeaderSelectDialogFragment.getInstance(BindAliActivity.this);
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

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    public void setPresenter(BindAccountContact.AliPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getBindAliSuccess(String obj) {
        WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.Upload_success));
        finish();
    }

    @Override
    public void getBindAliFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void getUpdateAliSuccess(String obj) {
        WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.Upload_success));
        finish();
    }

    @Override
    public void getUpdateAliFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void uploadBase64PicFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void uploadBase64PicSuccess(String obj) {
        if (WonderfulStringUtils.isEmpty(obj)) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.empty_address));
            return;
        }
        WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.Upload_success));
        url = obj;
        /*if (imageFile.exists()) imageFile.delete();*/
    }

    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            switch (requestCode) {
                case GlobalConstant.PERMISSION_CAMERA:
                    startCamera();
                    break;
            }
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            switch (requestCode) {
                case GlobalConstant.PERMISSION_CAMERA:
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(BindAliActivity.this,R.string.camera_permission));
                    break;
            }
        }
    };

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

    private void checkPermission(int requestCode, String[] permissions) {
        AndPermission.with(this).requestCode(requestCode).permission(permissions).callback(permissionListener).start();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case GlobalConstant.TAKE_PHOTO:
                takePhotoReturn(resultCode, data);
                break;
            case GlobalConstant.CHOOSE_ALBUM:
                choseAlbumReturn(resultCode, data);
                break;
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
        Bitmap bm = WonderfulBitmapUtils.zoomBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()), ivIdFace.getWidth(), ivIdFace.getHeight());
        Bitmap bm1 = WonderfulBitmapUtils.zoomBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()), ivIdFace1.getWidth(), ivIdFace1.getHeight());

        ivIdFace.setImageBitmap(bm);
        ivIdFace1.setImageBitmap(bm1);
        presenter.uploadBase64Pic(MyApplication.getApp().getCurrentUser().getToken(), "data:image/jpeg;base64," + WonderfulBitmapUtils.imgToBase64(bm));
    }

    private void takePhotoReturn(int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;
        Glide.with(this).load(imageFile).override(ivIdFace.getWidth(), ivIdFace.getHeight()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(ivIdFace);
        Glide.with(this).load(imageFile).override(ivIdFace1.getWidth(), ivIdFace1.getHeight()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(ivIdFace1);
        Bitmap bitmap = WonderfulBitmapUtils.zoomBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()), ivIdFace.getWidth(), ivIdFace.getHeight());
        presenter.uploadBase64Pic(getToken(), "data:image/jpeg;base64," + WonderfulBitmapUtils.imgToBase64(bitmap));
    }
}
