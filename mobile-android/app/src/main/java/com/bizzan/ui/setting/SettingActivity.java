package com.bizzan.ui.setting;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.ui.aboutus.AboutUsActivity;
import com.bizzan.ui.feed.FeedbackActivity;
import com.bizzan.app.GlobalConstant;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseActivity;
import com.bizzan.ui.dialog.CommonDialog;
import com.bizzan.entity.Vision;
import com.bizzan.ui.home.MainActivity;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulFileUtils;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulPermissionUtils;
import com.bizzan.utils.WonderfulToastUtils;
import com.bizzan.utils.okhttp.FileCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import com.bizzan.app.Injection;
import okhttp3.Request;

public class SettingActivity extends BaseActivity implements SettingContact.View {

    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.ibRegist)
    TextView ibRegist;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.llLanguage)
    LinearLayout llLanguage;
    @BindView(R.id.llFeed)
    LinearLayout llFeed;
    @BindView(R.id.llAboutUs)
    LinearLayout llAboutUs;
    @BindView(R.id.llVersion)
    LinearLayout llVersion;
    @BindView(R.id.tvVersionNumber)
    TextView tvVersionNumber;
    @BindView(R.id.tvLogOut)
    TextView tvLogOut;
    @BindView(R.id.line_gonggao)
    LinearLayout line_gonggao;
    @BindView(R.id.line_bangzhu)
    LinearLayout line_bangzhu;
    private String oldVision;
    private SettingContact.Presenter presenter;
    private static String versionName;
    private ProgressDialog progressDialog;
    @BindView(R.id.view_back)
    View view_back;


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_setting;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            displayLoadingPopup();
            finish();
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }
    @Override
    protected void initViews(Bundle savedInstanceState) {
        initProgressDialog();
        new SettingPresent(Injection.provideTasksRepository(getApplicationContext()), this);
        versionName = getAppVersionName(SettingActivity.this);
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
        llFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedbackActivity.actionStart(SettingActivity.this);
            }
        });
        llLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanguageActivity.actionStart(SettingActivity.this);
            }
        });
        llAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutUsActivity.actionStart(SettingActivity.this);
            }
        });
        llVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!WonderfulPermissionUtils.isCanUseStorage(SettingActivity.this)) {
                    checkPermission(GlobalConstant.PERMISSION_STORAGE, Permission.STORAGE);
                } else {
                    presenter.getNewVision(getToken());
                }
            }
        });
        tvVersionNumber.setText(versionName);
        tvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        line_gonggao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GongGaoActivity.actionStart(SettingActivity.this);
            }
        });
        line_bangzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            HelpActivity.actionStart(SettingActivity.this);
            }
        });

    }

    private void logout() {
        CommonDialog.getInstance(this, getResources().getString(R.string.logout_confirm), getResources().getString(R.string.confirm),
                getResources().getString(R.string.cancle), true, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        MyApplication.getApp().loginAgain(SettingActivity.this);
                        SharedPreferenceInstance.getInstance().saveaToken("");
                        SharedPreferenceInstance.getInstance().saveTOKEN("");
                    }
                });
       /* new AlertDialog.Builder(SettingActivity.this,R.style.custom_dialog).setMessage(R.string.logout_confirm)
                .setNegativeButton(R.string.cancle, null)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        MyApplication.getApp().loginAgain(SettingActivity.this);
                        SharedPreferenceInstance.getInstance().saveaToken("");
                        SharedPreferenceInstance.getInstance().saveTOKEN("");
//                        SharedPreferenceInstance.getInstance().saveLockPwd("");
                    }
                }).create().show();*/
    }


    private void initProgressDialog() {
        //创建进度条对话框
        progressDialog = new ProgressDialog(this);
        //设置标题
        progressDialog.setTitle(WonderfulToastUtils.getString(this,R.string.versionUpdateTip4));
        //设置信息
        progressDialog.setMessage(WonderfulToastUtils.getString(this,R.string.versionUpdateTip5));
        progressDialog.setProgress(0);
        //设置显示的格式
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    }

    private void checkPermission(int requestCode, String[] permissions) {
        AndPermission.with(SettingActivity.this).requestCode(requestCode).permission(permissions).callback(permissionListener).start();
    }

    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            switch (requestCode) {
                case GlobalConstant.PERMISSION_STORAGE:
                    presenter.getNewVision(getToken());
                    break;
                    default:
            }
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            switch (requestCode) {
                case GlobalConstant.PERMISSION_STORAGE:
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(SettingActivity.this,R.string.storage_permission));
                    break;
                    default:
            }
        }
    };

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            //versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
//            WonderfulLogUtils.logi("VersionInfo", "Exception", e);
        }
        return versionName;
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
            ImmersionBar.setTitleBar(this, llTitle);
            isSetTitle = true;
        }
    }


    @Override
    public void getNewVisionSuccess(final Vision obj) {
        if (obj.getData() == null){
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.versionUpdateTip));
            return;
        }
        if (versionName.equals(obj.getData().getVersion())) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.versionUpdateTip));
        } else {
            new AlertDialog.Builder(SettingActivity.this,R.style.custom_dialog)
                    .setIcon(null)
                    .setTitle(getString(R.string.has_new_version) + "(" + obj.getData().getVersion() + ")")
                    .setMessage(WonderfulToastUtils.getString(this,R.string.versionUpdateTip2))
                    .setPositiveButton(WonderfulToastUtils.getString(this,R.string.yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (obj.getData().getDownloadUrl() == null || "".equals(obj.getData().getDownloadUrl())) {
                                WonderfulToastUtils.showToast(WonderfulToastUtils.getString(SettingActivity.this,R.string.versionUpdateTip3));
                            }else{
                                Intent intent = new Intent();
                                intent.setData(Uri.parse(obj.getData().getDownloadUrl()));//Url 就是你要打开的网址
                                intent.setAction(Intent.ACTION_VIEW);
                                startActivity(intent); //启动浏览器
                            }
                        }
                    })
                    .setNegativeButton(WonderfulToastUtils.getString(this,R.string.no), null)
                    .show();
        }
    }

    private void download(String url) {
        WonderfulOkhttpUtils.get().url(url).build().execute(new FileCallback(WonderfulFileUtils.getCacheSaveFile(this, "application.apk").getAbsolutePath()) {
            @Override
            public void inProgress(float progress) {
                progressDialog.show();
                progressDialog.setProgress((int) (progress * 100));
            }

            @Override
            public void onError(Request request, Exception e) {
                WonderfulLogUtils.logi("下载失败：", e.getMessage());
                progressDialog.dismiss();
                WonderfulCodeUtils.checkedErrorCode(SettingActivity.this, GlobalConstant.OKHTTP_ERROR, "null");
            }

            @Override
            public void onResponse(File response) {
                progressDialog.dismiss();
                installAPk(response);
            }
        });
    }

    //普通安装
    private void installAPk(File apkPath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Uri apkUri = WonderfulFileUtils.getUriForFile(this, apkPath);
        intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        startActivity(intent);
    }

    @Override
    public void getNewVisionFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void setPresenter(SettingContact.Presenter presenter) {
        this.presenter = presenter;
    }
}
