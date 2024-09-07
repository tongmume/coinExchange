package com.bizzan.ui.feed;


import com.bizzan.data.DataSource;

/**
 * Created by Administrator on 2017/9/25.
 */

public class FeedBackPresenter implements FeedBackContract.Presenter {
    private final DataSource dataRepository;
    private final FeedBackContract.View view;

    public FeedBackPresenter(DataSource dataRepository, FeedBackContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void remark(String token, String remark) {
        view.displayLoadingPopup();
        dataRepository.remark(token, remark, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.remarkSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.remarkFail(code, toastMessage);

            }
        });
    }
}
