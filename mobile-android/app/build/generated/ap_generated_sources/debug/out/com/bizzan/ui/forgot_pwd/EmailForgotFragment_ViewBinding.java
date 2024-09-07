// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.forgot_pwd;

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

public class EmailForgotFragment_ViewBinding implements Unbinder {
  private EmailForgotFragment target;

  @UiThread
  public EmailForgotFragment_ViewBinding(EmailForgotFragment target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.tvChangeType = Utils.findRequiredViewAsType(source, R.id.tvChangeType, "field 'tvChangeType'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.etEmail = Utils.findRequiredViewAsType(source, R.id.etEmail, "field 'etEmail'", EditText.class);
    target.etCode = Utils.findRequiredViewAsType(source, R.id.etCode, "field 'etCode'", EditText.class);
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.etPassword, "field 'etPassword'", EditText.class);
    target.etRePassword = Utils.findRequiredViewAsType(source, R.id.etRePassword, "field 'etRePassword'", EditText.class);
    target.tvSubmit = Utils.findRequiredViewAsType(source, R.id.tvSubmit, "field 'tvSubmit'", TextView.class);
    target.tvBack = Utils.findRequiredViewAsType(source, R.id.tvBack, "field 'tvBack'", TextView.class);
    target.tvGetCode = Utils.findRequiredViewAsType(source, R.id.tvGetCode, "field 'tvGetCode'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EmailForgotFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.tvChangeType = null;
    target.llTitle = null;
    target.etEmail = null;
    target.etCode = null;
    target.etPassword = null;
    target.etRePassword = null;
    target.tvSubmit = null;
    target.tvBack = null;
    target.tvGetCode = null;
  }
}
