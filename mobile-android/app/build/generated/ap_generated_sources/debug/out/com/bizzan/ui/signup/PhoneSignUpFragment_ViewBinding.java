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

public class PhoneSignUpFragment_ViewBinding implements Unbinder {
  private PhoneSignUpFragment target;

  @UiThread
  public PhoneSignUpFragment_ViewBinding(PhoneSignUpFragment target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.tvChangeType = Utils.findRequiredViewAsType(source, R.id.tvChangeType, "field 'tvChangeType'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.etCode = Utils.findRequiredViewAsType(source, R.id.etCode, "field 'etCode'", EditText.class);
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.etPassword, "field 'etPassword'", EditText.class);
    target.etRePassword = Utils.findRequiredViewAsType(source, R.id.etRePassword, "field 'etRePassword'", EditText.class);
    target.tvBack = Utils.findRequiredViewAsType(source, R.id.tvBack, "field 'tvBack'", TextView.class);
    target.tvGetCode = Utils.findRequiredViewAsType(source, R.id.tvGetCode, "field 'tvGetCode'", TextView.class);
    target.tvSignUp = Utils.findRequiredViewAsType(source, R.id.tvSignUp, "field 'tvSignUp'", TextView.class);
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.etPhone, "field 'etPhone'", EditText.class);
    target.tvToRegist = Utils.findRequiredViewAsType(source, R.id.tvToRegist, "field 'tvToRegist'", TextView.class);
    target.checkbox = Utils.findRequiredViewAsType(source, R.id.checkbox, "field 'checkbox'", CheckBox.class);
    target.text_yonghu = Utils.findRequiredViewAsType(source, R.id.text_yonghu, "field 'text_yonghu'", TextView.class);
    target.yan = Utils.findRequiredViewAsType(source, R.id.yan, "field 'yan'", ImageView.class);
    target.tvAreaCode = Utils.findRequiredViewAsType(source, R.id.tvAreaCode, "field 'tvAreaCode'", TextView.class);
    target.yan1 = Utils.findRequiredViewAsType(source, R.id.yan1, "field 'yan1'", ImageView.class);
    target.tuijian = Utils.findRequiredViewAsType(source, R.id.tuijian, "field 'tuijian'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PhoneSignUpFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.tvChangeType = null;
    target.llTitle = null;
    target.etCode = null;
    target.etPassword = null;
    target.etRePassword = null;
    target.tvBack = null;
    target.tvGetCode = null;
    target.tvSignUp = null;
    target.etPhone = null;
    target.tvToRegist = null;
    target.checkbox = null;
    target.text_yonghu = null;
    target.yan = null;
    target.tvAreaCode = null;
    target.yan1 = null;
    target.tuijian = null;
  }
}
