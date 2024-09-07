package com.bizzan.ui.appeal;


import com.bizzan.data.DataSource;

/**
 * Created by Administrator on 2017/9/25.
 */

public class AppealPresenter implements AppealContract.Presenter {
    private final DataSource dataRepository;
    private final AppealContract.View view;

    public AppealPresenter(DataSource dataRepository, AppealContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void appeal(String token, String remark, String orderSn) {
        view.displayLoadingPopup();
        dataRepository.appeal(token, remark, orderSn, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.appealSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.appealFail(code, toastMessage);
            }
        });
    }
}
