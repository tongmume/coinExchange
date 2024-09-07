// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.buy_or_sell;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderConfirmDialogFragment_ViewBinding implements Unbinder {
  private OrderConfirmDialogFragment target;

  @UiThread
  public OrderConfirmDialogFragment_ViewBinding(OrderConfirmDialogFragment target, View source) {
    this.target = target;

    target.tvCancle = Utils.findRequiredViewAsType(source, R.id.tvCancle, "field 'tvCancle'", TextView.class);
    target.tvPriceText = Utils.findRequiredViewAsType(source, R.id.tvPriceText, "field 'tvPriceText'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tvPrice, "field 'tvPrice'", TextView.class);
    target.tvCountText = Utils.findRequiredViewAsType(source, R.id.tvCountText, "field 'tvCountText'", TextView.class);
    target.tvCount = Utils.findRequiredViewAsType(source, R.id.tvCount, "field 'tvCount'", TextView.class);
    target.tvTotalText = Utils.findRequiredViewAsType(source, R.id.tvTotalText, "field 'tvTotalText'", TextView.class);
    target.tvTotal = Utils.findRequiredViewAsType(source, R.id.tvTotal, "field 'tvTotal'", TextView.class);
    target.tvConfirm = Utils.findRequiredViewAsType(source, R.id.tvConfirm, "field 'tvConfirm'", TextView.class);
    target.llContent = Utils.findRequiredViewAsType(source, R.id.llContent, "field 'llContent'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderConfirmDialogFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvCancle = null;
    target.tvPriceText = null;
    target.tvPrice = null;
    target.tvCountText = null;
    target.tvCount = null;
    target.tvTotalText = null;
    target.tvTotal = null;
    target.tvConfirm = null;
    target.llContent = null;
  }
}
