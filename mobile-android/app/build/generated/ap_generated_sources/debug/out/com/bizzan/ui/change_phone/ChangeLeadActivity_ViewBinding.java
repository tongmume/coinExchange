// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.change_phone;

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

public class ChangeLeadActivity_ViewBinding implements Unbinder {
  private ChangeLeadActivity target;

  @UiThread
  public ChangeLeadActivity_ViewBinding(ChangeLeadActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangeLeadActivity_ViewBinding(ChangeLeadActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
    target.ibRegist = Utils.findRequiredViewAsType(source, R.id.ibRegist, "field 'ibRegist'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.llCanReceive = Utils.findRequiredViewAsType(source, R.id.llCanReceive, "field 'llCanReceive'", LinearLayout.class);
    target.llCannotReceive = Utils.findRequiredViewAsType(source, R.id.llCannotReceive, "field 'llCannotReceive'", LinearLayout.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    ChangeLeadActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.tvTitle = null;
    target.ibRegist = null;
    target.llTitle = null;
    target.llCanReceive = null;
    target.llCannotReceive = null;
    target.view_back = null;
  }
}
