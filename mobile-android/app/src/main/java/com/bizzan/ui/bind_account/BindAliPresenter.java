package com.bizzan.ui.bind_account;

import com.bizzan.data.DataSource;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class BindAliPresenter implements BindAccountContact.AliPresenter {
    private final DataSource dataRepository;
    private final BindAccountContact.AliView view;

    public BindAliPresenter(DataSource dataRepository, BindAccountContact.AliView view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getBindAli(String token, String ali, String jyPassword, String realName, String qrCodeUrl) {
        view.displayLoadingPopup();
        dataRepository.getBindAli(token,ali,jyPassword,realName,qrCodeUrl, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.getBindAliSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.getBindAliFail(code,toastMessage);
            }
        });
    }

    @Override
    public void getUpdateAli(String token, String ali, String jyPassword, String realName, String qrCodeUrl) {
        view.displayLoadingPopup();
        dataRepository.getUpdateAli(token,ali,jyPassword,realName,qrCodeUrl, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.getUpdateAliSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.getUpdateAliFail(code,toastMessage);
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
