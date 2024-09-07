package com.bizzan.ui.bind_account;


import com.bizzan.base.Contract;
import com.bizzan.entity.AccountSetting;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface BindAccountContact {
    interface View extends Contract.BaseView<Presenter> {

        void getAccountSettingSuccess(AccountSetting obj);

        void getAccountSettingFail(Integer code, String toastMessage);

    }

    interface Presenter extends Contract.BasePresenter {

        void getAccountSetting(String token);

    }

    interface AliView extends Contract.BaseView<AliPresenter> {

        void getBindAliSuccess(String obj);

        void getBindAliFail(Integer code, String toastMessage);

        void getUpdateAliSuccess(String obj);

        void getUpdateAliFail(Integer code, String toastMessage);

        void uploadBase64PicFail(Integer code, String toastMessage);

        void uploadBase64PicSuccess(String obj);

    }

    interface AliPresenter extends Contract.BasePresenter {

        void uploadBase64Pic(String token, String base64);

        void getBindAli(String token, String ali, String jyPassword, String realName, String qrCodeUrl);

        void getUpdateAli(String token, String ali, String jyPassword, String realName, String qrCodeUrl);

    }

    interface WeChatView extends Contract.BaseView<WeChatPresenter> {

        void getBindWeChatSuccess(String obj);

        void getBindWeChatFail(Integer code, String toastMessage);

        void getUpdateWeChatSuccess(String obj);

        void getUpdateWeChatFail(Integer code, String toastMessage);

        void uploadBase64PicFail(Integer code, String toastMessage);

        void uploadBase64PicSuccess(String obj);

    }

    interface WeChatPresenter extends Contract.BasePresenter {

        void uploadBase64Pic(String token, String base64);

        void getBindWeChat(String token, String wechat, String jyPassword, String realName, String qrCodeUrl);

        void getUpdateWeChat(String token, String wechat, String jyPassword, String realName, String qrCodeUrl);

    }

    interface BankView extends Contract.BaseView<BankPresenter> {

        void getBindBankSuccess(String obj);

        void getBindBankFail(Integer code, String toastMessage);

        void getUpdateBankSuccess(String obj);

        void getUpdateBankFail(Integer code, String toastMessage);

    }

    interface BankPresenter extends Contract.BasePresenter {

        void getBindBank(String token, String bank, String branch, String jyPassword, String realName, String cardNo);

        void getUpdateBank(String token, String bank, String branch, String jyPassword, String realName, String cardNo);

    }
}
