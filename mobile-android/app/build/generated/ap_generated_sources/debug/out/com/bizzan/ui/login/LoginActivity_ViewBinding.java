// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.login;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", TextView.class);
    target.ibRegist = Utils.findRequiredViewAsType(source, R.id.ibRegist, "field 'ibRegist'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.etUsername = Utils.findRequiredViewAsType(source, R.id.etUsername, "field 'etUsername'", EditText.class);
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.etPassword, "field 'etPassword'", EditText.class);
    target.tvLogin = Utils.findRequiredViewAsType(source, R.id.tvLogin, "field 'tvLogin'", TextView.class);
    target.tvForgetPas = Utils.findRequiredViewAsType(source, R.id.tvForgetPas, "field 'tvForgetPas'", TextView.class);
    target.tvToRegist = Utils.findRequiredViewAsType(source, R.id.tvToRegist, "field 'tvToRegist'", TextView.class);
    target.yan = Utils.findRequiredViewAsType(source, R.id.yan, "field 'yan'", ImageView.class);
    target.llLanguage = Utils.findRequiredView(source, R.id.llLanguage, "field 'llLanguage'");
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.ibRegist = null;
    target.llTitle = null;
    target.etUsername = null;
    target.etPassword = null;
    target.tvLogin = null;
    target.tvForgetPas = null;
    target.tvToRegist = null;
    target.yan = null;
    target.llLanguage = null;
  }
}
