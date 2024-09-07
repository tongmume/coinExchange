// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ETHMarket2Fragment_ViewBinding implements Unbinder {
  private ETHMarket2Fragment target;

  @UiThread
  public ETHMarket2Fragment_ViewBinding(ETHMarket2Fragment target, View source) {
    this.target = target;

    target.rvContent = Utils.findRequiredViewAsType(source, R.id.rvContent, "field 'rvContent'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ETHMarket2Fragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvContent = null;
  }
}
