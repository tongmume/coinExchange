// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.my_order;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyOrderActivity_ViewBinding implements Unbinder {
  private MyOrderActivity target;

  @UiThread
  public MyOrderActivity_ViewBinding(MyOrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyOrderActivity_ViewBinding(MyOrderActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.ibDetail = Utils.findRequiredViewAsType(source, R.id.ibDetail, "field 'ibDetail'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.tab = Utils.findRequiredViewAsType(source, R.id.tab, "field 'tab'", TabLayout.class);
    target.vpPager = Utils.findRequiredViewAsType(source, R.id.vpPager, "field 'vpPager'", ViewPager.class);
    target.llContainer = Utils.findRequiredViewAsType(source, R.id.llContainer, "field 'llContainer'", LinearLayout.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");

    Context context = source.getContext();
    Resources res = context.getResources();
    target.status = res.getStringArray(R.array.order_status);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyOrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.ibDetail = null;
    target.llTitle = null;
    target.tab = null;
    target.vpPager = null;
    target.llContainer = null;
    target.view_back = null;
  }
}
