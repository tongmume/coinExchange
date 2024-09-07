package com.bizzan.ui.wallet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import com.bizzan.R;
import com.bizzan.app.Injection;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.WalletConstract;
import com.bizzan.entity.Wallet_Coin;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulToastUtils;

//划转
public class OverturnActivity extends BaseActivity implements OverturnContract.View {

    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.view_back)
    View viewBack;
    @BindView(R.id.ibDetail)
    TextView ibDetail;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.tvc)
    TextView tvc;
    @BindView(R.id.tvd)
    TextView tvd;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.ll_switch)
    LinearLayout llSwitch;
    @BindView(R.id.tv_coins)
    TextView tvCoins;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.tv_coin)
    TextView tvCoin;
    @BindView(R.id.edit_number)
    EditText editNumber;
    @BindView(R.id.tv_top1)
    TextView tvTop1;
    @BindView(R.id.tv_top2)
    TextView tvTop2;
    @BindView(R.id.tv_bottom1)
    TextView tvBottom1;
    @BindView(R.id.tv_bottom2)
    TextView tvBottom2;
    @BindView(R.id.iv_top)
    ImageView ivTop;
    @BindView(R.id.iv_bottom)
    ImageView ivBottom;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.rl_boottom)
    RelativeLayout rlBoottom;
    @BindView(R.id.rl_coin)
    RelativeLayout rlCoin;
    @BindView(R.id.tv_coin_account)
    TextView tvCoinAccount;

    private int switch_type = 1;//表示币币在上面  2表示币币现在在下面
    private int coin_type = 0;//获取现在显示的币种是列表的第几个
    private OverturnContract.Presenter presenter;
    private List<WalletConstract> constracts = new ArrayList<>();
    private Wallet_Coin coin;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, OverturnActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_overturn;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        new OverturnPresenter(Injection.provideTasksRepository(getApplicationContext()), this);
        rlTop.setEnabled(false);
        tvTop2.setText(getResources().getText(R.string.tv_balance) + ": 0.0000 USDT");
        tvBottom2.setText(getResources().getText(R.string.tv_balance) + ": 0.0000 USDT");
        tvCoinAccount.setText(getResources().getText(R.string.constract)+""+getResources().getText(R.string.contract)+""+getResources().getText(R.string.tv_currency));
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {
        presenter.myWalletUsdt(getToken());
        presenter.myWalletList(getToken());
    }

    @Override
    public void setPresenter(OverturnContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            ImmersionBar.setTitleBar(this, llTitle);
            isSetTitle = true;
        }
    }

    //点击事件
    @OnClick({R.id.ibBack, R.id.ll_switch, R.id.rl_top, R.id.rl_boottom, R.id.tv_all, R.id.rl_coin, R.id.tvOverturn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibBack:
                finish();
                break;
            case R.id.ll_switch://上下切换账户
                if (switch_type == 1) {//币币切换到下面
                    tvTop1.setText(WonderfulToastUtils.getString(this, R.string.tv_constract_account));
                    tvBottom1.setText(WonderfulToastUtils.getString(this, R.string.tv_coins) + "(USDT)");
                    ivTop.setVisibility(View.VISIBLE);
                    ivBottom.setVisibility(View.GONE);
                    rlTop.setEnabled(true);
                    rlBoottom.setEnabled(false);
                    editNumber.setText("");
                    switch_type = 2;
                    getCoin(coin);
                    getConstract(constracts);
                } else if (switch_type == 2) {//币币切换到上面
                    tvTop1.setText(WonderfulToastUtils.getString(this, R.string.tv_coins) + "(USDT)");
                    tvBottom1.setText(WonderfulToastUtils.getString(this, R.string.tv_constract_account));
                    ivTop.setVisibility(View.GONE);
                    ivBottom.setVisibility(View.VISIBLE);
                    rlTop.setEnabled(false);
                    rlBoottom.setEnabled(true);
                    editNumber.setText("");
                    switch_type = 1;
                    getCoin(coin);
                    getConstract(constracts);
                }

                break;
            case R.id.rl_top:
                SelectAccountActivity.actionStart(this, constracts);
                break;
            case R.id.rl_boottom:
                SelectAccountActivity.actionStart(this, constracts);
                break;
            case R.id.tv_all:
                //点击全部
                if (switch_type == 1) {
                    editNumber.setText(coin.getBalance() + "");
                } else if (switch_type == 2) {
                    for (int i = 0; i < constracts.size(); i++) {
                        if (constracts.get(i).getContractCoin().getCoinSymbol().equals(tvCoins.getText().toString().trim())) {
                            editNumber.setText(constracts.get(i).getUsdtBalance() + "");
                            return;
                        }
                    }
                }
                break;
            case R.id.rl_coin:
                SelectCoinActivity.actionStart(this, constracts);
                break;
            case R.id.tvOverturn:
                String trim = editNumber.getText().toString().trim();
                if (!trim.equals("")) {
                    if (switch_type == 1) {
                        presenter.RequesTrans(getToken(), "USDT", "0", "1", coin.getId() + "", constracts.get(coin_type).getId() + "", trim);
                    } else if (switch_type == 2) {
                        presenter.RequesTrans(getToken(), "USDT", "1", "0", constracts.get(coin_type).getId() + "", coin.getId() + "", trim);
                    }
                } else {
                    WonderfulToastUtils.showToast(getResources().getText(R.string.tv_input_overturn_number) + "");
                }
                break;
        }
    }

//    unit:USDT
//    from:0
//    to:1
//    fromWalletId:9113
//    toWalletId:504
//    amount:1

    @Override
    public void myWalletUsdtSuccess(Wallet_Coin obj) {
        coin = obj;
        getCoin(coin);
    }

    @Override
    public void myWalletListSuccess(List<WalletConstract> obj) {
        constracts.addAll(obj);
        tvCoins.setText(constracts.get(0).getContractCoin().getCoinSymbol());
        getConstract(constracts);
    }

    @Override
    public void myTransSuccess(String obj) {
        Toast.makeText(this, "" + obj, Toast.LENGTH_SHORT).show();
        editNumber.setText("");
        presenter.myWalletUsdt(getToken());
        presenter.myWalletList(getToken());
    }

    @Override
    public void myWalletFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    //刷新币币资产
    private void getCoin(Wallet_Coin coin) {
        if (switch_type == 1) {
            tvTop2.setText(getResources().getText(R.string.tv_balance) + ": " + ProcessPrice(coin.getBalance()) + " USDT");
        } else if (switch_type == 2) {
            tvBottom2.setText(getResources().getText(R.string.tv_balance) + ": " + ProcessPrice(coin.getBalance()) + " USDT");
        }
    }

    //刷新其他账户资产
    private void getConstract(List<WalletConstract> constracts) {
        for (int i = 0; i < constracts.size(); i++) {
            if (constracts.get(i).getContractCoin().getCoinSymbol().equals(tvCoins.getText().toString().trim())) {
                if (switch_type == 1) {
                    tvBottom2.setText(getResources().getText(R.string.tv_balance) + ": " + ProcessPrice(constracts.get(i).getUsdtBalance()) + " USDT");
                    coin_type = i;
                } else if (switch_type == 2) {
                    tvTop2.setText(getResources().getText(R.string.tv_balance) + ": " + ProcessPrice(constracts.get(i).getUsdtBalance()) + " USDT");
                    coin_type = i;
                }
            }
        }
    }

    private String ProcessPrice(double Price) {
        BigDecimal bigDecimal = new BigDecimal(Price);
        return bigDecimal.setScale(2, BigDecimal.ROUND_DOWN).toPlainString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 传过来的币种
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void WalletMessage(WalletMessage coin) {
        tvCoins.setText(coin.getCoin());
        getConstract(constracts);
    }

}