// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.signup;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
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

public class EmailSignUpFragment_ViewBinding implements Unbinder {
  private EmailSignUpFragment target;

  @UiThread
  public EmailSignUpFragment_ViewBinding(EmailSignUpFragment target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.tvChangeType = Utils.findRequiredViewAsType(source, R.id.tvChangeType, "field 'tvChangeType'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.etEmail = Utils.findRequiredViewAsType(source, R.id.etEmail, "field 'etEmail'", EditText.class);
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.etPassword, "field 'etPassword'", EditText.class);
    target.etRePassword = Utils.findRequiredViewAsType(source, R.id.etRePassword, "field 'etRePassword'", EditText.class);
    target.tvSignUp = Utils.findRequiredViewAsType(source, R.id.tvSignUp, "field 'tvSignUp'", TextView.class);
    target.tvBack = Utils.findRequiredViewAsType(source, R.id.tvBack, "field 'tvBack'", TextView.class);
    target.tuijian = Utils.findRequiredViewAsType(source, R.id.tuijian, "field 'tuijian'", EditText.class);
    target.yan = Utils.findRequiredViewAsType(source, R.id.yan, "field 'yan'", ImageView.class);
    target.yan1 = Utils.findRequiredViewAsType(source, R.id.yan1, "field 'yan1'", ImageView.class);
    target.checkbox = Utils.findRequiredViewAsType(source, R.id.checkbox, "field 'checkbox'", CheckBox.class);
    target.textYonghu = Utils.findRequiredViewAsType(source, R.id.text_yonghu, "field 'textYonghu'", TextView.class);
    target.tvToRegist = Utils.findRequiredViewAsType(source, R.id.tvToRegist, "field 'tvToRegist'", TextView.class);
    target.etCode = Utils.findRequiredViewAsType(source, R.id.etCode, "field 'etCode'", EditText.class);
    target.tvGetCode = Utils.findRequiredViewAsType(source, R.id.tvGetCode, "field 'tvGetCode'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EmailSignUpFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.tvChangeType = null;
    target.llTitle = null;
    target.etEmail = null;
    target.etPassword = null;
    target.etRePassword = null;
    target.tvSignUp = null;
    target.tvBack = null;
    target.tuijian = null;
    target.yan = null;
    target.yan1 = null;
    target.checkbox = null;
    target.textYonghu = null;
    target.tvToRegist = null;
    target.etCode = null;
    target.tvGetCode = null;
  }
}
