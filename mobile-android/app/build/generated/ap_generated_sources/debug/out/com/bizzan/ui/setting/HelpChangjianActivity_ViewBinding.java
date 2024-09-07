// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.setting;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import com.bizzan.base.LinListView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HelpChangjianActivity_ViewBinding implements Unbinder {
  private HelpChangjianActivity target;

  @UiThread
  public HelpChangjianActivity_ViewBinding(HelpChangjianActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HelpChangjianActivity_ViewBinding(HelpChangjianActivity target, View source) {
    this.target = target;

    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.listview_xinshou = Utils.findRequiredViewAsType(source, R.id.listview_xinshou, "field 'listview_xinshou'", LinListView.class);
    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    HelpChangjianActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llTitle = null;
    target.listview_xinshou = null;
    target.ibBack = null;
    target.view_back = null;
  }
}
