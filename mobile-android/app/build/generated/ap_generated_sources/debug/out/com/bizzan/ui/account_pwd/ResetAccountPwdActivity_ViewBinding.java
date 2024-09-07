// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.account_pwd;

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

public class ResetAccountPwdActivity_ViewBinding implements Unbinder {
  private ResetAccountPwdActivity target;

  @UiThread
  public ResetAccountPwdActivity_ViewBinding(ResetAccountPwdActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ResetAccountPwdActivity_ViewBinding(ResetAccountPwdActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.etLoginPwd = Utils.findRequiredViewAsType(source, R.id.etLoginPwd, "field 'etLoginPwd'", EditText.class);
    target.etCode = Utils.findRequiredViewAsType(source, R.id.etCode, "field 'etCode'", EditText.class);
    target.tvSend = Utils.findRequiredViewAsType(source, R.id.tvSend, "field 'tvSend'", TextView.class);
    target.etPwd = Utils.findRequiredViewAsType(source, R.id.etPwd, "field 'etPwd'", EditText.class);
    target.etRepeatePwd = Utils.findRequiredViewAsType(source, R.id.etRepeatePwd, "field 'etRepeatePwd'", EditText.class);
    target.tvEdit = Utils.findRequiredViewAsType(source, R.id.tvEdit, "field 'tvEdit'", TextView.class);
    target.yan = Utils.findRequiredViewAsType(source, R.id.yan, "field 'yan'", ImageView.class);
    target.yan1 = Utils.findRequiredViewAsType(source, R.id.yan1, "field 'yan1'", ImageView.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    ResetAccountPwdActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.llTitle = null;
    target.etLoginPwd = null;
    target.etCode = null;
    target.tvSend = null;
    target.etPwd = null;
    target.etRepeatePwd = null;
    target.tvEdit = null;
    target.yan = null;
    target.yan1 = null;
    target.view_back = null;
  }
}
