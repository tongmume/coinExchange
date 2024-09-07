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

public class VolumeFragment_ViewBinding implements Unbinder {
  private VolumeFragment target;

  @UiThread
  public VolumeFragment_ViewBinding(VolumeFragment target, View source) {
    this.target = target;

    target.tvNumberV = Utils.findRequiredViewAsType(source, R.id.tvNumberV, "field 'tvNumberV'", TextView.class);
    target.tvPriceV = Utils.findRequiredViewAsType(source, R.id.tvPriceV, "field 'tvPriceV'", TextView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.rvVolume, "field 'recyclerView'", RecyclerView.class);
    target.volumeBar = Utils.findRequiredViewAsType(source, R.id.volumeBar, "field 'volumeBar'", ProgressBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VolumeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvNumberV = null;
    target.tvPriceV = null;
    target.recyclerView = null;
    target.volumeBar = null;
  }
}
