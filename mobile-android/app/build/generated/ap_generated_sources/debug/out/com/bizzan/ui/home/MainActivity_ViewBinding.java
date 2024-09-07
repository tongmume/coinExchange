// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.home;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.flContainer = Utils.findRequiredViewAsType(source, R.id.flContainer, "field 'flContainer'", FrameLayout.class);
    target.llOne = Utils.findRequiredViewAsType(source, R.id.llOne, "field 'llOne'", LinearLayout.class);
    target.llTwo = Utils.findRequiredViewAsType(source, R.id.llTwo, "field 'llTwo'", LinearLayout.class);
    target.llThree = Utils.findRequiredViewAsType(source, R.id.llThree, "field 'llThree'", LinearLayout.class);
    target.llFour = Utils.findRequiredViewAsType(source, R.id.llFour, "field 'llFour'", LinearLayout.class);
    target.llFive = Utils.findRequiredViewAsType(source, R.id.llFive, "field 'llFive'", LinearLayout.class);
    target.llTab = Utils.findRequiredViewAsType(source, R.id.llTab, "field 'llTab'", LinearLayout.class);
    target.ibClose = Utils.findRequiredViewAsType(source, R.id.ibClose, "field 'ibClose'", ImageButton.class);
    target.tab = Utils.findRequiredViewAsType(source, R.id.tab, "field 'tab'", TabLayout.class);
    target.vpMenu = Utils.findRequiredViewAsType(source, R.id.vpMenu, "field 'vpMenu'", ViewPager.class);
    target.dlRoot = Utils.findRequiredViewAsType(source, R.id.dlRoot, "field 'dlRoot'", DrawerLayout.class);
    target.llSix = Utils.findRequiredViewAsType(source, R.id.llSix, "field 'llSix'", LinearLayout.class);
    target.llSeven = Utils.findRequiredViewAsType(source, R.id.llSeven, "field 'llSeven'", LinearLayout.class);
    target.llCoins = Utils.findRequiredViewAsType(source, R.id.ll_coins, "field 'llCoins'", LinearLayout.class);
    target.ibCloseConstract = Utils.findRequiredViewAsType(source, R.id.ibClose_constract, "field 'ibCloseConstract'", ImageButton.class);
    target.vpConstract = Utils.findRequiredViewAsType(source, R.id.vp_constract, "field 'vpConstract'", ViewPager.class);
    target.llContract = Utils.findRequiredViewAsType(source, R.id.ll_contract, "field 'llContract'", LinearLayout.class);
    target.ibCloseOption = Utils.findRequiredViewAsType(source, R.id.ibClose_option, "field 'ibCloseOption'", ImageButton.class);
    target.vpOption = Utils.findRequiredViewAsType(source, R.id.vp_option, "field 'vpOption'", ViewPager.class);
    target.llOption = Utils.findRequiredViewAsType(source, R.id.ll_option, "field 'llOption'", LinearLayout.class);

    Context context = source.getContext();
    Resources res = context.getResources();
    target.titles = res.getStringArray(R.array.home_two_top_tab);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.flContainer = null;
    target.llOne = null;
    target.llTwo = null;
    target.llThree = null;
    target.llFour = null;
    target.llFive = null;
    target.llTab = null;
    target.ibClose = null;
    target.tab = null;
    target.vpMenu = null;
    target.dlRoot = null;
    target.llSix = null;
    target.llSeven = null;
    target.llCoins = null;
    target.ibCloseConstract = null;
    target.vpConstract = null;
    target.llContract = null;
    target.ibCloseOption = null;
    target.vpOption = null;
    target.llOption = null;
  }
}
