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
import org.angmarch.views.NiceSpinner;

public class TrustListActivity_ViewBinding implements Unbinder {
  private TrustListActivity target;

  @UiThread
  public TrustListActivity_ViewBinding(TrustListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TrustListActivity_ViewBinding(TrustListActivity target, View source) {
    this.target = target;

    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.iv_filter = Utils.findRequiredViewAsType(source, R.id.iv_filter, "field 'iv_filter'", ImageView.class);
    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageView.class);
    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refreshLayout, "field 'refreshLayout'", SwipeRefreshLayout.class);
    target.rvAds = Utils.findRequiredViewAsType(source, R.id.rvAds, "field 'rvAds'", RecyclerView.class);
    target.tv_no_data = Utils.findRequiredViewAsType(source, R.id.tv_no_data, "field 'tv_no_data'", TextView.class);
    target.tv_title_current_trust = Utils.findRequiredViewAsType(source, R.id.tv_title_current_trust, "field 'tv_title_current_trust'", TextView.class);
    target.tv_title_history_trust = Utils.findRequiredViewAsType(source, R.id.tv_title_history_trust, "field 'tv_title_history_trust'", TextView.class);
    target.current_trust_underline = Utils.findRequiredView(source, R.id.current_trust_underline, "field 'current_trust_underline'");
    target.history_trust_underline = Utils.findRequiredView(source, R.id.history_trust_underline, "field 'history_trust_underline'");
    target.dropdown_layout = Utils.findRequiredViewAsType(source, R.id.dropdown_layout, "field 'dropdown_layout'", DropdownLayout.class);
    target.sp_symbol = Utils.findRequiredViewAsType(source, R.id.sp_symbol, "field 'sp_symbol'", NiceSpinner.class);
    target.sp_type = Utils.findRequiredViewAsType(source, R.id.sp_type, "field 'sp_type'", NiceSpinner.class);
    target.sp_direction = Utils.findRequiredViewAsType(source, R.id.sp_direction, "field 'sp_direction'", NiceSpinner.class);
    target.tv_start_time = Utils.findRequiredViewAsType(source, R.id.tv_start_time, "field 'tv_start_time'", TextView.class);
    target.tv_end_time = Utils.findRequiredViewAsType(source, R.id.tv_end_time, "field 'tv_end_time'", TextView.class);
    target.tv_reset = Utils.findRequiredViewAsType(source, R.id.tv_reset, "field 'tv_reset'", TextView.class);
    target.tv_confirm = Utils.findRequiredViewAsType(source, R.id.tv_confirm, "field 'tv_confirm'", TextView.class);
    target.line_2 = Utils.findRequiredViewAsType(source, R.id.line_2, "field 'line_2'", LinearLayout.class);
    target.view_zhe = Utils.findRequiredView(source, R.id.view_zhe, "field 'view_zhe'");
  }

  @Override
  @CallSuper
  public void unbind() {
    TrustListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llTitle = null;
    target.iv_filter = null;
    target.ibBack = null;
    target.refreshLayout = null;
    target.rvAds = null;
    target.tv_no_data = null;
    target.tv_title_current_trust = null;
    target.tv_title_history_trust = null;
    target.current_trust_underline = null;
    target.history_trust_underline = null;
    target.dropdown_layout = null;
    target.sp_symbol = null;
    target.sp_type = null;
    target.sp_direction = null;
    target.tv_start_time = null;
    target.tv_end_time = null;
    target.tv_reset = null;
    target.tv_confirm = null;
    target.line_2 = null;
    target.view_zhe = null;
  }
}
