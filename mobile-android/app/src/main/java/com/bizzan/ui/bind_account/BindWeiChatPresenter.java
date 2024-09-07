package com.bizzan.ui.bind_account;

import com.bizzan.data.DataSource;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class BindWeiChatPresenter implements BindAccountContact.WeChatPresenter {
    private final DataSource dataRepository;
    private final BindAccountContact.WeChatView view;

    public BindWeiChatPresenter(DataSource dataRepository, BindAccountContact.WeChatView view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getBindWeChat(String token, String wechat, String jyPassword, String realName, String qrCodeUrl) {
        view.displayLoadingPopup();
        dataRepository.getBindWeiChat(token,wechat,jyPassword,realName,qrCodeUrl, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.getBindWeChatSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.getBindWeChatFail(code,toastMessage);
            }
        });
    }

    @Override
    public void getUpdateWeChat(String token, String wechat, String jyPassword, String realName, String qrCodeUrl) {
        view.displayLoadingPopup();
        dataRepository.getUpdateWeiChat(token,wechat,jyPassword,realName,qrCodeUrl, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.getUpdateWeChatSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.getUpdateWeChatFail(code,toastMessage);
            }
        });
    }

    @Override
    public void uploadBase64Pic(String token, String base64Data) {
        view.displayLoadingPopup();
        dataRepository.uploadBase64Pic(token, base64Data, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.uploadBase64PicSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.uploadBase64PicFail(code, toastMessage);

            }
        });
    }
}
