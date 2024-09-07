package com.bizzan.ui.bind_account;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.adapter.BankNameAdapter;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.AccountSetting;
import com.bizzan.entity.BankName;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.bizzan.app.Injection;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class BindBankActivity extends BaseActivity implements BindAccountContact.BankView {


    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.etName)
    EditText etName;

    @BindView(R.id.llBank)
    LinearLayout llBank;
    @BindView(R.id.etBranch)
    EditText etBranch;
    @BindView(R.id.llBranch)
    LinearLayout llBranch;
    @BindView(R.id.tvAccount)
    TextView tvAccount;
    @BindView(R.id.etAccount)
    EditText etAccount;
    @BindView(R.id.etConfirmAccount)
    EditText etConfirmAccount;
    @BindView(R.id.llConfirmAccount)
    LinearLayout llConfirmAccount;
    @BindView(R.id.etNewPwd)
    EditText etNewPwd;
    @BindView(R.id.tvConfirm)
    TextView tvConfirm;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.tvBank)
    TextView tvBank;
    @BindView(R.id.view_back)
    View view_back;

    private BindAccountContact.BankPresenter presenter;
    private AccountSetting setting;

    private View coinInfoView;
    private RecyclerView rvCoinInfo;
    private BankNameAdapter adapter;
    private AlertDialog dialog;
    private String[] bankNames;
    private List<String> nameList = new ArrayList<>();
    private BankName bankName;

    public static void actionStart(Context context, AccountSetting accountSetting) {
        Intent intent = new Intent(context, BindBankActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("accountSetting", accountSetting);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_bind_bank;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        new BindBankPresenter(Injection.provideTasksRepository(getApplicationContext()), this);
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setting = (AccountSetting) getIntent().getSerializableExtra("accountSetting");
        etName.setText(setting.getRealName());
        etName.setEnabled(false);
        if (setting.getBankVerified() == 1) {
            tvBank.setText(setting.getBankInfo().getBank());
            etBranch.setText(setting.getBankInfo().getBranch());
            etAccount.setText(setting.getBankInfo().getCardNo());
            tvTitle.setText(WonderfulToastUtils.getString(this,R.string.text_change) + WonderfulToastUtils.getString(this,R.string.unionpay_account));
        } else
            tvTitle.setText(WonderfulToastUtils.getString(this,R.string.binding) + WonderfulToastUtils.getString(this,R.string.unionpay_account));
        bankNames = getResources().getStringArray(R.array.bank_name);
        for (int i = 0; i < bankNames.length; i++) {
            BankName name = new BankName();
            name.setBankName(bankNames[i]);
            nameList.add(name.getBankName());
        }
        llBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm();
            }
        });
    }

    private void confirm() {
        String name = etName.getText().toString();
        String bank = tvBank.getText().toString();
        String branch = etBranch.getText().toString();
        String account = etAccount.getText().toString();
        String reaccount = etConfirmAccount.getText().toString();
        String pwd = etNewPwd.getText().toString();
        if (!WonderfulStringUtils.isEmpty(name, bank, branch, account, reaccount, pwd)) {
            if (account.equals(reaccount)) {
                if (setting.getAliVerified() == 1) {
                    presenter.getUpdateBank(getToken(), bank, branch, pwd, name, account);
                } else presenter.getBindBank(getToken(), bank, branch, pwd, name, account);
            } else
                WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.diff_cardnumber));
        } else
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.Incomplete_information));
    }

    private void showDialog() {
//        if (coinInfoView == null) {
//            coinInfoView = getLayoutInflater().inflate(R.layout.dialog_coins_view, null);
//            rvCoinInfo = coinInfoView.findViewById(R.id.rvCoinList);
//            LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//            rvCoinInfo.setLayoutManager(manager);
//            adapter = new BankNameAdapter(R.layout.adapter_dialog_coininfo,nameList);
//            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                    ItemClick(position);
//                }
//            });
//            rvCoinInfo.setAdapter(adapter);
//            dialog = new AlertDialog.Builder(this).setTitle(WonderfulToastUtils.getString(this,R.string.bank)).setView(coinInfoView)
//                    .setPositiveButton(WonderfulToastUtils.getString(this,R.string.confirm), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Confirm();
//                        }
//                    }).setNegativeButton(WonderfulToastUtils.getString(this,R.string.cancle), null).create();
//        }
//        dialog.show();
        //条件选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(BindBankActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                tvBank.setText(nameList.get(options1));
            }
        }).setSubmitColor(Color.parseColor("#f0a70a"))//确定按钮文字颜色
                .setTitleText(WonderfulToastUtils.getString(this,R.string.bank)).setTitleColor(Color.WHITE)
                .setCancelColor(Color.parseColor("#666666"))
                .setTextColorCenter(Color.parseColor("#f0a70a"))
                .setTitleBgColor(getResources().getColor(R.color.primaryBackNormal))
                .setBgColor(getResources().getColor(R.color.primaryBackNormal))
                .build();
        pvOptions.setPicker(nameList);//取消按钮文字颜色;
        pvOptions.show();
    }

//    private void ItemClick(int position) {
//        for (int i = 0, len = nameList.size(); i < len; i++) {
//            nameList.get(i).setSelected(false);
//        }
//        nameList.get(position).setSelected(true);
//        adapter.notifyDataSetChanged();
//    }
//
//    private void Confirm() {
//        for (BankName bankName : nameList) {
//            if (bankName.isSelected()) {
//                this.bankName = bankName;
//                tvBank.setText(bankName.getBankName());
//                break;
//            }
//        }
//    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setPresenter(BindAccountContact.BankPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getBindBankSuccess(String obj) {
        WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.Upload_success));
        finish();
    }

    @Override
    public void getBindBankFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void getUpdateBankSuccess(String obj) {
        WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.Upload_success));
        finish();
    }

    @Override
    public void getUpdateBankFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            ImmersionBar.setTitleBar(this, llTitle);
            isSetTitle = true;
        }
    }
}
