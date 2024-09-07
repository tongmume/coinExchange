// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.my_order;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderFragment_ViewBinding implements Unbinder {
  private OrderFragment target;

  @UiThread
  public OrderFragment_ViewBinding(OrderFragment target, View source) {
    this.target = target;

    target.rvIngOrder = Utils.findRequiredViewAsType(source, R.id.rvIngOrder, "field 'rvIngOrder'", RecyclerView.class);
    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refreshLayout, "field 'refreshLayout'", SwipeRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvIngOrder = null;
    target.refreshLayout = null;
  }
}
