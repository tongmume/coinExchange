// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.dialog;

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

public class BBConfirmDialogFragment_ViewBinding implements Unbinder {
  private BBConfirmDialogFragment target;

  @UiThread
  public BBConfirmDialogFragment_ViewBinding(BBConfirmDialogFragment target, View source) {
    this.target = target;

    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tvPrice, "field 'tvPrice'", TextView.class);
    target.ll1 = Utils.findRequiredViewAsType(source, R.id.ll1, "field 'll1'", LinearLayout.class);
    target.tvTotal = Utils.findRequiredViewAsType(source, R.id.tvTotal, "field 'tvTotal'", TextView.class);
    target.tvAmount = Utils.findRequiredViewAsType(source, R.id.tvAmount, "field 'tvAmount'", TextView.class);
    target.ll2 = Utils.findRequiredViewAsType(source, R.id.ll2, "field 'll2'", LinearLayout.class);
    target.tvType = Utils.findRequiredViewAsType(source, R.id.tvType, "field 'tvType'", TextView.class);
    target.tvConfirm = Utils.findRequiredViewAsType(source, R.id.tvConfirm, "field 'tvConfirm'", TextView.class);
    target.tvCancle = Utils.findRequiredViewAsType(source, R.id.tvCancle, "field 'tvCancle'", TextView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BBConfirmDialogFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvPrice = null;
    target.ll1 = null;
    target.tvTotal = null;
    target.tvAmount = null;
    target.ll2 = null;
    target.tvType = null;
    target.tvConfirm = null;
    target.tvCancle = null;
    target.tvTitle = null;
  }
}
