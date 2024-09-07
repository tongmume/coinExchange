// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.extract;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddressActivity_ViewBinding implements Unbinder {
  private AddressActivity target;

  @UiThread
  public AddressActivity_ViewBinding(AddressActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddressActivity_ViewBinding(AddressActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
    target.ibRegist = Utils.findRequiredViewAsType(source, R.id.ibRegist, "field 'ibRegist'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.rvAddress = Utils.findRequiredViewAsType(source, R.id.rvAddress, "field 'rvAddress'", RecyclerView.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    AddressActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.tvTitle = null;
    target.ibRegist = null;
    target.llTitle = null;
    target.rvAddress = null;
    target.view_back = null;
  }
}
