// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.set_lock;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import com.bizzan.customview.lock.LockPatternIndicator;
import com.bizzan.customview.lock.LockPatternView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SetLockActivity_ViewBinding implements Unbinder {
  private SetLockActivity target;

  @UiThread
  public SetLockActivity_ViewBinding(SetLockActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SetLockActivity_ViewBinding(SetLockActivity target, View source) {
    this.target = target;

    target.tvCancle = Utils.findRequiredViewAsType(source, R.id.tvCancle, "field 'tvCancle'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.indicator = Utils.findRequiredViewAsType(source, R.id.indicator, "field 'indicator'", LockPatternIndicator.class);
    target.lockView = Utils.findRequiredViewAsType(source, R.id.lockView, "field 'lockView'", LockPatternView.class);
    target.tvMessage = Utils.findRequiredViewAsType(source, R.id.tvMessage, "field 'tvMessage'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SetLockActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvCancle = null;
    target.llTitle = null;
    target.indicator = null;
    target.lockView = null;
    target.tvMessage = null;
  }
}
