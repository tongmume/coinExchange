// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.appeal;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AppealActivity_ViewBinding implements Unbinder {
  private AppealActivity target;

  @UiThread
  public AppealActivity_ViewBinding(AppealActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AppealActivity_ViewBinding(AppealActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.ibRegist = Utils.findRequiredViewAsType(source, R.id.ibRegist, "field 'ibRegist'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.etRemark = Utils.findRequiredViewAsType(source, R.id.etRemark, "field 'etRemark'", EditText.class);
    target.tvSubmit = Utils.findRequiredViewAsType(source, R.id.tvSubmit, "field 'tvSubmit'", TextView.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    AppealActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.ibRegist = null;
    target.llTitle = null;
    target.etRemark = null;
    target.tvSubmit = null;
    target.view_back = null;
  }
}
