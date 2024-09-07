// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.home;

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

public class C2CFragment_ViewBinding implements Unbinder {
  private C2CFragment target;

  @UiThread
  public C2CFragment_ViewBinding(C2CFragment target, View source) {
    this.target = target;

    target.rvAds = Utils.findRequiredViewAsType(source, R.id.rvAds, "field 'rvAds'", RecyclerView.class);
    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refreshLayout, "field 'refreshLayout'", SwipeRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    C2CFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvAds = null;
    target.refreshLayout = null;
  }
}
