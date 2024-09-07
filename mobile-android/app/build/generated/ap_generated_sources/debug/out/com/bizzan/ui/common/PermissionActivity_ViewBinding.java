// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.common;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PermissionActivity_ViewBinding implements Unbinder {
  private PermissionActivity target;

  @UiThread
  public PermissionActivity_ViewBinding(PermissionActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PermissionActivity_ViewBinding(PermissionActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.ibMessage = Utils.findRequiredViewAsType(source, R.id.ibMessage, "field 'ibMessage'", ImageButton.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.wb = Utils.findRequiredViewAsType(source, R.id.wb, "field 'wb'", WebView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PermissionActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.ibMessage = null;
    target.llTitle = null;
    target.wb = null;
  }
}
