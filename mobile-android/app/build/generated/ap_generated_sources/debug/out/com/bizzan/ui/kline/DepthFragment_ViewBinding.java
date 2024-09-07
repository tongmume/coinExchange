// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.kline;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DepthFragment_ViewBinding implements Unbinder {
  private DepthFragment target;

  @UiThread
  public DepthFragment_ViewBinding(DepthFragment target, View source) {
    this.target = target;

    target.tvNumber = Utils.findRequiredViewAsType(source, R.id.tvNumber, "field 'tvNumber'", TextView.class);
    target.tvNumberR = Utils.findRequiredViewAsType(source, R.id.tvNumberR, "field 'tvNumberR'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tvPrice, "field 'tvPrice'", TextView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.rvDepth, "field 'recyclerView'", RecyclerView.class);
    target.depthBar = Utils.findRequiredViewAsType(source, R.id.depthBar, "field 'depthBar'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DepthFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvNumber = null;
    target.tvNumberR = null;
    target.tvPrice = null;
    target.recyclerView = null;
    target.depthBar = null;
  }
}
