package com.bizzan.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bizzan.app.MyApplication;


/**
 * Created by Administrator on 2018/1/17.
 */

public class SharedPreferenceInstance {


    private SharedPreferences mPreferences;
    private static SharedPreferenceInstance mInstance = null;

    public static final String SP_KEY_ISFIRSTUSE = "SYSTEM_KEY_ISFIRSTUSE";
    public static final String SP_KEY_LANGUAGE = "SP_KEY_LANGUAGE";
    public static final String SP_KEY_MONEY_SHOW_TYPE = "SP_KEY_MONEY_SHOW_TYPE";
    private static final String SP_KEY_LOCK_PWD = "SP_KEY_LOCK_PWD";
    private static final String SP_KEY_IS_NEED_SHOW_LOCK = "SP_KEY_IS_NEED_SHOW_LOCK";
    private static final String SP_KEY_TOKEN = "SP_KEY_TOKEN";
    private static final String HAS_NEW_MESSAGE = "HAS_NEW_MESSAGE";
    private static final String VERSION_CODE = "VERSION_CODE";

    private SharedPreferenceInstance() {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getApp().getApplicationContext());
    }

    public synchronized static SharedPreferenceInstance getInstance() {
        return mInstance == null ? new SharedPreferenceInstance() : mInstance;
    }

    /**
     * 获取是否是第一次使用APP
     **/
    public boolean getIsFirstUse() {
        return mPreferences == null ? true : mPreferences.getBoolean(SP_KEY_ISFIRSTUSE, true);
    }

    /**
     * 保存是否是第一次使用APP
     */
    public void saveIsFirstUse(boolean isFirstUse) {
        if (mPreferences == null) return;
        mPreferences.edit().putBoolean(SP_KEY_ISFIRSTUSE, isFirstUse).apply();
    }

    /**
     * 保存是否是提示
     */
    public void saveTishi(boolean isFirstUse) {
        if (mPreferences == null) return;
        mPreferences.edit().putBoolean("tishi", isFirstUse).apply();
    }

    public boolean getTishi() {
        return mPreferences == null ? false : mPreferences.getBoolean("tishi", false);
    }

    /**
     * 保存语言偏好  // 1 中文  2 英文  3日文
     *
     * @param languageCode
     */
    public void saveLanguageCode(int languageCode) {
        if (mPreferences == null) return;
        mPreferences.edit().putInt(SP_KEY_LANGUAGE, languageCode).apply();
    }

    /**
     * 获取语言偏好
     *
     * @return
     */
    public int getLanguageCode() {
        return mPreferences.getInt(SP_KEY_LANGUAGE, 2);
    }

    /**
     * 获取语系/地区码
     *
     * @return
     */
    public String getLanguageRegion() {
        int code = getLanguageCode();
        String lang = "en_US";
        switch (code) {
            case -1:
                lang = "zh_HK";
                break;
            case 1:
                lang = "zh_CN";
                break;
            case 2:
                lang = "en_US";
                break;
            case 3:
                lang = "ja_JP";
                break;
            case 4:
                lang = "ko_KR";
                break;
            case 5:
                lang = "de_DE";
                break;
            case 6:
                lang = "fr_FR";
                break;
            case 7:
                lang = "it_IT";
                break;
            case 8:
                lang = "es_ES";
                break;
            case 9:
                lang = "pt_PT";
                break;
            case 10:
                lang = "tr_TR";
                break;
            case 11:
                lang = "en-id";
                break;
        }
        return lang;
    }

    /**
     * 保存手势密码
     */
    public synchronized void saveLockPwd(String encryPas) {
        if (mPreferences == null) return;
        mPreferences.edit().putString(SP_KEY_LOCK_PWD, encryPas).apply();
    }


    /**
     * 获取手势密码
     */
    public synchronized String getLockPwd() {
        return mPreferences == null ? null : mPreferences.getString(SP_KEY_LOCK_PWD, null);
    }


    /**
     * 保存账户余额显示偏好 // 1 明文显示  2 密文显示
     */
    public void saveMoneyShowtype(int type) {
        if (mPreferences == null) return;
        mPreferences.edit().putInt(SP_KEY_MONEY_SHOW_TYPE, type).apply();
    }

    /**
     * 获取账户余额显示偏好
     */
    public int getMoneyShowType() {
        return mPreferences == null ? 2 : mPreferences.getInt(SP_KEY_MONEY_SHOW_TYPE, 1);
    }


    /**
     * 保存用户id
     */
    public void saveID(int type) {
        if (mPreferences == null) return;
        mPreferences.edit().putInt("id", type).apply();
    }

    /**
     * 获取用户id
     */
    public int getID() {
        return mPreferences == null ? 0 : mPreferences.getInt("id", 0);
    }

    /**
     * 保存用户token
     */
    public void saveTOKEN(String type) {
        if (mPreferences == null) return;
        mPreferences.edit().putString("token", type).apply();
    }

    /**
     * 获取用户token
     */
    public String getTOKEN() {
        return mPreferences == null ? "null" : mPreferences.getString("token", "null");
    }

    /**
     * 保存再进入 是否需要显示手势锁
     */
    public void saveIsNeedShowLock(boolean b) {
        if (mPreferences == null) return;
        mPreferences.edit().putBoolean(SP_KEY_IS_NEED_SHOW_LOCK, b).apply();
    }

    /**
     * 获取再进入 是否需要显示手势锁
     */
    public boolean getIsNeedShowLock() {
        return mPreferences == null ? false : mPreferences.getBoolean(SP_KEY_IS_NEED_SHOW_LOCK, false);
    }

    /**
     * 保存验证token
     */
    public void saveToken(String tokenKey) {
        if (mPreferences == null) return;
        mPreferences.edit().putString(SP_KEY_TOKEN, tokenKey).apply();
    }

    /**
     * 获取验证token
     */
    public String getToken() {
        return mPreferences == null ? "" : mPreferences.getString(SP_KEY_TOKEN, "");
    }

    /**
     * 保存验证atoken
     */
    public void saveaToken(String tokenKey) {
        if (mPreferences == null) return;
        mPreferences.edit().putString("atoken", tokenKey).apply();
    }

    /**
     * 获取验证atoken
     */
    public String getaToken() {
        return mPreferences == null ? "" : mPreferences.getString("atoken", "");
    }

    /**
     * 保存新消息提示
     */
    public void saveHasNew(boolean b) {
        if (mPreferences == null) return;
        mPreferences.edit().putBoolean(HAS_NEW_MESSAGE, b).apply();
    }

    /**
     * 获取新消息提示
     */
    public boolean getHasNew() {
        return mPreferences == null ? false : mPreferences.getBoolean(HAS_NEW_MESSAGE, false);
    }

    /**
     * 保存版本号
     */
    public void saveVersion(String code) {
        if (mPreferences == null) return;
        mPreferences.edit().putString(VERSION_CODE, code).apply();
    }

    /**
     * 获取版本号
     */
    public String getVersion() {
        return mPreferences == null ? "" : mPreferences.getString(VERSION_CODE, "V1.0.0");
    }
}

