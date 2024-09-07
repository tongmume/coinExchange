package com.bizzan.ui.credit;


import com.bizzan.data.DataSource;

/**
 * Created by Administrator on 2017/9/25.
 */

public class CreditInfoPresenter implements CreditContract.InfoPresenter {
    private final DataSource dataRepository;
    private final CreditContract.InfoView view;

    public CreditInfoPresenter(DataSource dataRepository, CreditContract.InfoView view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }



    @Override
    public void commitRealName(String token, String realName, String idCard, String idCardFront, String idCardBack, String handHeldIdCard) {
        view.displayLoadingPopup();
        dataRepository.name(token, realName, idCard, idCardFront, idCardBack, handHeldIdCard, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.commitSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.commitError(code, toastMessage);
            }
        });
    }


}
