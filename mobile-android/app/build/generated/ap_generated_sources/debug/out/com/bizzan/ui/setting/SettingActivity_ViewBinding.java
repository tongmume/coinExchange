// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.setting;

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

public class SettingActivity_ViewBinding implements Unbinder {
  private SettingActivity target;

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.ibRegist = Utils.findRequiredViewAsType(source, R.id.ibRegist, "field 'ibRegist'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.llLanguage = Utils.findRequiredViewAsType(source, R.id.llLanguage, "field 'llLanguage'", LinearLayout.class);
    target.llFeed = Utils.findRequiredViewAsType(source, R.id.llFeed, "field 'llFeed'", LinearLayout.class);
    target.llAboutUs = Utils.findRequiredViewAsType(source, R.id.llAboutUs, "field 'llAboutUs'", LinearLayout.class);
    target.llVersion = Utils.findRequiredViewAsType(source, R.id.llVersion, "field 'llVersion'", LinearLayout.class);
    target.tvVersionNumber = Utils.findRequiredViewAsType(source, R.id.tvVersionNumber, "field 'tvVersionNumber'", TextView.class);
    target.tvLogOut = Utils.findRequiredViewAsType(source, R.id.tvLogOut, "field 'tvLogOut'", TextView.class);
    target.line_gonggao = Utils.findRequiredViewAsType(source, R.id.line_gonggao, "field 'line_gonggao'", LinearLayout.class);
    target.line_bangzhu = Utils.findRequiredViewAsType(source, R.id.line_bangzhu, "field 'line_bangzhu'", LinearLayout.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    SettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.ibRegist = null;
    target.llTitle = null;
    target.llLanguage = null;
    target.llFeed = null;
    target.llAboutUs = null;
    target.llVersion = null;
    target.tvVersionNumber = null;
    target.tvLogOut = null;
    target.line_gonggao = null;
    target.line_bangzhu = null;
    target.view_back = null;
  }
}
