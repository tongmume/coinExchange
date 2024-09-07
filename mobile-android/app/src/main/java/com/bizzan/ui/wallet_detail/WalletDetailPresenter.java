package com.bizzan.ui.wallet_detail;


import android.util.Log;

import com.bizzan.data.DataSource;
import com.bizzan.entity.WalletDetailNew;

/**
 * Created by Administrator on 2017/9/25.
 */

public class WalletDetailPresenter implements WalletDetailContract.Presenter {
    private final DataSource dataRepository;
    private final WalletDetailContract.View view;

    public WalletDetailPresenter(DataSource dataRepository, WalletDetailContract.View view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void allTransaction(String token, int pageNo, int limit,int memberId,String startTime,String endTime,String symbol,String type) {
        view.displayLoadingPopup();
        Log.i("allTransaction","token-"+token+",-pageNo-"+pageNo+",-limit-"+limit+",-memberId-"+memberId+",-startTime-"+startTime+",-endTime-"+endTime+",-symbol-"+symbol+",-type-"+type);
        dataRepository.allTransaction(token, pageNo, limit, memberId,startTime,endTime,symbol,type, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.allTransactionSuccess((WalletDetailNew) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.allTransactionFail(code, toastMessage);

            }
        });
    }
}
