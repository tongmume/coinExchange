// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.wallet;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import com.bizzan.base.LinListView;
import com.bizzan.ui.entrust.DropdownLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChongBiJLActivity_ViewBinding implements Unbinder {
  private ChongBiJLActivity target;

  @UiThread
  public ChongBiJLActivity_ViewBinding(ChongBiJLActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChongBiJLActivity_ViewBinding(ChongBiJLActivity target, View source) {
    this.target = target;

    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.ibCalendar = Utils.findRequiredViewAsType(source, R.id.ibCalendar, "field 'ibCalendar'", ImageButton.class);
    target.dropdownLayout = Utils.findRequiredViewAsType(source, R.id.dropdownLayout, "field 'dropdownLayout'", DropdownLayout.class);
    target.listview = Utils.findRequiredViewAsType(source, R.id.listview, "field 'listview'", ListView.class);
    target.listview_1 = Utils.findRequiredViewAsType(source, R.id.listview_1, "field 'listview_1'", LinListView.class);
    target.tvMessage = Utils.findRequiredViewAsType(source, R.id.tvMessage, "field 'tvMessage'", LinearLayout.class);
    target.view_xianshi = Utils.findRequiredView(source, R.id.view_xianshi, "field 'view_xianshi'");
  }

  @Override
  @CallSuper
  public void unbind() {
    ChongBiJLActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llTitle = null;
    target.ibBack = null;
    target.ibCalendar = null;
    target.dropdownLayout = null;
    target.listview = null;
    target.listview_1 = null;
    target.tvMessage = null;
    target.view_xianshi = null;
  }
}
