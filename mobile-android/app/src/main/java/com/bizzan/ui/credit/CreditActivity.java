package com.bizzan.ui.credit;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.ui.login.LoginActivity;
import com.bizzan.app.GlobalConstant;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseActivity;
import com.bizzan.ui.dialog.HeaderSelectDialogFragment;
import com.bizzan.entity.Credit;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import com.bizzan.app.Injection;

public class CreditActivity extends BaseActivity implements CreditContract.View, HeaderSelectDialogFragment.OperateCallback {
    public static final int FACE = 1;
    public static final int BACK = 2;
    public static final int HOLD = 3;


    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tv_save)
    TextView tv_save;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.ivIdFace)
    ImageView ivIdFace;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.ivHold)
    ImageView ivHold;
    @BindView(R.id.text_shili)
    TextView text_shili;
    @BindView(R.id.view_back)
    View view_back;
    private int type;
    private ImageView currentImg;
    private File imageFile;
    private String filename = "idCard.jpg";
    private String idCardFront = "";
    private String idCardBack = "";
    private String handHeldIdCard = "";
    private Uri imageUri;
    private HeaderSelectDialogFragment headerSelectDialogFragment;


    private CreditContract.Presenter presenter;

    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            switch (requestCode) {
                case GlobalConstant.PERMISSION_CAMERA:
                    showHeaderSelectDialog();
                    break;
                case GlobalConstant.PERMISSION_STORAGE:
                    chooseFromAlbum();
                    break;
                default:
            }
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            switch (requestCode) {
                case GlobalConstant.PERMISSION_CAMERA:
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(CreditActivity.this,R.string.camera_permission));
                    break;
                case GlobalConstant.PERMISSION_STORAGE:
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(CreditActivity.this,R.string.storage_permission));
                    break;
                default:
            }
        }
    };
    private Credit.DataBean dataBean;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, CreditActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_credit;
    }


    @Override
    protected void initViews(Bundle savedInstanceState) {
        new CreditPresenter(Injection.provideTasksRepository(CreditActivity.this), this);
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
        ivIdFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCamera(FACE, ivIdFace, GlobalConstant.PERMISSION_CAMERA, Permission.CAMERA);
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCamera(BACK, ivBack, GlobalConstant.PERMISSION_CAMERA, Permission.CAMERA);
            }
        });
        ivHold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCamera(HOLD, ivHold, GlobalConstant.PERMISSION_CAMERA, Permission.CAMERA);
            }
        });
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        text_shili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPop();
            }
        });

    }
    private void showPop() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CreditActivity.this,R.style.custom_dialog_transparent);
        final Dialog dialog = builder.create();
        dialog.show();
        LayoutInflater inflater = LayoutInflater.from(CreditActivity.this);
        View v = inflater.inflate(R.layout.dialog_shili, null);
        dialog.getWindow().setContentView(v);
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = (int)(display.getWidth() * 0.7);
        dialog.getWindow().setAttributes(layoutParams);
//        ImageView cha=v.findViewById(R.id.cha);
//        cha.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
        ImageView cha=v.findViewById(R.id.cha);
        cha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });





    }
    private void save() {
        if(WonderfulStringUtils.isEmpty(idCardFront,idCardBack,handHeldIdCard)){
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.tv_affirm_uploading_picture));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("idCardFront", idCardFront);
        intent.putExtra("idCardBack", idCardBack);
        intent.putExtra("handHeldIdCard", handHeldIdCard);
        setResult(CreditInfoActivity.REQUEST_CODE_UPLOAD_PHOTO, intent);
        finish();
    }

    private void showHeaderSelectDialog() {
        try {
            if (headerSelectDialogFragment == null) {
                headerSelectDialogFragment = HeaderSelectDialogFragment.getInstance(CreditActivity.this);
            }
            headerSelectDialogFragment.show(getSupportFragmentManager(), "header_select");
        } catch (Exception e) {

        }
    }

    private void toCamera(int type, ImageView ivImage, int requestCode, String[] permissions) {
        this.type = type;
        currentImg = ivImage;
        if (!WonderfulPermissionUtils.isCanUseCamera(this)) {
            checkPermission(requestCode, permissions);
        } else {
            showHeaderSelectDialog();
        }
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
        intent.setType("image/*");
        startActivityForResult(intent, GlobalConstant.CHOOSE_ALBUM);
    }

    @Override
    protected void obtainData() {
    }

    @Override
    protected void fillWidget() {
        if (!MyApplication.getApp().isLogin()) {
            showToLoginView();
        } else {
            hideToLoginView();
        }

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case GlobalConstant.TAKE_PHOTO:
                    try {
                        Glide.with(this).load(imageFile).override(currentImg.getWidth(), currentImg.getHeight()).skipMemoryCache(true)
                                .diskCacheStrategy(DiskCacheStrategy.NONE).into(currentImg);

                        Bitmap bitmap = WonderfulBitmapUtils.loadBitmap(new FileInputStream(imageFile), currentImg.getWidth(), currentImg.getHeight());
                        WonderfulBitmapUtils.saveBitmapToFile(bitmap, imageFile, 100);
                        String base64Data = WonderfulBitmapUtils.imgToBase64(bitmap);
                        bitmap.recycle();
                        presenter.uploadBase64Pic(getToken(), "data:image/jpeg;base64," + base64Data, type);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case LoginActivity.RETURN_LOGIN:
                    if (MyApplication.getApp().isLogin()) {
                        hideToLoginView();
                    }
                    break;
                case GlobalConstant.CHOOSE_ALBUM:
                    if (resultCode != RESULT_OK) {
                        return;
                    }
                    imageUri = data.getData();
                    WonderfulLogUtils.logi("CreditActivity  ", "data.getData()   " + imageUri);
                    if (Build.VERSION.SDK_INT >= 19) {
                        imageFile = WonderfulUriUtils.getUriFromKitKat(this, imageUri);
                    } else {
                        imageFile = WonderfulUriUtils.getUriBeforeKitKat(this, imageUri);
                    }
                    if (imageFile == null) {
                        WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.library_file_exception));
                        return;
                    }
                    WonderfulLogUtils.logi("CreditActivity  ", imageFile + "data.getData()   " + imageFile.getAbsolutePath());
                    Bitmap bm = null;
                    try {
                        bm = WonderfulBitmapUtils.zoomBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()), currentImg.getWidth(), currentImg.getHeight());
                    } catch (Exception e) {
                        WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.library_file_exception));
                    }

                    currentImg.setImageBitmap(bm);
                    presenter.uploadBase64Pic(getToken(), "data:image/jpeg;base64," + WonderfulBitmapUtils.imgToBase64(bm), type);

                    break;
                default:
            }
        }
    }

    @Override
    public void setPresenter(CreditContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void uploadBase64PicFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void uploadBase64PicSuccess(String obj, int type) {
        if (WonderfulStringUtils.isEmpty(obj)) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.empty_address));
            return;
        }
        WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.Upload_success));
        switch (type) {
            case 1:
                idCardFront = obj;
                break;
            case 2:
                idCardBack = obj;
                break;
            case 3:
                handHeldIdCard = obj;
                break;
            default:
        }
        //if (imageFile.exists()) imageFile.delete();
    }

    @Override
    public void nameSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        finish();
    }

    @Override
    public void nameFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void getCreditInfoSuccess(Credit.DataBean obj) {

    }


    @Override
    public void getCreditInfoFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void toTakePhoto() {
        if (!WonderfulPermissionUtils.isCanUseCamera(this)) {
            checkPermission(GlobalConstant.PERMISSION_CAMERA, Permission.CAMERA);
        } else {
            startCamera();
        }
    }

    @Override
    public void toChooseFromAlbum() {
        if (!WonderfulPermissionUtils.isCanUseStorage(this)) {
            checkPermission(GlobalConstant.PERMISSION_STORAGE, Permission.STORAGE);
        } else {
            chooseFromAlbum();
        }
    }
}
