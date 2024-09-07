// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.bind_account;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BindAccountActivity_ViewBinding implements Unbinder {
  private BindAccountActivity target;

  @UiThread
  public BindAccountActivity_ViewBinding(BindAccountActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BindAccountActivity_ViewBinding(BindAccountActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.tvAli = Utils.findRequiredViewAsType(source, R.id.tvAli, "field 'tvAli'", TextView.class);
    target.tvWeiChat = Utils.findRequiredViewAsType(source, R.id.tvWeiChat, "field 'tvWeiChat'", TextView.class);
    target.tvUnionPay = Utils.findRequiredViewAsType(source, R.id.tvUnionPay, "field 'tvUnionPay'", TextView.class);
    target.llAliAccount = Utils.findRequiredViewAsType(source, R.id.llAliAccount, "field 'llAliAccount'", LinearLayout.class);
    target.llWeiChatAccount = Utils.findRequiredViewAsType(source, R.id.llWeiChatAccount, "field 'llWeiChatAccount'", LinearLayout.class);
    target.llUnionPayAccount = Utils.findRequiredViewAsType(source, R.id.llUnionPayAccount, "field 'llUnionPayAccount'", LinearLayout.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    BindAccountActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.tvAli = null;
    target.tvWeiChat = null;
    target.tvUnionPay = null;
    target.llAliAccount = null;
    target.llWeiChatAccount = null;
    target.llUnionPayAccount = null;
    target.llTitle = null;
    target.view_back = null;
  }
}
