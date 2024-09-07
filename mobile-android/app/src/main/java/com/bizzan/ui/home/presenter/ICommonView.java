package com.bizzan.ui.home.presenter;

/**
 * Created by Administrator on 2018/2/25.
 */

public interface ICommonView {
    void setPresenter(CommonPresenter presenter);

    void deleteSuccess(String obj, int position);

    void deleteFail(Integer code, String toastMessage);

    void addSuccess(String obj, int position);

    void addFail(Integer code, String toastMessage);
}
