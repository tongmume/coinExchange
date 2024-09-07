package com.bizzan.base;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface Contract {
    interface BasePresenter {

    }

    interface BaseView<T> {
        void setPresenter(T presenter);
        void hideLoadingPopup();
        void displayLoadingPopup();

    }
}
