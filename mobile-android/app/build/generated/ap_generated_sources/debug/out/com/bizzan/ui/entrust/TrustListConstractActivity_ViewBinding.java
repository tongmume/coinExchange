// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.entrust;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TrustListConstractActivity_ViewBinding implements Unbinder {
  private TrustListConstractActivity target;

  @UiThread
  public TrustListConstractActivity_ViewBinding(TrustListConstractActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TrustListConstractActivity_ViewBinding(TrustListConstractActivity target, View source) {
    this.target = target;

    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.iv_filter = Utils.findRequiredViewAsType(source, R.id.iv_filter, "field 'iv_filter'", ImageView.class);
    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageView.class);
    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refreshLayout, "field 'refreshLayout'", SwipeRefreshLayout.class);
    target.rvAds = Utils.findRequiredViewAsType(source, R.id.rvAds, "field 'rvAds'", RecyclerView.class);
    target.tv_no_data = Utils.findRequiredViewAsType(source, R.id.tv_no_data, "field 'tv_no_data'", TextView.class);
    target.tv_title_history_trust = Utils.findRequiredViewAsType(source, R.id.tv_title_history_trust, "field 'tv_title_history_trust'", TextView.class);
    target.tv_title_now_trust = Utils.findRequiredViewAsType(source, R.id.tv_title_now_trust, "field 'tv_title_now_trust'", TextView.class);
    target.history_trust_underline = Utils.findRequiredView(source, R.id.history_trust_underline, "field 'history_trust_underline'");
  }

  @Override
  @CallSuper
  public void unbind() {
    TrustListConstractActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llTitle = null;
    target.iv_filter = null;
    target.ibBack = null;
    target.refreshLayout = null;
    target.rvAds = null;
    target.tv_no_data = null;
    target.tv_title_history_trust = null;
    target.tv_title_now_trust = null;
    target.history_trust_underline = null;
  }
}
