// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyAttendActivity_ViewBinding implements Unbinder {
  private MyAttendActivity target;

  @UiThread
  public MyAttendActivity_ViewBinding(MyAttendActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyAttendActivity_ViewBinding(MyAttendActivity target, View source) {
    this.target = target;

    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tab, "field 'tabLayout'", TabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.vpPager, "field 'viewPager'", ViewPager.class);
    target.llTitle = Utils.findRequiredView(source, R.id.llTitle, "field 'llTitle'");
  }

  @Override
  @CallSuper
  public void unbind() {
    MyAttendActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tabLayout = null;
    target.viewPager = null;
    target.llTitle = null;
  }
}
