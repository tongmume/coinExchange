// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.bind_phone;

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

public class BindPhoneActivity_ViewBinding implements Unbinder {
  private BindPhoneActivity target;

  @UiThread
  public BindPhoneActivity_ViewBinding(BindPhoneActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BindPhoneActivity_ViewBinding(BindPhoneActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.tvCountry = Utils.findRequiredViewAsType(source, R.id.tvCountry, "field 'tvCountry'", TextView.class);
    target.tvAreaCode = Utils.findRequiredViewAsType(source, R.id.tvAreaCode, "field 'tvAreaCode'", TextView.class);
    target.etPhone = Utils.findRequiredViewAsType(source, R.id.etPhone, "field 'etPhone'", EditText.class);
    target.etCode = Utils.findRequiredViewAsType(source, R.id.etCode, "field 'etCode'", EditText.class);
    target.tvSend = Utils.findRequiredViewAsType(source, R.id.tvSend, "field 'tvSend'", TextView.class);
    target.etPwd = Utils.findRequiredViewAsType(source, R.id.etPwd, "field 'etPwd'", EditText.class);
    target.tvBind = Utils.findRequiredViewAsType(source, R.id.tvBind, "field 'tvBind'", TextView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BindPhoneActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.llTitle = null;
    target.tvCountry = null;
    target.tvAreaCode = null;
    target.etPhone = null;
    target.etCode = null;
    target.tvSend = null;
    target.etPwd = null;
    target.tvBind = null;
    target.tvTitle = null;
  }
}
