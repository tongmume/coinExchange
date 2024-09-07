package com.bizzan.ui.home.presenter;

import com.bizzan.ui.home.MainContract;
import com.bizzan.data.DataSource;

/**
 * Created by Administrator on 2018/2/24.
 */

public class TwoPresenter implements MainContract.TwoPresenter {
    private MainContract.TwoView view;
    private DataSource dataRepository;

    public TwoPresenter(DataSource dataRepository, MainContract.TwoView view) {
        this.view = view;
        this.dataRepository = dataRepository;
        this.view.setPresenter(this);
    }


}
