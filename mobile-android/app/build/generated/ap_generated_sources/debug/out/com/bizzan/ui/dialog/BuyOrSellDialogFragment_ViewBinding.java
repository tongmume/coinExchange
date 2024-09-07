// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BuyOrSellDialogFragment_ViewBinding implements Unbinder {
  private BuyOrSellDialogFragment target;

  @UiThread
  public BuyOrSellDialogFragment_ViewBinding(BuyOrSellDialogFragment target, View source) {
    this.target = target;

    target.ivBuy = Utils.findRequiredViewAsType(source, R.id.ivBuy, "field 'ivBuy'", ImageView.class);
    target.tvBuy = Utils.findRequiredViewAsType(source, R.id.tvBuy, "field 'tvBuy'", TextView.class);
    target.ivSell = Utils.findRequiredViewAsType(source, R.id.ivSell, "field 'ivSell'", ImageView.class);
    target.tvSell = Utils.findRequiredViewAsType(source, R.id.tvSell, "field 'tvSell'", TextView.class);
    target.tvCancle = Utils.findRequiredViewAsType(source, R.id.tvCancle, "field 'tvCancle'", TextView.class);
    target.rlContent = Utils.findRequiredViewAsType(source, R.id.rlContent, "field 'rlContent'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BuyOrSellDialogFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBuy = null;
    target.tvBuy = null;
    target.ivSell = null;
    target.tvSell = null;
    target.tvCancle = null;
    target.rlContent = null;
  }
}
