package com.bizzan.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.multidex.MultiDex;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.bizzan.R;

import com.umeng.commonsdk.UMConfigure;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import org.xutils.x;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.bizzan.ui.login.LoginActivity;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.Currency;
import com.bizzan.entity.User;
import com.bizzan.utils.WonderfulFileUtils;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulStringUtils;

/**
 * Created by pc on 2017/3/8.
 */
public class MyApplication extends Application {
    /**
     * 是否发布了
     */
    private boolean isReleased = false;

    /**
     * 当前是否有网络
     */
    private boolean isConnect = false;

    /**
     * 当前用户信息是否发生改变
     */
    private boolean isLoginStatusChange = false;

    public int typeBiaoshi = 0;
    public int typeBiaoshi2 = 0;
    public int typeBiaoshi1 = 0;

    public static MyApplication app;

    private User currentUser = new User();
    /**
     * WonderfulToastView
     */
    private TextView tvToast;
    /**
     * 当前手机屏幕的宽高
     */
    private int mWidth;
    private int mHeight;
    public static int realVerified = 0;
    public static String number = "";
    public static List<Currency> list = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initView();
        /*二维码识别*/
        ZXingLibrary.initDisplayOpinion(this);
        getDisplayMetric();
        getCurrentUserFromFile();
        checkInternet();
        x.Ext.init(this);
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");

        BaseActivity.PreferenceUtil.init(this);
        //根据上次的语言设置，重新设置语言
        switchLanguage(BaseActivity.PreferenceUtil.getString("language", "en"));

    }


    private void switchLanguage(String language) {
        BaseActivity.switchLanguage(this, language);
    }

    private void initView() {
        tvToast = (TextView) View.inflate(app, R.layout.my_toast, null);
    }

    public boolean isLogin() {
        if (getCurrentUser() == null) return false;
        return !WonderfulStringUtils.isEmpty(getCurrentUser().getToken());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 检查是否有网络
     */
    private void checkInternet() {
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        NetworkInfo.State mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        if ((wifiState != null && wifiState == NetworkInfo.State.CONNECTED) || (mobileState != null && mobileState == NetworkInfo.State.CONNECTED)) {
            isConnect = true;
        }
    }

    /**
     * 获取屏幕的宽高
     */
    private void getDisplayMetric() {
        mWidth = getResources().getDisplayMetrics().widthPixels;
        mHeight = getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取程序的Application对象
     */
    public static MyApplication getApp() {
        return app;
    }

    /**
     * 重新登录
     */
    public void loginAgain(BaseActivity activity) {
        setCurrentUser(null);
        WonderfulFileUtils.getLongSaveFile(this, "User", "user.info").delete();
        activity.startActivityForResult(new Intent(activity, LoginActivity.class), LoginActivity.RETURN_LOGIN);
    }

    /**
     * 重新登录
     */
    public void loginAgain(Fragment fragment) {
        setCurrentUser(null);
        WonderfulFileUtils.getLongSaveFile(this, "User", "user.info").delete();
        fragment.startActivityForResult(new Intent(fragment.getActivity(), LoginActivity.class), LoginActivity.RETURN_LOGIN);
    }


    public synchronized void saveCurrentUser() {
        try {
            File file = WonderfulFileUtils.getLongSaveFile(this, "User", "user.info");
            if (currentUser == null) {
                if (file.exists()) {
                    file.delete();
                }
                return;
            }
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(currentUser);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getCurrentUserFromFile() {
        try {
            File file = new File(WonderfulFileUtils.getLongSaveDir(this, "User"), "user.info");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            this.currentUser = (User) ois.readObject();
            WonderfulLogUtils.logi("读出来的User", currentUser.toString());
            if (this.currentUser == null) {
                this.currentUser = new User();
            }
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public boolean isReleased() {
        return isReleased;
    }

    public int getmWidth() {
        return mWidth;
    }

    public int getmHeight() {
        return mHeight;
    }

    public TextView getTvToast() {
        return tvToast;
    }

    public User getCurrentUser() {
        return currentUser == null ? currentUser = new User() : currentUser;
    }

    public synchronized void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        saveCurrentUser();
    }

    public boolean isLoginStatusChange() {
        return isLoginStatusChange;
    }

    public void setLoginStatusChange(boolean loginStatusChange) {
        isLoginStatusChange = loginStatusChange;
    }

    public boolean isConnect() {
        return isConnect;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }


}
