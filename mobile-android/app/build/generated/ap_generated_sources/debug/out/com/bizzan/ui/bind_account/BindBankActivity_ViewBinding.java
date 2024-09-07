// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.bind_account;

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

public class BindBankActivity_ViewBinding implements Unbinder {
  private BindBankActivity target;

  @UiThread
  public BindBankActivity_ViewBinding(BindBankActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BindBankActivity_ViewBinding(BindBankActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
    target.etName = Utils.findRequiredViewAsType(source, R.id.etName, "field 'etName'", EditText.class);
    target.llBank = Utils.findRequiredViewAsType(source, R.id.llBank, "field 'llBank'", LinearLayout.class);
    target.etBranch = Utils.findRequiredViewAsType(source, R.id.etBranch, "field 'etBranch'", EditText.class);
    target.llBranch = Utils.findRequiredViewAsType(source, R.id.llBranch, "field 'llBranch'", LinearLayout.class);
    target.tvAccount = Utils.findRequiredViewAsType(source, R.id.tvAccount, "field 'tvAccount'", TextView.class);
    target.etAccount = Utils.findRequiredViewAsType(source, R.id.etAccount, "field 'etAccount'", EditText.class);
    target.etConfirmAccount = Utils.findRequiredViewAsType(source, R.id.etConfirmAccount, "field 'etConfirmAccount'", EditText.class);
    target.llConfirmAccount = Utils.findRequiredViewAsType(source, R.id.llConfirmAccount, "field 'llConfirmAccount'", LinearLayout.class);
    target.etNewPwd = Utils.findRequiredViewAsType(source, R.id.etNewPwd, "field 'etNewPwd'", EditText.class);
    target.tvConfirm = Utils.findRequiredViewAsType(source, R.id.tvConfirm, "field 'tvConfirm'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.tvBank = Utils.findRequiredViewAsType(source, R.id.tvBank, "field 'tvBank'", TextView.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    BindBankActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.tvTitle = null;
    target.etName = null;
    target.llBank = null;
    target.etBranch = null;
    target.llBranch = null;
    target.tvAccount = null;
    target.etAccount = null;
    target.etConfirmAccount = null;
    target.llConfirmAccount = null;
    target.etNewPwd = null;
    target.tvConfirm = null;
    target.llTitle = null;
    target.tvBank = null;
    target.view_back = null;
  }
}
