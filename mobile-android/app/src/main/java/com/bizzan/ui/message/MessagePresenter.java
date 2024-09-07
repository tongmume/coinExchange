package com.bizzan.ui.message;


import com.bizzan.data.DataSource;
import com.bizzan.entity.Message;

import java.util.List;

/**
 * Created by Administrator on 2017/9/25.
 */

public class MessagePresenter implements MessageContract.Presenter {
    private final DataSource dataRepository;
    private final MessageContract.View view;

    public MessagePresenter(DataSource dataRepository, MessageContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void message(int pageNo, int pageSize) {
        view.displayLoadingPopup();
        dataRepository.message(pageNo, pageSize, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.messageSuccess((List<Message>) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.messageFail(code, toastMessage);

            }
        });
    }
}
