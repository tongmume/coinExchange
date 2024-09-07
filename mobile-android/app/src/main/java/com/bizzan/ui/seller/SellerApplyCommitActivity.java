package com.bizzan.ui.seller;

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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.ui.login.LoginActivity;
import com.bizzan.app.GlobalConstant;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseActivity;
import com.bizzan.ui.dialog.HeaderSelectDialogFragment;
import com.bizzan.entity.DepositInfo;
import com.bizzan.entity.SellerApply;
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

import org.angmarch.views.NiceSpinner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.bizzan.app.Injection;

public class SellerApplyCommitActivity extends BaseActivity implements SellerApplyCommitContract.View, View.OnClickListener, HeaderSelectDialogFragment.OperateCallback {

    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.et_seller_phone)
    EditText etSellerPhone;
    @BindView(R.id.et_seller_wechat)
    EditText etSellerWechat;
    @BindView(R.id.et_seller_qq)
    EditText etSellerQq;
    @BindView(R.id.tv_seller_coin_type)
    TextView tvSellerCoinType;
    @BindView(R.id.tv_seller_coin_amount)
    TextView tvSellerCoinAmount;
    @BindView(R.id.tv_seller_asset_image)
    TextView tvSellerAssetImage;
    @BindView(R.id.tv_seller_exchange_image)
    TextView tvSellerExchangeImage;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.sp_coin_type)
    NiceSpinner sp_coin_type;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    public static final int TYPE_ASSET = 1;
    public static final int TYPE_EXCHANGE = 2;
    private String assetFileName = "asset.jpg";
    private String exchangeFileName = "exchange.jpg";
    private String assetImagePath = "";
    private String exchangeImagePath = "";
    private File imageFile;
    int type;
    private HeaderSelectDialogFragment headerSelectDialogFragment;
    private SellerApplyCommitContract.Presenter presenter;
    private Uri imageUri;
    private String phone;
    private String wechat;
    private String qq;
    private String coinType;
    private String coinAmount;
    private String coinId;
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
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(SellerApplyCommitActivity.this,R.string.camera_permission));
                    break;
                case GlobalConstant.PERMISSION_STORAGE:
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(SellerApplyCommitActivity.this,R.string.storage_permission));
                    break;
                default:
            }
        }
    };

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SellerApplyCommitActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_seller_apply_commit;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        new SellerApplyCommitPresent(Injection.provideTasksRepository(getApplicationContext()), this);
        tvCommit.setOnClickListener(this);
        tvSellerExchangeImage.setOnClickListener(this);
        tvSellerAssetImage.setOnClickListener(this);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void obtainData() {
        presenter.getDepositList(getToken());


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
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_commit:
                if (checkInput()) {
                    SellerApply sellerApply = new SellerApply(phone, wechat, qq, coinType, Integer.parseInt(coinAmount), assetImagePath, exchangeImagePath);
                    String sellerApplyString = new Gson().toJson(sellerApply);
                    presenter.commitSellerApply(getToken(), coinId, sellerApplyString);
                }
                break;
            case R.id.tv_seller_asset_image:
                imageFile = WonderfulFileUtils.getCacheSaveFile(this, assetFileName);
                toCamera(TYPE_ASSET, GlobalConstant.PERMISSION_CAMERA, Permission.CAMERA);
                break;
            case R.id.tv_seller_exchange_image:
                imageFile = WonderfulFileUtils.getCacheSaveFile(this, exchangeFileName);
                toCamera(TYPE_EXCHANGE, GlobalConstant.PERMISSION_CAMERA, Permission.CAMERA);
                break;

            default:
        }
    }

    private boolean checkInput() {
        phone = etSellerPhone.getText().toString();
        wechat = etSellerWechat.getText().toString();
        qq = etSellerQq.getText().toString();
        coinAmount = tvSellerCoinAmount.getText().toString();
        if (WonderfulStringUtils.isEmpty(phone, wechat, qq, coinType, coinAmount, assetImagePath, exchangeImagePath)) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.up_picture));
            return false;
        }
        return true;
    }


    private void showHeaderSelectDialog() {
        try {
            if (headerSelectDialogFragment == null) {
                headerSelectDialogFragment = HeaderSelectDialogFragment.getInstance(SellerApplyCommitActivity.this);
            }
            headerSelectDialogFragment.show(getSupportFragmentManager(), "header_select");
        } catch (Exception e) {
            e.printStackTrace();
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
    public void setPresenter(SellerApplyCommitContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void commitSellerApplySuccess(String message) {
        WonderfulToastUtils.showToast(message);
        finish();
    }

    @Override
    public void commitSellerApplyFail(String message) {
        WonderfulToastUtils.showToast(message);
    }

    @Override
    public void uploadBase64PicFail(int code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void uploadBase64PicSuccess(String imagePath, int type) {
        if (WonderfulStringUtils.isEmpty(imagePath)) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.empty_address));
            return;
        }
        WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.Upload_success));
        switch (type) {
            case TYPE_ASSET:
                assetImagePath = imagePath;
                tvSellerAssetImage.setText(WonderfulToastUtils.getString(this,R.string.tv_uploading));
                break;
            case TYPE_EXCHANGE:
                exchangeImagePath = imagePath;
                tvSellerExchangeImage.setText(WonderfulToastUtils.getString(this,R.string.tv_uploading));
                break;
            default:
        }
    }

    private void toCamera(int type, int requestCode, String[] permissions) {
        this.type = type;
        if (!WonderfulPermissionUtils.isCanUseCamera(this)) {
            checkPermission(requestCode, permissions);
        } else {
            showHeaderSelectDialog();
        }
    }

    @Override
    public void getDepositListSuccess(final List<DepositInfo> depositInfoList) {
        if(depositInfoList==null||depositInfoList.size()==0){
            return;
        }
        tvSellerCoinAmount.setText(depositInfoList.get(0).getAmount() + "");
        coinId = depositInfoList.get(0).getId() + "";
        if (depositInfoList.size() == 1) {
            tvSellerCoinType.setText(depositInfoList.get(0).getCoin().getUnit());
            coinType = tvSellerCoinType.getText().toString();
        } else if (depositInfoList.size() > 1) {
            sp_coin_type.setVisibility(View.VISIBLE);
            tvSellerCoinAmount.setText(depositInfoList.get(0).getAmount() + "");
            coinId = depositInfoList.get(0).getId() + "";
            coinType= depositInfoList.get(0).getCoin().getUnit();
            ArrayList<String> typeList = new ArrayList<>();
            for (DepositInfo depositInfo : depositInfoList) {
                typeList.add(depositInfo.getCoin().getUnit());
            }
            sp_coin_type.attachDataSource(typeList);
            sp_coin_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    DepositInfo depositInfo = depositInfoList.get(position);
                    tvSellerCoinAmount.setText(depositInfo.getAmount()+"");
                    coinId =depositInfo.getId() + "";
                    coinType = depositInfo.getCoin().getUnit();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    @Override
    public void getDepositListFail(String message) {
        WonderfulToastUtils.showToast(message);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case GlobalConstant.TAKE_PHOTO:
                    try {
                        Bitmap bitmap = WonderfulBitmapUtils.loadBitmap(new FileInputStream(imageFile), 800, 500);
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
                    try {
                        Bitmap bm = WonderfulBitmapUtils.zoomBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()), 800, 500);
                        presenter.uploadBase64Pic(getToken(), "data:image/jpeg;base64," + WonderfulBitmapUtils.imgToBase64(bm), type);
                    } catch (Exception e) {
                        WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.library_file_exception));
                    }
                    break;
                default:
            }
        }
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