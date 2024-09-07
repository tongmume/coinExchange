// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.wallet;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WalletListActivity_ViewBinding implements Unbinder {
  private WalletListActivity target;

  private View view2131296571;

  @UiThread
  public WalletListActivity_ViewBinding(WalletListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WalletListActivity_ViewBinding(final WalletListActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ibBack, "field 'ibBack', method 'onViewClicked', and method 'onViewClicked'");
    target.ibBack = Utils.castView(view, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    view2131296571 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
        target.onViewClicked(p0);
      }
    });
    target.viewBack = Utils.findRequiredView(source, R.id.view_back, "field 'viewBack'");
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.rvCurrency = Utils.findRequiredViewAsType(source, R.id.rv_currency, "field 'rvCurrency'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WalletListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.viewBack = null;
    target.llTitle = null;
    target.rvCurrency = null;

    view2131296571.setOnClickListener(null);
    view2131296571 = null;
  }
}
