// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.wallet;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WalletDialogFragment_ViewBinding implements Unbinder {
  private WalletDialogFragment target;

  @UiThread
  public WalletDialogFragment_ViewBinding(WalletDialogFragment target, View source) {
    this.target = target;

    target.tvRecharge = Utils.findRequiredViewAsType(source, R.id.tvRecharge, "field 'tvRecharge'", TextView.class);
    target.tvExtract = Utils.findRequiredViewAsType(source, R.id.tvExtract, "field 'tvExtract'", TextView.class);
    target.llContainer = Utils.findRequiredViewAsType(source, R.id.llContainer, "field 'llContainer'", LinearLayout.class);
    target.tvMatch = Utils.findRequiredViewAsType(source, R.id.tvMatch, "field 'tvMatch'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WalletDialogFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvRecharge = null;
    target.tvExtract = null;
    target.llContainer = null;
    target.tvMatch = null;
  }
}
