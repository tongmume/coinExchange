package com.bizzan.ui.aboutus;


import com.bizzan.data.DataSource;
import com.bizzan.entity.AppInfo;

/**
 * Created by Administrator on 2017/9/25.
 */

public class AboutUsPresenter implements AboutUsContract.Presenter {
    private final DataSource dataRepository;
    private final AboutUsContract.View view;

    public AboutUsPresenter(DataSource dataRepository, AboutUsContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void appInfo() {
        view.displayLoadingPopup();
        dataRepository.appInfo(new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.appInfoSuccess((AppInfo) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.appInfoFail(code, toastMessage);

            }
        });
    }
}
