// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import com.bizzan.customview.VpSwipeRefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ActivityFragment_ViewBinding implements Unbinder {
  private ActivityFragment target;

  @UiThread
  public ActivityFragment_ViewBinding(ActivityFragment target, View source) {
    this.target = target;

    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refreshLayout, "field 'refreshLayout'", VpSwipeRefreshLayout.class);
    target.listview = Utils.findRequiredViewAsType(source, R.id.listview, "field 'listview'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ActivityFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.refreshLayout = null;
    target.listview = null;
  }
}
