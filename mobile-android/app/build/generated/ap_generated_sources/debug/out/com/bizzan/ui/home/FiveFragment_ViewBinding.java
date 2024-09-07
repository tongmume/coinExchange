// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FiveFragment_ViewBinding implements Unbinder {
  private FiveFragment target;

  @UiThread
  public FiveFragment_ViewBinding(FiveFragment target, View source) {
    this.target = target;

    target.llTop = Utils.findRequiredViewAsType(source, R.id.llTop, "field 'llTop'", LinearLayout.class);
    target.llAccount = Utils.findRequiredViewAsType(source, R.id.llAccount, "field 'llAccount'", LinearLayout.class);
    target.llOrder = Utils.findRequiredViewAsType(source, R.id.llOrder, "field 'llOrder'", LinearLayout.class);
    target.line_lab = Utils.findRequiredViewAsType(source, R.id.line_lab, "field 'line_lab'", LinearLayout.class);
    target.llAds = Utils.findRequiredViewAsType(source, R.id.llAds, "field 'llAds'", LinearLayout.class);
    target.line_top = Utils.findRequiredViewAsType(source, R.id.line_top, "field 'line_top'", LinearLayout.class);
    target.line_zican = Utils.findRequiredViewAsType(source, R.id.line_zican, "field 'line_zican'", LinearLayout.class);
    target.line_shoukuan = Utils.findRequiredViewAsType(source, R.id.line_shoukuan, "field 'line_shoukuan'", LinearLayout.class);
    target.line_jypwd = Utils.findRequiredViewAsType(source, R.id.line_jypwd, "field 'line_jypwd'", LinearLayout.class);
    target.line_bibi = Utils.findRequiredViewAsType(source, R.id.line_bibi, "field 'line_bibi'", LinearLayout.class);
    target.line_ctc = Utils.findRequiredViewAsType(source, R.id.line_ctc, "field 'line_ctc'", LinearLayout.class);
    target.line_security = Utils.findRequiredViewAsType(source, R.id.line_security, "field 'line_security'", LinearLayout.class);
    target.llSafe = Utils.findRequiredViewAsType(source, R.id.llSafe, "field 'llSafe'", ImageView.class);
    target.llSettings = Utils.findRequiredViewAsType(source, R.id.llSettings, "field 'llSettings'", ImageView.class);
    target.llMyinfo = Utils.findRequiredViewAsType(source, R.id.llMyinfo, "field 'llMyinfo'", LinearLayout.class);
    target.tvNickName = Utils.findRequiredViewAsType(source, R.id.tvNickName, "field 'tvNickName'", TextView.class);
    target.tvAccount = Utils.findRequiredViewAsType(source, R.id.tvAccount, "field 'tvAccount'", TextView.class);
    target.tvLevelOneCount = Utils.findRequiredViewAsType(source, R.id.tvLevelOneCount, "field 'tvLevelOneCount'", TextView.class);
    target.tvLevelTwoCount = Utils.findRequiredViewAsType(source, R.id.tvLevelTwoCount, "field 'tvLevelTwoCount'", TextView.class);
    target.tvEstimatedReward = Utils.findRequiredViewAsType(source, R.id.tvEstimatedReward, "field 'tvEstimatedReward'", TextView.class);
    target.tvCurrentLevel = Utils.findRequiredViewAsType(source, R.id.tvCurrentLevel, "field 'tvCurrentLevel'", TextView.class);
    target.tvMyPromotionCode = Utils.findRequiredViewAsType(source, R.id.tvMyPromotionCode, "field 'tvMyPromotionCode'", TextView.class);
    target.ivHeader = Utils.findRequiredViewAsType(source, R.id.ivHeader, "field 'ivHeader'", ImageView.class);
    target.llEntrust = Utils.findRequiredViewAsType(source, R.id.llEntrust, "field 'llEntrust'", LinearLayout.class);
    target.img = Utils.findRequiredViewAsType(source, R.id.img, "field 'img'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FiveFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llTop = null;
    target.llAccount = null;
    target.llOrder = null;
    target.line_lab = null;
    target.llAds = null;
    target.line_top = null;
    target.line_zican = null;
    target.line_shoukuan = null;
    target.line_jypwd = null;
    target.line_bibi = null;
    target.line_ctc = null;
    target.line_security = null;
    target.llSafe = null;
    target.llSettings = null;
    target.llMyinfo = null;
    target.tvNickName = null;
    target.tvAccount = null;
    target.tvLevelOneCount = null;
    target.tvLevelTwoCount = null;
    target.tvEstimatedReward = null;
    target.tvCurrentLevel = null;
    target.tvMyPromotionCode = null;
    target.ivHeader = null;
    target.llEntrust = null;
    target.img = null;
  }
}
