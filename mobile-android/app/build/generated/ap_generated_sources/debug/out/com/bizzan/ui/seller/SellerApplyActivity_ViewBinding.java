// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.seller;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SellerApplyActivity_ViewBinding implements Unbinder {
  private SellerApplyActivity target;

  @UiThread
  public SellerApplyActivity_ViewBinding(SellerApplyActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SellerApplyActivity_ViewBinding(SellerApplyActivity target, View source) {
    this.target = target;

    target.text_shangjia = Utils.findRequiredViewAsType(source, R.id.text_shangjia, "field 'text_shangjia'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.tv_apply_seller_state_title = Utils.findRequiredViewAsType(source, R.id.tv_apply_seller_state_title, "field 'tv_apply_seller_state_title'", TextView.class);
    target.ll_apply_seller_process = Utils.findRequiredViewAsType(source, R.id.ll_apply_seller_process, "field 'll_apply_seller_process'", LinearLayout.class);
    target.iv_seller_apply_process = Utils.findRequiredViewAsType(source, R.id.iv_seller_apply_process, "field 'iv_seller_apply_process'", ImageView.class);
    target.tv_release_advertisement = Utils.findRequiredViewAsType(source, R.id.tv_release_advertisement, "field 'tv_release_advertisement'", TextView.class);
    target.vp_seller_apply = Utils.findRequiredViewAsType(source, R.id.vp_seller_apply, "field 'vp_seller_apply'", ViewPager.class);
    target.llCircleIndicator = Utils.findRequiredViewAsType(source, R.id.ll_circle_indicator, "field 'llCircleIndicator'", LinearLayout.class);
    target.ll_apply_seller_bottom = Utils.findRequiredViewAsType(source, R.id.ll_apply_seller_bottom, "field 'll_apply_seller_bottom'", LinearLayout.class);
    target.iv_check_box = Utils.findRequiredViewAsType(source, R.id.iv_check_box, "field 'iv_check_box'", ImageView.class);
    target.tv_commit = Utils.findRequiredViewAsType(source, R.id.tv_commit, "field 'tv_commit'", TextView.class);
    target.iv_back = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'iv_back'", ImageView.class);
    target.ll_failed = Utils.findRequiredViewAsType(source, R.id.ll_failed, "field 'll_failed'", LinearLayout.class);
    target.tv_retry = Utils.findRequiredViewAsType(source, R.id.tv_retry, "field 'tv_retry'", TextView.class);
    target.tv_failed_reason = Utils.findRequiredViewAsType(source, R.id.tv_failed_reason, "field 'tv_failed_reason'", TextView.class);
    target.view_back = Utils.findRequiredViewAsType(source, R.id.view_back, "field 'view_back'", TextView.class);
    target.indicators = Utils.arrayOf(
        Utils.findRequiredViewAsType(source, R.id.tv_indicator0, "field 'indicators'", TextView.class), 
        Utils.findRequiredViewAsType(source, R.id.tv_indicator1, "field 'indicators'", TextView.class), 
        Utils.findRequiredViewAsType(source, R.id.tv_indicator2, "field 'indicators'", TextView.class));
  }

  @Override
  @CallSuper
  public void unbind() {
    SellerApplyActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.text_shangjia = null;
    target.llTitle = null;
    target.tv_apply_seller_state_title = null;
    target.ll_apply_seller_process = null;
    target.iv_seller_apply_process = null;
    target.tv_release_advertisement = null;
    target.vp_seller_apply = null;
    target.llCircleIndicator = null;
    target.ll_apply_seller_bottom = null;
    target.iv_check_box = null;
    target.tv_commit = null;
    target.iv_back = null;
    target.ll_failed = null;
    target.tv_retry = null;
    target.tv_failed_reason = null;
    target.view_back = null;
    target.indicators = null;
  }
}
