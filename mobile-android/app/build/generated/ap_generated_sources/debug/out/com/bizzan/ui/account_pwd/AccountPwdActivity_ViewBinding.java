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

public class AccountPwdActivity_ViewBinding implements Unbinder {
  private AccountPwdActivity target;

  @UiThread
  public AccountPwdActivity_ViewBinding(AccountPwdActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AccountPwdActivity_ViewBinding(AccountPwdActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.etLoginPwd = Utils.findRequiredViewAsType(source, R.id.etLoginPwd, "field 'etLoginPwd'", EditText.class);
    target.etAccountPwd = Utils.findRequiredViewAsType(source, R.id.etAccountPwd, "field 'etAccountPwd'", EditText.class);
    target.etRepeatPwd = Utils.findRequiredViewAsType(source, R.id.etRepeatPwd, "field 'etRepeatPwd'", EditText.class);
    target.tvSet = Utils.findRequiredViewAsType(source, R.id.tvSet, "field 'tvSet'", TextView.class);
    target.yan = Utils.findRequiredViewAsType(source, R.id.yan, "field 'yan'", ImageView.class);
    target.yan1 = Utils.findRequiredViewAsType(source, R.id.yan1, "field 'yan1'", ImageView.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    AccountPwdActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.llTitle = null;
    target.etLoginPwd = null;
    target.etAccountPwd = null;
    target.etRepeatPwd = null;
    target.tvSet = null;
    target.yan = null;
    target.yan1 = null;
    target.view_back = null;
  }
}
