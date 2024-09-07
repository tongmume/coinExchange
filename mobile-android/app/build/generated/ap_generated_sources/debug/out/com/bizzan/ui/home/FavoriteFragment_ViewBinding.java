// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FavoriteFragment_ViewBinding implements Unbinder {
  private FavoriteFragment target;

  @UiThread
  public FavoriteFragment_ViewBinding(FavoriteFragment target, View source) {
    this.target = target;

    target.rvContent = Utils.findRequiredViewAsType(source, R.id.rvContent, "field 'rvContent'", RecyclerView.class);
    target.textNoRecords = Utils.findRequiredViewAsType(source, R.id.textNoRecords, "field 'textNoRecords'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FavoriteFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvContent = null;
    target.textNoRecords = null;
  }
}
