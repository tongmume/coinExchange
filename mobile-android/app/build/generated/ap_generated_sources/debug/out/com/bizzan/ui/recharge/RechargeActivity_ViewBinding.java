// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.recharge;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RechargeActivity_ViewBinding implements Unbinder {
  private RechargeActivity target;

  @UiThread
  public RechargeActivity_ViewBinding(RechargeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RechargeActivity_ViewBinding(RechargeActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
    target.tvAddressText = Utils.findRequiredViewAsType(source, R.id.tvAddressText, "field 'tvAddressText'", TextView.class);
    target.ivAddress = Utils.findRequiredViewAsType(source, R.id.ivAddress, "field 'ivAddress'", ImageView.class);
    target.tvAddress = Utils.findRequiredViewAsType(source, R.id.tvAddress, "field 'tvAddress'", TextView.class);
    target.llAlbum = Utils.findRequiredViewAsType(source, R.id.llAlbum, "field 'llAlbum'", LinearLayout.class);
    target.layout_memo = Utils.findRequiredViewAsType(source, R.id.layout_memo, "field 'layout_memo'", LinearLayout.class);
    target.llCopy = Utils.findRequiredViewAsType(source, R.id.llCopy, "field 'llCopy'", LinearLayout.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.text_deposit = Utils.findRequiredViewAsType(source, R.id.text_deposit, "field 'text_deposit'", TextView.class);
    target.text_memo = Utils.findRequiredViewAsType(source, R.id.text_memo, "field 'text_memo'", TextView.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    RechargeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.tvTitle = null;
    target.tvAddressText = null;
    target.ivAddress = null;
    target.tvAddress = null;
    target.llAlbum = null;
    target.layout_memo = null;
    target.llCopy = null;
    target.llTitle = null;
    target.text_deposit = null;
    target.text_memo = null;
    target.view_back = null;
  }
}
