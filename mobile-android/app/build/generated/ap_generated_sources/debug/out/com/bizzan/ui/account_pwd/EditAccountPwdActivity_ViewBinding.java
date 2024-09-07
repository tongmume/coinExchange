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

public class EditAccountPwdActivity_ViewBinding implements Unbinder {
  private EditAccountPwdActivity target;

  @UiThread
  public EditAccountPwdActivity_ViewBinding(EditAccountPwdActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditAccountPwdActivity_ViewBinding(EditAccountPwdActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.etLoginPwd = Utils.findRequiredViewAsType(source, R.id.etLoginPwd, "field 'etLoginPwd'", EditText.class);
    target.etOld = Utils.findRequiredViewAsType(source, R.id.etOld, "field 'etOld'", EditText.class);
    target.etNew = Utils.findRequiredViewAsType(source, R.id.sellPrice, "field 'etNew'", EditText.class);
    target.etRepeate = Utils.findRequiredViewAsType(source, R.id.etRepeate, "field 'etRepeate'", EditText.class);
    target.tvForgot = Utils.findRequiredViewAsType(source, R.id.tvForgot, "field 'tvForgot'", TextView.class);
    target.tvEdit = Utils.findRequiredViewAsType(source, R.id.tvEdit, "field 'tvEdit'", TextView.class);
    target.yan = Utils.findRequiredViewAsType(source, R.id.yan, "field 'yan'", ImageView.class);
    target.yan1 = Utils.findRequiredViewAsType(source, R.id.yan1, "field 'yan1'", ImageView.class);
    target.yan2 = Utils.findRequiredViewAsType(source, R.id.yan2, "field 'yan2'", ImageView.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    EditAccountPwdActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.llTitle = null;
    target.etLoginPwd = null;
    target.etOld = null;
    target.etNew = null;
    target.etRepeate = null;
    target.tvForgot = null;
    target.tvEdit = null;
    target.yan = null;
    target.yan1 = null;
    target.yan2 = null;
    target.view_back = null;
  }
}
