// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.bind_email;

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

public class BindEmailActivity_ViewBinding implements Unbinder {
  private BindEmailActivity target;

  @UiThread
  public BindEmailActivity_ViewBinding(BindEmailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BindEmailActivity_ViewBinding(BindEmailActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.etEmail = Utils.findRequiredViewAsType(source, R.id.etEmail, "field 'etEmail'", EditText.class);
    target.etCode = Utils.findRequiredViewAsType(source, R.id.etCode, "field 'etCode'", EditText.class);
    target.tvSend = Utils.findRequiredViewAsType(source, R.id.tvSend, "field 'tvSend'", TextView.class);
    target.etPwd = Utils.findRequiredViewAsType(source, R.id.etPwd, "field 'etPwd'", EditText.class);
    target.tvBind = Utils.findRequiredViewAsType(source, R.id.tvBind, "field 'tvBind'", TextView.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    BindEmailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.tvTitle = null;
    target.llTitle = null;
    target.etEmail = null;
    target.etCode = null;
    target.tvSend = null;
    target.etPwd = null;
    target.tvBind = null;
    target.view_back = null;
  }
}
