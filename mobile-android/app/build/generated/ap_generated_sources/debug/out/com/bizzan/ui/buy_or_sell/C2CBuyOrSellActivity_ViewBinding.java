// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.buy_or_sell;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class C2CBuyOrSellActivity_ViewBinding implements Unbinder {
  private C2CBuyOrSellActivity target;

  @UiThread
  public C2CBuyOrSellActivity_ViewBinding(C2CBuyOrSellActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public C2CBuyOrSellActivity_ViewBinding(C2CBuyOrSellActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.ivHeader = Utils.findRequiredViewAsType(source, R.id.ivHeader, "field 'ivHeader'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tvName, "field 'tvName'", TextView.class);
    target.tvLimit = Utils.findRequiredViewAsType(source, R.id.tvLimit, "field 'tvLimit'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tvPrice, "field 'tvPrice'", TextView.class);
    target.tvExchangeCount = Utils.findRequiredViewAsType(source, R.id.tvExchangeCount, "field 'tvExchangeCount'", TextView.class);
    target.tvCommentPercent = Utils.findRequiredViewAsType(source, R.id.tvCommentPercent, "field 'tvCommentPercent'", TextView.class);
    target.tvDoneHistory = Utils.findRequiredViewAsType(source, R.id.tvDoneHistory, "field 'tvDoneHistory'", TextView.class);
    target.tvMessage = Utils.findRequiredViewAsType(source, R.id.tvMessage, "field 'tvMessage'", TextView.class);
    target.tvLocalCoinText = Utils.findRequiredViewAsType(source, R.id.tvLocalCoinText, "field 'tvLocalCoinText'", TextView.class);
    target.etLocalCoin = Utils.findRequiredViewAsType(source, R.id.etLocalCoin, "field 'etLocalCoin'", EditText.class);
    target.tvOtherCoinText = Utils.findRequiredViewAsType(source, R.id.tvOtherCoinText, "field 'tvOtherCoinText'", TextView.class);
    target.etOtherCoin = Utils.findRequiredViewAsType(source, R.id.etOtherCoin, "field 'etOtherCoin'", EditText.class);
    target.tvBuy = Utils.findRequiredViewAsType(source, R.id.tvBuy, "field 'tvBuy'", TextView.class);
    target.tvInfo = Utils.findRequiredViewAsType(source, R.id.tvInfo, "field 'tvInfo'", TextView.class);
    target.tvMyOrder = Utils.findRequiredViewAsType(source, R.id.tvMyOrder, "field 'tvMyOrder'", TextView.class);
    target.ivZhifubao = Utils.findRequiredViewAsType(source, R.id.ivZhifubao, "field 'ivZhifubao'", ImageView.class);
    target.ivWeChat = Utils.findRequiredViewAsType(source, R.id.ivWeChat, "field 'ivWeChat'", ImageView.class);
    target.ivUnionPay = Utils.findRequiredViewAsType(source, R.id.ivUnionPay, "field 'ivUnionPay'", ImageView.class);
    target.tvRemainAmount = Utils.findRequiredViewAsType(source, R.id.tvRemainAmount, "field 'tvRemainAmount'", TextView.class);
    target.tvTradeAmount = Utils.findRequiredViewAsType(source, R.id.tvTradeAmount, "field 'tvTradeAmount'", TextView.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    C2CBuyOrSellActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.tvTitle = null;
    target.llTitle = null;
    target.ivHeader = null;
    target.tvName = null;
    target.tvLimit = null;
    target.tvPrice = null;
    target.tvExchangeCount = null;
    target.tvCommentPercent = null;
    target.tvDoneHistory = null;
    target.tvMessage = null;
    target.tvLocalCoinText = null;
    target.etLocalCoin = null;
    target.tvOtherCoinText = null;
    target.etOtherCoin = null;
    target.tvBuy = null;
    target.tvInfo = null;
    target.tvMyOrder = null;
    target.ivZhifubao = null;
    target.ivWeChat = null;
    target.ivUnionPay = null;
    target.tvRemainAmount = null;
    target.tvTradeAmount = null;
    target.view_back = null;
  }
}
