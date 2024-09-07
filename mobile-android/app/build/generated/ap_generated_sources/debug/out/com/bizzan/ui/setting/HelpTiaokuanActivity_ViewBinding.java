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

public class HelpTiaokuanActivity_ViewBinding implements Unbinder {
  private HelpTiaokuanActivity target;

  @UiThread
  public HelpTiaokuanActivity_ViewBinding(HelpTiaokuanActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HelpTiaokuanActivity_ViewBinding(HelpTiaokuanActivity target, View source) {
    this.target = target;

    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.listview_tiaokuan = Utils.findRequiredViewAsType(source, R.id.listview_tiaokuan, "field 'listview_tiaokuan'", LinListView.class);
    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    HelpTiaokuanActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llTitle = null;
    target.listview_tiaokuan = null;
    target.ibBack = null;
    target.view_back = null;
  }
}
