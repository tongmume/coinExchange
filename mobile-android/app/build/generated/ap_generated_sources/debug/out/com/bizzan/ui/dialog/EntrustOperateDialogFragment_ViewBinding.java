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

public class EntrustOperateDialogFragment_ViewBinding implements Unbinder {
  private EntrustOperateDialogFragment target;

  @UiThread
  public EntrustOperateDialogFragment_ViewBinding(EntrustOperateDialogFragment target,
      View source) {
    this.target = target;

    target.llContent = Utils.findRequiredViewAsType(source, R.id.llContent, "field 'llContent'", LinearLayout.class);
    target.ll1 = Utils.findRequiredViewAsType(source, R.id.ll1, "field 'll1'", LinearLayout.class);
    target.ll2 = Utils.findRequiredViewAsType(source, R.id.ll2, "field 'll2'", LinearLayout.class);
    target.tvCancle = Utils.findRequiredViewAsType(source, R.id.tvCancle, "field 'tvCancle'", TextView.class);
    target.tvType = Utils.findRequiredViewAsType(source, R.id.tvType, "field 'tvType'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tvPrice, "field 'tvPrice'", TextView.class);
    target.tvTotal = Utils.findRequiredViewAsType(source, R.id.tvTotal, "field 'tvTotal'", TextView.class);
    target.tvAmount = Utils.findRequiredViewAsType(source, R.id.tvAmount, "field 'tvAmount'", TextView.class);
    target.tvCancleOrder = Utils.findRequiredViewAsType(source, R.id.tvCancleOrder, "field 'tvCancleOrder'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EntrustOperateDialogFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llContent = null;
    target.ll1 = null;
    target.ll2 = null;
    target.tvCancle = null;
    target.tvType = null;
    target.tvPrice = null;
    target.tvTotal = null;
    target.tvAmount = null;
    target.tvCancleOrder = null;
  }
}
