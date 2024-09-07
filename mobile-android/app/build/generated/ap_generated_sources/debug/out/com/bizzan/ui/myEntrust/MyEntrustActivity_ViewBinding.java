// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.myEntrust;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyEntrustActivity_ViewBinding implements Unbinder {
  private MyEntrustActivity target;

  @UiThread
  public MyEntrustActivity_ViewBinding(MyEntrustActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyEntrustActivity_ViewBinding(MyEntrustActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refreshLayout, "field 'refreshLayout'", SwipeRefreshLayout.class);
    target.spinner = Utils.findRequiredViewAsType(source, R.id.spinner, "field 'spinner'", Spinner.class);
    target.rlEntrust = Utils.findRequiredViewAsType(source, R.id.rlEntrust, "field 'rlEntrust'", RecyclerView.class);
    target.rlEmpty = Utils.findRequiredViewAsType(source, R.id.rlEmpty, "field 'rlEmpty'", RelativeLayout.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    MyEntrustActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.llTitle = null;
    target.refreshLayout = null;
    target.spinner = null;
    target.rlEntrust = null;
    target.rlEmpty = null;
    target.view_back = null;
  }
}
