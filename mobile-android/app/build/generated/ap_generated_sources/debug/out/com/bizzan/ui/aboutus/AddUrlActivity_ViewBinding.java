// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.aboutus;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddUrlActivity_ViewBinding implements Unbinder {
  private AddUrlActivity target;

  private View view2131296571;

  @UiThread
  public AddUrlActivity_ViewBinding(AddUrlActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddUrlActivity_ViewBinding(final AddUrlActivity target, View source) {
    this.target = target;

    View view;
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.wb = Utils.findRequiredViewAsType(source, R.id.wb, "field 'wb'", WebView.class);
    view = Utils.findRequiredView(source, R.id.ibBack, "method 'onViewClicked'");
    view2131296571 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    AddUrlActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.title = null;
    target.llTitle = null;
    target.wb = null;

    view2131296571.setOnClickListener(null);
    view2131296571 = null;
  }
}
