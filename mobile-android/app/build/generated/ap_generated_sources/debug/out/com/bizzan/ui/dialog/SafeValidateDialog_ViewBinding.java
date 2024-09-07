// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SafeValidateDialog_ViewBinding implements Unbinder {
  private SafeValidateDialog target;

  @UiThread
  public SafeValidateDialog_ViewBinding(SafeValidateDialog target, View source) {
    this.target = target;

    target.label = Utils.findRequiredViewAsType(source, R.id.label, "field 'label'", TextView.class);
    target.btnclose = Utils.findRequiredView(source, R.id.btnclose, "field 'btnclose'");
    target.editText = Utils.findRequiredViewAsType(source, R.id.etCode, "field 'editText'", EditText.class);
    target.tvmobile = Utils.findRequiredViewAsType(source, R.id.tvmobile, "field 'tvmobile'", TextView.class);
    target.tvGetCode = Utils.findRequiredViewAsType(source, R.id.tvGetCode, "field 'tvGetCode'", TextView.class);
    target.btnsubmit = Utils.findRequiredView(source, R.id.btnSubmit, "field 'btnsubmit'");
  }

  @Override
  @CallSuper
  public void unbind() {
    SafeValidateDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.label = null;
    target.btnclose = null;
    target.editText = null;
    target.tvmobile = null;
    target.tvGetCode = null;
    target.btnsubmit = null;
  }
}
