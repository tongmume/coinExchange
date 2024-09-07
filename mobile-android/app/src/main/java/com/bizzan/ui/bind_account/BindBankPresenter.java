package com.bizzan.ui.bind_account;

import com.bizzan.data.DataSource;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class BindBankPresenter implements BindAccountContact.BankPresenter {
    private final DataSource dataRepository;
    private final BindAccountContact.BankView view;

    public BindBankPresenter(DataSource dataRepository, BindAccountContact.BankView view) {
        this.dataRepository = dataRepository;
        this.view = view;
        view.setPresenter(this);
    }


    @Override
    public void getBindBank(String token, String bank, String branch, String jyPassword, String realName, String cardNo) {
        view.displayLoadingPopup();
        dataRepository.getBindBank(token,bank,branch,jyPassword,realName,cardNo, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.getBindBankSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.getBindBankFail(code,toastMessage);
            }
        });
    }

    @Override
    public void getUpdateBank(String token, String bank, String branch, String jyPassword, String realName, String cardNo) {
        view.displayLoadingPopup();
        dataRepository.getUpdateBank(token,bank,branch,jyPassword,realName,cardNo, new DataSource.DataCallback() {
            @Override
            public void onDataLoaded(Object obj) {
                view.hideLoadingPopup();
                view.getUpdateBankSuccess((String) obj);
            }

            @Override
            public void onDataNotAvailable(Integer code, String toastMessage) {
                view.hideLoadingPopup();
                view.getUpdateBankFail(code,toastMessage);
            }
        });
    }
}
