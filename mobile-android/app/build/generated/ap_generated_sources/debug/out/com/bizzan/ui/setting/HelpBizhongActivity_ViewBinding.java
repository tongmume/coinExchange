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

public class HelpBizhongActivity_ViewBinding implements Unbinder {
  private HelpBizhongActivity target;

  @UiThread
  public HelpBizhongActivity_ViewBinding(HelpBizhongActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HelpBizhongActivity_ViewBinding(HelpBizhongActivity target, View source) {
    this.target = target;

    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.listview_bizhong = Utils.findRequiredViewAsType(source, R.id.listview_bizhong, "field 'listview_bizhong'", LinListView.class);
    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    HelpBizhongActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llTitle = null;
    target.listview_bizhong = null;
    target.ibBack = null;
    target.view_back = null;
  }
}
