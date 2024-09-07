// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.wallet;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectCoinActivity_ViewBinding implements Unbinder {
  private SelectCoinActivity target;

  private View view2131296571;

  @UiThread
  public SelectCoinActivity_ViewBinding(SelectCoinActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SelectCoinActivity_ViewBinding(final SelectCoinActivity target, View source) {
    this.target = target;

    View view;
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.rvCoins = Utils.findRequiredViewAsType(source, R.id.rv_coins, "field 'rvCoins'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.ibBack, "method 'onViewClicked'");
    view2131296571 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectCoinActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llTitle = null;
    target.rvCoins = null;

    view2131296571.setOnClickListener(null);
    view2131296571 = null;
  }
}
