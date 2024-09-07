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

public class EntrustConstractDialogFragment_ViewBinding implements Unbinder {
  private EntrustConstractDialogFragment target;

  @UiThread
  public EntrustConstractDialogFragment_ViewBinding(EntrustConstractDialogFragment target,
      View source) {
    this.target = target;

    target.llContent = Utils.findRequiredViewAsType(source, R.id.llContent, "field 'llContent'", LinearLayout.class);
    target.tvCancle = Utils.findRequiredViewAsType(source, R.id.tvCancle, "field 'tvCancle'", TextView.class);
    target.tvCancleOrder = Utils.findRequiredViewAsType(source, R.id.tvCancleOrder, "field 'tvCancleOrder'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EntrustConstractDialogFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llContent = null;
    target.tvCancle = null;
    target.tvCancleOrder = null;
  }
}
