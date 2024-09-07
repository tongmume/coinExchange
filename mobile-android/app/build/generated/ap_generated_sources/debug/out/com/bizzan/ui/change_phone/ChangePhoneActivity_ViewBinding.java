// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.change_phone;

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

public class ChangePhoneActivity_ViewBinding implements Unbinder {
  private ChangePhoneActivity target;

  @UiThread
  public ChangePhoneActivity_ViewBinding(ChangePhoneActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangePhoneActivity_ViewBinding(ChangePhoneActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.etCode = Utils.findRequiredViewAsType(source, R.id.etCode, "field 'etCode'", EditText.class);
    target.tvSend = Utils.findRequiredViewAsType(source, R.id.tvSend, "field 'tvSend'", TextView.class);
    target.tvAreaCode = Utils.findRequiredViewAsType(source, R.id.tvAreaCode, "field 'tvAreaCode'", TextView.class);
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.etPhone, "field 'etPhone'", EditText.class);
    target.etPwd = Utils.findRequiredViewAsType(source, R.id.etPwd, "field 'etPwd'", EditText.class);
    target.tvSubmit = Utils.findRequiredViewAsType(source, R.id.tvSubmit, "field 'tvSubmit'", TextView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tvPhone, "field 'tvPhone'", TextView.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    ChangePhoneActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.tvTitle = null;
    target.llTitle = null;
    target.etCode = null;
    target.tvSend = null;
    target.tvAreaCode = null;
    target.etPhone = null;
    target.etPwd = null;
    target.tvSubmit = null;
    target.tvPhone = null;
    target.view_back = null;
  }
}
