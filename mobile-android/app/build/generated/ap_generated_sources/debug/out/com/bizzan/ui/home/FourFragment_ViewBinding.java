// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
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

public class FourFragment_ViewBinding implements Unbinder {
  private FourFragment target;

  @UiThread
  public FourFragment_ViewBinding(FourFragment target, View source) {
    this.target = target;

    target.ivBack = Utils.findRequiredView(source, R.id.ibBack, "field 'ivBack'");
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tab, "field 'tabLayout'", TabLayout.class);
    target.vp = Utils.findRequiredViewAsType(source, R.id.vp, "field 'vp'", ViewPager.class);
    target.tvBuy = Utils.findRequiredViewAsType(source, R.id.tvBuy, "field 'tvBuy'", TextView.class);
    target.tvSell = Utils.findRequiredViewAsType(source, R.id.tvSell, "field 'tvSell'", TextView.class);
    target.ivGoOrder = Utils.findRequiredViewAsType(source, R.id.ivGoOrder, "field 'ivGoOrder'", ImageView.class);
    target.ivReleseAd = Utils.findRequiredViewAsType(source, R.id.ivReleseAd, "field 'ivReleseAd'", ImageView.class);
    target.iv_seller_apply = Utils.findRequiredViewAsType(source, R.id.iv_seller_apply, "field 'iv_seller_apply'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FourFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.llTitle = null;
    target.tabLayout = null;
    target.vp = null;
    target.tvBuy = null;
    target.tvSell = null;
    target.ivGoOrder = null;
    target.ivReleseAd = null;
    target.iv_seller_apply = null;
  }
}
