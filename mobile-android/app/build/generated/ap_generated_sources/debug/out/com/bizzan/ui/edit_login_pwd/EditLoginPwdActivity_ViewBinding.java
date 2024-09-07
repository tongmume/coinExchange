// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.edit_login_pwd;

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

public class EditLoginPwdActivity_ViewBinding implements Unbinder {
  private EditLoginPwdActivity target;

  @UiThread
  public EditLoginPwdActivity_ViewBinding(EditLoginPwdActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditLoginPwdActivity_ViewBinding(EditLoginPwdActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.etOldPwd = Utils.findRequiredViewAsType(source, R.id.etOldPwd, "field 'etOldPwd'", EditText.class);
    target.etNewPwd = Utils.findRequiredViewAsType(source, R.id.etNewPwd, "field 'etNewPwd'", EditText.class);
    target.etRepeatPwd = Utils.findRequiredViewAsType(source, R.id.etRepeatPwd, "field 'etRepeatPwd'", EditText.class);
    target.tvConfirm = Utils.findRequiredViewAsType(source, R.id.tvConfirm, "field 'tvConfirm'", TextView.class);
    target.etCode = Utils.findRequiredViewAsType(source, R.id.etCode, "field 'etCode'", EditText.class);
    target.tvSend = Utils.findRequiredViewAsType(source, R.id.tvSend, "field 'tvSend'", TextView.class);
    target.tvNumber = Utils.findRequiredViewAsType(source, R.id.tvNumber, "field 'tvNumber'", TextView.class);
    target.yan = Utils.findRequiredViewAsType(source, R.id.yan, "field 'yan'", ImageView.class);
    target.yan1 = Utils.findRequiredViewAsType(source, R.id.yan1, "field 'yan1'", ImageView.class);
    target.yan2 = Utils.findRequiredViewAsType(source, R.id.yan2, "field 'yan2'", ImageView.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    EditLoginPwdActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.llTitle = null;
    target.etOldPwd = null;
    target.etNewPwd = null;
    target.etRepeatPwd = null;
    target.tvConfirm = null;
    target.etCode = null;
    target.tvSend = null;
    target.tvNumber = null;
    target.yan = null;
    target.yan1 = null;
    target.yan2 = null;
    target.view_back = null;
  }
}
