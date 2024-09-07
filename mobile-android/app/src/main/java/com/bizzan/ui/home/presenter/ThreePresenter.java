package com.bizzan.ui.home.presenter;

import com.bizzan.ui.home.MainContract;
import com.bizzan.data.DataSource;

/**
 * Created by Administrator on 2018/2/24.
 */

public class ThreePresenter implements MainContract.ThreePresenter {
    private MainContract.ThreeView view;
    private DataSource dataRepository;

    public ThreePresenter(DataSource dataRepository, MainContract.ThreeView view) {
        this.view = view;
        this.dataRepository = dataRepository;
        this.view.setPresenter(this);
    }

}
