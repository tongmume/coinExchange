// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.wallet_detail;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import com.bizzan.ui.entrust.DropdownLayout;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.angmarch.views.NiceSpinner;

public class WalletDetailActivity_ViewBinding implements Unbinder {
  private WalletDetailActivity target;

  @UiThread
  public WalletDetailActivity_ViewBinding(WalletDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WalletDetailActivity_ViewBinding(WalletDetailActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", RelativeLayout.class);
    target.rvDetail = Utils.findRequiredViewAsType(source, R.id.rvDetail, "field 'rvDetail'", RecyclerView.class);
    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refreshLayout, "field 'refreshLayout'", SwipeRefreshLayout.class);
    target.ivFilter = Utils.findRequiredViewAsType(source, R.id.iv_filter, "field 'ivFilter'", ImageView.class);
    target.dropdownLayout = Utils.findRequiredViewAsType(source, R.id.dropdown_layout, "field 'dropdownLayout'", DropdownLayout.class);
    target.sp_type = Utils.findRequiredViewAsType(source, R.id.sp_type, "field 'sp_type'", NiceSpinner.class);
    target.sp_symbol = Utils.findRequiredViewAsType(source, R.id.sp_symbol, "field 'sp_symbol'", NiceSpinner.class);
    target.tv_start_time = Utils.findRequiredViewAsType(source, R.id.tv_start_time, "field 'tv_start_time'", TextView.class);
    target.tv_end_time = Utils.findRequiredViewAsType(source, R.id.tv_end_time, "field 'tv_end_time'", TextView.class);
    target.tvReset = Utils.findRequiredViewAsType(source, R.id.tv_reset, "field 'tvReset'", TextView.class);
    target.tvConfirm = Utils.findRequiredViewAsType(source, R.id.tv_confirm, "field 'tvConfirm'", TextView.class);
    target.view_zhe = Utils.findRequiredView(source, R.id.view_zhe, "field 'view_zhe'");
  }

  @Override
  @CallSuper
  public void unbind() {
    WalletDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.llTitle = null;
    target.rvDetail = null;
    target.refreshLayout = null;
    target.ivFilter = null;
    target.dropdownLayout = null;
    target.sp_type = null;
    target.sp_symbol = null;
    target.tv_start_time = null;
    target.tv_end_time = null;
    target.tvReset = null;
    target.tvConfirm = null;
    target.view_zhe = null;
  }
}
