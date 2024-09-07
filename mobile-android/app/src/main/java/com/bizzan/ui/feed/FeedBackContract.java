package com.bizzan.ui.feed;


import com.bizzan.base.Contract;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface FeedBackContract {

    interface View extends Contract.BaseView<Presenter> {

        void remarkSuccess(String obj);

        void remarkFail(Integer code, String toastMessage);
    }

    interface Presenter extends Contract.BasePresenter {

        void remark(String token, String remark);

    }
}
