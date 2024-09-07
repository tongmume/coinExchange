// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.wallet;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WalletActivity_ViewBinding implements Unbinder {
  private WalletActivity target;

  @UiThread
  public WalletActivity_ViewBinding(WalletActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WalletActivity_ViewBinding(WalletActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.ibDetail = Utils.findRequiredViewAsType(source, R.id.ibDetail, "field 'ibDetail'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.tvAmount = Utils.findRequiredViewAsType(source, R.id.tvAmount, "field 'tvAmount'", TextView.class);
    target.tvCnyAmount = Utils.findRequiredViewAsType(source, R.id.tvCnyAmount, "field 'tvCnyAmount'", TextView.class);
    target.llAccount = Utils.findRequiredViewAsType(source, R.id.llAccount, "field 'llAccount'", LinearLayout.class);
    target.rvWallet = Utils.findRequiredViewAsType(source, R.id.rvWallet, "field 'rvWallet'", RecyclerView.class);
    target.ivSee = Utils.findRequiredViewAsType(source, R.id.ivSee, "field 'ivSee'", ImageView.class);
    target.llContainer = Utils.findRequiredViewAsType(source, R.id.llContainer, "field 'llContainer'", LinearLayout.class);
    target.ivSearch = Utils.findRequiredViewAsType(source, R.id.ivSearch, "field 'ivSearch'", ImageView.class);
    target.evSearch = Utils.findRequiredViewAsType(source, R.id.evSearch, "field 'evSearch'", EditText.class);
    target.cbHide = Utils.findRequiredViewAsType(source, R.id.cbHide, "field 'cbHide'", CheckBox.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
    target.textToAll = Utils.findRequiredViewAsType(source, R.id.text_to_all, "field 'textToAll'", RelativeLayout.class);
    target.llCoins = Utils.findRequiredViewAsType(source, R.id.ll_coins, "field 'llCoins'", LinearLayout.class);
    target.rvWalletConstract = Utils.findRequiredViewAsType(source, R.id.rvWallet_constract, "field 'rvWalletConstract'", RecyclerView.class);
    target.llConstract = Utils.findRequiredViewAsType(source, R.id.ll_constract, "field 'llConstract'", LinearLayout.class);
    target.llCoinsTab = Utils.findRequiredViewAsType(source, R.id.ll_coins_tab, "field 'llCoinsTab'", LinearLayout.class);
    target.llConstractTab = Utils.findRequiredViewAsType(source, R.id.ll_constract_tab, "field 'llConstractTab'", LinearLayout.class);
    target.tvCoins = Utils.findRequiredViewAsType(source, R.id.tv_coins, "field 'tvCoins'", TextView.class);
    target.coinsUnderline = Utils.findRequiredView(source, R.id.coins_underline, "field 'coinsUnderline'");
    target.tvConstract = Utils.findRequiredViewAsType(source, R.id.tv_constract, "field 'tvConstract'", TextView.class);
    target.constractUnderline = Utils.findRequiredView(source, R.id.constract_underline, "field 'constractUnderline'");
    target.tvCharge = Utils.findRequiredViewAsType(source, R.id.tv_charge, "field 'tvCharge'", TextView.class);
    target.tvMention = Utils.findRequiredViewAsType(source, R.id.tv_Mention, "field 'tvMention'", TextView.class);
    target.txRightBt = Utils.findRequiredViewAsType(source, R.id.tx_right_bt, "field 'txRightBt'", TextView.class);
    target.llMid = Utils.findRequiredViewAsType(source, R.id.ll_mid, "field 'llMid'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WalletActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.ibDetail = null;
    target.llTitle = null;
    target.tvAmount = null;
    target.tvCnyAmount = null;
    target.llAccount = null;
    target.rvWallet = null;
    target.ivSee = null;
    target.llContainer = null;
    target.ivSearch = null;
    target.evSearch = null;
    target.cbHide = null;
    target.view_back = null;
    target.textToAll = null;
    target.llCoins = null;
    target.rvWalletConstract = null;
    target.llConstract = null;
    target.llCoinsTab = null;
    target.llConstractTab = null;
    target.tvCoins = null;
    target.coinsUnderline = null;
    target.tvConstract = null;
    target.constractUnderline = null;
    target.tvCharge = null;
    target.tvMention = null;
    target.txRightBt = null;
    target.llMid = null;
  }
}
