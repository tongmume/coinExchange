package com.bizzan.ui.credit;


import com.bizzan.data.DataSource;
import com.bizzan.entity.Credit;

/**
 * Created by Administrator on 2017/9/25.
 */

public class CreditPresenter implements CreditContract.Presenter {
    private final DataSource dataRepository;
    private final CreditContract.View view;

    public CreditPresenter(DataSource dataRepository, CreditContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void uploadBase64Pic(String token, String base64Data, final int type) {
        view.displayLoadingPopup();
        dataRepository.uploadBase64Pic(token, base64Data, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.uploadBase64PicSuccess((String) obj, type);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.uploadBase64PicFail(code, toastMessage);

            }
        });
    }

    @Override
    public void name(String token, String realName, String idCard, String idCardFront, String idCardBack, String handHeldIdCard) {
        view.displayLoadingPopup();
        dataRepository.name(token, realName, idCard, idCardFront, idCardBack, handHeldIdCard, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.nameSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.nameFail(code, toastMessage);
            }
        });
    }

    @Override
    public void getCreditInfo(String token) {
        view.displayLoadingPopup();
        dataRepository.getCreditInfo(token, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.getCreditInfoSuccess((Credit.DataBean) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.getCreditInfoFail(code, toastMessage);

            }
        });
    }
}
