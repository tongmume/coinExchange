// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.bind_phone;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PhoneActivity_ViewBinding implements Unbinder {
  private PhoneActivity target;

  @UiThread
  public PhoneActivity_ViewBinding(PhoneActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PhoneActivity_ViewBinding(PhoneActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tvPhone, "field 'tvPhone'", TextView.class);
    target.tvEdit = Utils.findRequiredViewAsType(source, R.id.tvEdit, "field 'tvEdit'", TextView.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    PhoneActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.tvTitle = null;
    target.llTitle = null;
    target.tvPhone = null;
    target.tvEdit = null;
    target.view_back = null;
  }
}
