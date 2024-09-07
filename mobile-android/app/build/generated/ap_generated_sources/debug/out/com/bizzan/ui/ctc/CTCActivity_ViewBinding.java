// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.ctc;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CTCActivity_ViewBinding implements Unbinder {
  private CTCActivity target;

  @UiThread
  public CTCActivity_ViewBinding(CTCActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CTCActivity_ViewBinding(CTCActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.llBuy = Utils.findRequiredViewAsType(source, R.id.llBuy, "field 'llBuy'", LinearLayout.class);
    target.llSell = Utils.findRequiredViewAsType(source, R.id.llSell, "field 'llSell'", LinearLayout.class);
    target.tabBuy = Utils.findRequiredViewAsType(source, R.id.tabBuy, "field 'tabBuy'", LinearLayout.class);
    target.tabSell = Utils.findRequiredViewAsType(source, R.id.tabSell, "field 'tabSell'", LinearLayout.class);
    target.buyPrice = Utils.findRequiredViewAsType(source, R.id.buyPrice, "field 'buyPrice'", TextView.class);
    target.buyAmount = Utils.findRequiredViewAsType(source, R.id.buyAmount, "field 'buyAmount'", EditText.class);
    target.llPayTypeBuy = Utils.findRequiredViewAsType(source, R.id.llPayTypeBuy, "field 'llPayTypeBuy'", LinearLayout.class);
    target.tvPayTypeBuy = Utils.findRequiredViewAsType(source, R.id.tvPayTypeBuy, "field 'tvPayTypeBuy'", TextView.class);
    target.tvBuyTotal = Utils.findRequiredViewAsType(source, R.id.tvBuyTotal, "field 'tvBuyTotal'", TextView.class);
    target.btnBuy = Utils.findRequiredViewAsType(source, R.id.btnBuy, "field 'btnBuy'", Button.class);
    target.tv_buy_tab = Utils.findRequiredViewAsType(source, R.id.tv_buy_tab, "field 'tv_buy_tab'", TextView.class);
    target.buy_tab_underline = Utils.findRequiredView(source, R.id.buy_tab_underline, "field 'buy_tab_underline'");
    target.sellPrice = Utils.findRequiredViewAsType(source, R.id.sellPrice, "field 'sellPrice'", TextView.class);
    target.sellAmount = Utils.findRequiredViewAsType(source, R.id.sellAmount, "field 'sellAmount'", EditText.class);
    target.llPayTypeSell = Utils.findRequiredViewAsType(source, R.id.llPayTypeSell, "field 'llPayTypeSell'", LinearLayout.class);
    target.tvPayTypeSell = Utils.findRequiredViewAsType(source, R.id.tvPayTypeSell, "field 'tvPayTypeSell'", TextView.class);
    target.tvSellTotal = Utils.findRequiredViewAsType(source, R.id.tvSellTotal, "field 'tvSellTotal'", TextView.class);
    target.btnSell = Utils.findRequiredViewAsType(source, R.id.btnSell, "field 'btnSell'", Button.class);
    target.tv_sell_tab = Utils.findRequiredViewAsType(source, R.id.tv_sell_tab, "field 'tv_sell_tab'", TextView.class);
    target.sell_tab_underline = Utils.findRequiredView(source, R.id.sell_tab_underline, "field 'sell_tab_underline'");
    target.rvDetail = Utils.findRequiredViewAsType(source, R.id.rvDetail, "field 'rvDetail'", RecyclerView.class);
    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refreshLayout, "field 'refreshLayout'", SwipeRefreshLayout.class);
    target.tvTradeKnow = Utils.findRequiredViewAsType(source, R.id.tvTradeKnow, "field 'tvTradeKnow'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CTCActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.view_back = null;
    target.llTitle = null;
    target.llBuy = null;
    target.llSell = null;
    target.tabBuy = null;
    target.tabSell = null;
    target.buyPrice = null;
    target.buyAmount = null;
    target.llPayTypeBuy = null;
    target.tvPayTypeBuy = null;
    target.tvBuyTotal = null;
    target.btnBuy = null;
    target.tv_buy_tab = null;
    target.buy_tab_underline = null;
    target.sellPrice = null;
    target.sellAmount = null;
    target.llPayTypeSell = null;
    target.tvPayTypeSell = null;
    target.tvSellTotal = null;
    target.btnSell = null;
    target.tv_sell_tab = null;
    target.sell_tab_underline = null;
    target.rvDetail = null;
    target.refreshLayout = null;
    target.tvTradeKnow = null;
  }
}
