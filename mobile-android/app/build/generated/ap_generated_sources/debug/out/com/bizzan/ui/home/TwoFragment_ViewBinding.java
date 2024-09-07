// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.home;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import com.bizzan.customview.intercept.WonderfulViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TwoFragment_ViewBinding implements Unbinder {
  private TwoFragment target;

  @UiThread
  public TwoFragment_ViewBinding(TwoFragment target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.ibSearch = Utils.findRequiredViewAsType(source, R.id.ibSearch, "field 'ibSearch'", ImageButton.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.tab = Utils.findRequiredViewAsType(source, R.id.tab, "field 'tab'", TabLayout.class);
    target.vpPager = Utils.findRequiredViewAsType(source, R.id.vpPager, "field 'vpPager'", WonderfulViewPager.class);

    Context context = source.getContext();
    Resources res = context.getResources();
    target.tabs = res.getStringArray(R.array.home_two_top_tab);
  }

  @Override
  @CallSuper
  public void unbind() {
    TwoFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.ibSearch = null;
    target.llTitle = null;
    target.tab = null;
    target.vpPager = null;
  }
}
