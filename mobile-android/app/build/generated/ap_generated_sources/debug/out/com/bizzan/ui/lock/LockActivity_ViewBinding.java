// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.lock;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import com.bizzan.customview.CircleImageView;
import com.bizzan.customview.lock.LockPatternView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LockActivity_ViewBinding implements Unbinder {
  private LockActivity target;

  @UiThread
  public LockActivity_ViewBinding(LockActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LockActivity_ViewBinding(LockActivity target, View source) {
    this.target = target;

    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.ivHeader = Utils.findRequiredViewAsType(source, R.id.ivHeader, "field 'ivHeader'", CircleImageView.class);
    target.tvNickName = Utils.findRequiredViewAsType(source, R.id.tvNickName, "field 'tvNickName'", TextView.class);
    target.tvMessage = Utils.findRequiredViewAsType(source, R.id.tvMessage, "field 'tvMessage'", TextView.class);
    target.gestureTipLayout = Utils.findRequiredViewAsType(source, R.id.gesture_tip_layout, "field 'gestureTipLayout'", LinearLayout.class);
    target.lockView = Utils.findRequiredViewAsType(source, R.id.lockView, "field 'lockView'", LockPatternView.class);
    target.tvForgot = Utils.findRequiredViewAsType(source, R.id.tvForgot, "field 'tvForgot'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LockActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llTitle = null;
    target.ivHeader = null;
    target.tvNickName = null;
    target.tvMessage = null;
    target.gestureTipLayout = null;
    target.lockView = null;
    target.tvForgot = null;
  }
}
