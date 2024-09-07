// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.releaseAd;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReleaseAdsActivity_ViewBinding implements Unbinder {
  private ReleaseAdsActivity target;

  @UiThread
  public ReleaseAdsActivity_ViewBinding(ReleaseAdsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ReleaseAdsActivity_ViewBinding(ReleaseAdsActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
    target.ibHelp = Utils.findRequiredViewAsType(source, R.id.ibHelp, "field 'ibHelp'", ImageButton.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.tvCoin = Utils.findRequiredViewAsType(source, R.id.tvCoin, "field 'tvCoin'", TextView.class);
    target.llCoin = Utils.findRequiredViewAsType(source, R.id.llCoin, "field 'llCoin'", LinearLayout.class);
    target.llCountry = Utils.findRequiredViewAsType(source, R.id.llCountry, "field 'llCountry'", LinearLayout.class);
    target.llPayWay = Utils.findRequiredViewAsType(source, R.id.llPayWay, "field 'llPayWay'", LinearLayout.class);
    target.tvCountry = Utils.findRequiredViewAsType(source, R.id.tvCountry, "field 'tvCountry'", TextView.class);
    target.tvCoinKind = Utils.findRequiredViewAsType(source, R.id.tvCoinKind, "field 'tvCoinKind'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tvPrice, "field 'tvPrice'", TextView.class);
    target.tvOverflow = Utils.findRequiredViewAsType(source, R.id.tvOverflow, "field 'tvOverflow'", EditText.class);
    target.etMin = Utils.findRequiredViewAsType(source, R.id.etMin, "field 'etMin'", EditText.class);
    target.etMax = Utils.findRequiredViewAsType(source, R.id.etMax, "field 'etMax'", EditText.class);
    target.tvCountText = Utils.findRequiredViewAsType(source, R.id.tvCountText, "field 'tvCountText'", TextView.class);
    target.etCount = Utils.findRequiredViewAsType(source, R.id.etCount, "field 'etCount'", EditText.class);
    target.tvPayWayText = Utils.findRequiredViewAsType(source, R.id.tvPayWayText, "field 'tvPayWayText'", TextView.class);
    target.tvPayWay = Utils.findRequiredViewAsType(source, R.id.tvPayWay, "field 'tvPayWay'", TextView.class);
    target.tvPayTime = Utils.findRequiredViewAsType(source, R.id.tvPayTime, "field 'tvPayTime'", TextView.class);
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.etPassword, "field 'etPassword'", EditText.class);
    target.etMessage = Utils.findRequiredViewAsType(source, R.id.etMessage, "field 'etMessage'", EditText.class);
    target.tvRelease = Utils.findRequiredViewAsType(source, R.id.tvRelease, "field 'tvRelease'", TextView.class);
    target.llTime = Utils.findRequiredViewAsType(source, R.id.llTime, "field 'llTime'", LinearLayout.class);
    target.line0 = Utils.findRequiredView(source, R.id.line0, "field 'line0'");
    target.rbFixed = Utils.findRequiredViewAsType(source, R.id.rbFixed, "field 'rbFixed'", RadioButton.class);
    target.rbChange = Utils.findRequiredViewAsType(source, R.id.rbChange, "field 'rbChange'", RadioButton.class);
    target.rgPriceType = Utils.findRequiredViewAsType(source, R.id.rgPriceType, "field 'rgPriceType'", RadioGroup.class);
    target.rbYes = Utils.findRequiredViewAsType(source, R.id.rbYes, "field 'rbYes'", RadioButton.class);
    target.rbNo = Utils.findRequiredViewAsType(source, R.id.rbNo, "field 'rbNo'", RadioButton.class);
    target.rgReply = Utils.findRequiredViewAsType(source, R.id.rgReply, "field 'rgReply'", RadioGroup.class);
    target.etReplyContent = Utils.findRequiredViewAsType(source, R.id.etReplyContent, "field 'etReplyContent'", EditText.class);
    target.etjyPrice = Utils.findRequiredViewAsType(source, R.id.etjyPrice, "field 'etjyPrice'", EditText.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    ReleaseAdsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.tvTitle = null;
    target.ibHelp = null;
    target.llTitle = null;
    target.tvCoin = null;
    target.llCoin = null;
    target.llCountry = null;
    target.llPayWay = null;
    target.tvCountry = null;
    target.tvCoinKind = null;
    target.tvPrice = null;
    target.tvOverflow = null;
    target.etMin = null;
    target.etMax = null;
    target.tvCountText = null;
    target.etCount = null;
    target.tvPayWayText = null;
    target.tvPayWay = null;
    target.tvPayTime = null;
    target.etPassword = null;
    target.etMessage = null;
    target.tvRelease = null;
    target.llTime = null;
    target.line0 = null;
    target.rbFixed = null;
    target.rbChange = null;
    target.rgPriceType = null;
    target.rbYes = null;
    target.rbNo = null;
    target.rgReply = null;
    target.etReplyContent = null;
    target.etjyPrice = null;
    target.view_back = null;
  }
}
