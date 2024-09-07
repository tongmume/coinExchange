// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.myinfo;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import com.bizzan.customview.CircleImageView;
import com.kyleduo.switchbutton.SwitchButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyInfoActivity_ViewBinding implements Unbinder {
  private MyInfoActivity target;

  @UiThread
  public MyInfoActivity_ViewBinding(MyInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyInfoActivity_ViewBinding(MyInfoActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.ibRegist = Utils.findRequiredViewAsType(source, R.id.ibRegist, "field 'ibRegist'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.llAccountPwd = Utils.findRequiredViewAsType(source, R.id.llAccountPwd, "field 'llAccountPwd'", LinearLayout.class);
    target.ivHeader = Utils.findRequiredViewAsType(source, R.id.ivHeader, "field 'ivHeader'", CircleImageView.class);
    target.tvPhone = Utils.findRequiredViewAsType(source, R.id.tvPhone, "field 'tvPhone'", TextView.class);
    target.tvEmail = Utils.findRequiredViewAsType(source, R.id.tvEmail, "field 'tvEmail'", TextView.class);
    target.tvLoginPwd = Utils.findRequiredViewAsType(source, R.id.tvLoginPwd, "field 'tvLoginPwd'", TextView.class);
    target.tvAcountPwd = Utils.findRequiredViewAsType(source, R.id.tvAcountPwd, "field 'tvAcountPwd'", TextView.class);
    target.tvIdCard = Utils.findRequiredViewAsType(source, R.id.tvIdCard, "field 'tvIdCard'", TextView.class);
    target.llPhone = Utils.findRequiredViewAsType(source, R.id.llPhone, "field 'llPhone'", LinearLayout.class);
    target.llEmail = Utils.findRequiredViewAsType(source, R.id.llEmail, "field 'llEmail'", LinearLayout.class);
    target.llLoginPwd = Utils.findRequiredViewAsType(source, R.id.llLoginPwd, "field 'llLoginPwd'", LinearLayout.class);
    target.llIdCard = Utils.findRequiredViewAsType(source, R.id.llIdCard, "field 'llIdCard'", LinearLayout.class);
    target.tvAccount = Utils.findRequiredViewAsType(source, R.id.tvAccount, "field 'tvAccount'", TextView.class);
    target.llAccount = Utils.findRequiredViewAsType(source, R.id.llAccount, "field 'llAccount'", LinearLayout.class);
    target.llLockSet = Utils.findRequiredViewAsType(source, R.id.llLockSet, "field 'llLockSet'", LinearLayout.class);
    target.switchButton = Utils.findRequiredViewAsType(source, R.id.switchButton, "field 'switchButton'", SwitchButton.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    MyInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.ibRegist = null;
    target.llTitle = null;
    target.llAccountPwd = null;
    target.ivHeader = null;
    target.tvPhone = null;
    target.tvEmail = null;
    target.tvLoginPwd = null;
    target.tvAcountPwd = null;
    target.tvIdCard = null;
    target.llPhone = null;
    target.llEmail = null;
    target.llLoginPwd = null;
    target.llIdCard = null;
    target.tvAccount = null;
    target.llAccount = null;
    target.llLockSet = null;
    target.switchButton = null;
    target.view_back = null;
  }
}
