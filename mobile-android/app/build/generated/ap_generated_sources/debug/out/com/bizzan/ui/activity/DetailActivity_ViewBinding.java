// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailActivity_ViewBinding implements Unbinder {
  private DetailActivity target;

  @UiThread
  public DetailActivity_ViewBinding(DetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailActivity_ViewBinding(DetailActivity target, View source) {
    this.target = target;

    target.llTitle = Utils.findRequiredView(source, R.id.llTitle, "field 'llTitle'");
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
    target.tvdetail = Utils.findRequiredViewAsType(source, R.id.tvdetail, "field 'tvdetail'", TextView.class);
    target.contentLayout = Utils.findRequiredViewAsType(source, R.id.contentlayout, "field 'contentLayout'", LinearLayout.class);
    target.amountlayout = Utils.findRequiredView(source, R.id.amountlayout, "field 'amountlayout'");
    target.alreadyamount = Utils.findRequiredViewAsType(source, R.id.alreadyamount, "field 'alreadyamount'", TextView.class);
    target.leftamount = Utils.findRequiredViewAsType(source, R.id.leftamount, "field 'leftamount'", TextView.class);
    target.holderlayout = Utils.findRequiredView(source, R.id.holderlayout, "field 'holderlayout'");
    target.myalreadyholdamount = Utils.findRequiredViewAsType(source, R.id.myalreadyholdamount, "field 'myalreadyholdamount'", TextView.class);
    target.alreadyholdamount = Utils.findRequiredViewAsType(source, R.id.alreadyholdamount, "field 'alreadyholdamount'", TextView.class);
    target.freezeAmount = Utils.findRequiredViewAsType(source, R.id.freezeAmount, "field 'freezeAmount'", TextView.class);
    target.inputlayout = Utils.findRequiredView(source, R.id.inputlayout, "field 'inputlayout'");
    target.inputlabel = Utils.findRequiredViewAsType(source, R.id.inputlabel, "field 'inputlabel'", TextView.class);
    target.inputview = Utils.findRequiredViewAsType(source, R.id.inputview, "field 'inputview'", EditText.class);
    target.inputunit = Utils.findRequiredViewAsType(source, R.id.inputunit, "field 'inputunit'", TextView.class);
    target.tvbalance = Utils.findRequiredViewAsType(source, R.id.tvbalance, "field 'tvbalance'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llTitle = null;
    target.tvTitle = null;
    target.tvdetail = null;
    target.contentLayout = null;
    target.amountlayout = null;
    target.alreadyamount = null;
    target.leftamount = null;
    target.holderlayout = null;
    target.myalreadyholdamount = null;
    target.alreadyholdamount = null;
    target.freezeAmount = null;
    target.inputlayout = null;
    target.inputlabel = null;
    target.inputview = null;
    target.inputunit = null;
    target.tvbalance = null;
  }
}
