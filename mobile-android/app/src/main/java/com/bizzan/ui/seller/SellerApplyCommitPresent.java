package com.bizzan.ui.seller;

import com.bizzan.data.DataSource;
import com.bizzan.entity.DepositInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class SellerApplyCommitPresent implements SellerApplyCommitContract.Presenter {

    private final DataSource dataRepository;
    private final SellerApplyCommitContract.View view;

    public SellerApplyCommitPresent(DataSource dataRepository, SellerApplyCommitContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }


    @Override
    public void commitSellerApply(String token, String coinId, String json ) {

        dataRepository.commitSellerApply(token, coinId, json, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.commitSellerApplySuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.commitSellerApplyFail(toastMessage);
            }
        });
    }

    @Override
    public void getDepositList(String token) {
        dataRepository.getDepositList(token, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.getDepositListSuccess((List<DepositInfo>) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.getDepositListFail(  toastMessage);
            }
        });
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


}
