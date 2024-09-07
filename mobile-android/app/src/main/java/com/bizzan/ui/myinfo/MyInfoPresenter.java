package com.bizzan.ui.myinfo;


import com.bizzan.data.DataSource;
import com.bizzan.entity.SafeSetting;

/**
 * Created by Administrator on 2017/9/25.
 */

public class MyInfoPresenter implements MyInfoContract.Presenter {
    private final DataSource dataRepository;
    private final MyInfoContract.View view;

    public MyInfoPresenter(DataSource dataRepository, MyInfoContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void safeSetting(String token) {
        view.displayLoadingPopup();
        dataRepository.safeSetting(token, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.safeSettingSuccess((SafeSetting) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.safeSettingFail(code, toastMessage);

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

    @Override
    public void avatar(String token, String url) {
        view.displayLoadingPopup();
        dataRepository.avatar(token, url, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.avatarSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.avatarFail(code, toastMessage);

            }
        });
    }
}
