package com.bizzan.ui.setting;

import com.bizzan.data.DataSource;
import com.bizzan.entity.Vision;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class SettingPresent implements SettingContact.Presenter {

    private final DataSource dataRepository;
    private final SettingContact.View view;

    public SettingPresent(DataSource dataRepository, SettingContact.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getNewVision(String token) {
        view.displayLoadingPopup();
        dataRepository.getNewVision(token, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.getNewVisionSuccess((Vision) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.getNewVisionFail(code, toastMessage);
            }
        });
    }

}
