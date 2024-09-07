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

public class GongGaoActivity_ViewBinding implements Unbinder {
  private GongGaoActivity target;

  @UiThread
  public GongGaoActivity_ViewBinding(GongGaoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GongGaoActivity_ViewBinding(GongGaoActivity target, View source) {
    this.target = target;

    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.listview_1 = Utils.findRequiredViewAsType(source, R.id.listview_1, "field 'listview_1'", LinListView.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    GongGaoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llTitle = null;
    target.ibBack = null;
    target.listview_1 = null;
    target.view_back = null;
  }
}
