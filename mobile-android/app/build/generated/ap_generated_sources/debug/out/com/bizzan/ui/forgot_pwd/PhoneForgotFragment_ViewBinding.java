// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.forgot_pwd;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PhoneForgotFragment_ViewBinding implements Unbinder {
  private PhoneForgotFragment target;

  @UiThread
  public PhoneForgotFragment_ViewBinding(PhoneForgotFragment target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.tvChangeType = Utils.findRequiredViewAsType(source, R.id.tvChangeType, "field 'tvChangeType'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.etUsername = Utils.findRequiredViewAsType(source, R.id.etUsername, "field 'etUsername'", EditText.class);
    target.etCode = Utils.findRequiredViewAsType(source, R.id.etCode, "field 'etCode'", EditText.class);
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.etPassword, "field 'etPassword'", EditText.class);
    target.etRePassword = Utils.findRequiredViewAsType(source, R.id.etRePassword, "field 'etRePassword'", EditText.class);
    target.tvBack = Utils.findRequiredViewAsType(source, R.id.tvBack, "field 'tvBack'", TextView.class);
    target.tvGetCode = Utils.findRequiredViewAsType(source, R.id.tvGetCode, "field 'tvGetCode'", TextView.class);
    target.tvSubmit = Utils.findRequiredViewAsType(source, R.id.tvSubmit, "field 'tvSubmit'", TextView.class);
    target.yan = Utils.findRequiredViewAsType(source, R.id.yan, "field 'yan'", ImageView.class);
    target.yan1 = Utils.findRequiredViewAsType(source, R.id.yan1, "field 'yan1'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PhoneForgotFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.tvChangeType = null;
    target.llTitle = null;
    target.etUsername = null;
    target.etCode = null;
    target.etPassword = null;
    target.etRePassword = null;
    target.tvBack = null;
    target.tvGetCode = null;
    target.tvSubmit = null;
    target.yan = null;
    target.yan1 = null;
  }
}
