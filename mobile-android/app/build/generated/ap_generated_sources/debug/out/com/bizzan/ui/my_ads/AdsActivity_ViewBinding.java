// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.my_ads;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AdsActivity_ViewBinding implements Unbinder {
  private AdsActivity target;

  @UiThread
  public AdsActivity_ViewBinding(AdsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AdsActivity_ViewBinding(AdsActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.ivReleseAd = Utils.findRequiredViewAsType(source, R.id.ivReleseAd, "field 'ivReleseAd'", ImageView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.rvAds = Utils.findRequiredViewAsType(source, R.id.rvAds, "field 'rvAds'", RecyclerView.class);
    target.llEmpty = Utils.findRequiredViewAsType(source, R.id.llEmpty, "field 'llEmpty'", LinearLayout.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    AdsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.ivReleseAd = null;
    target.llTitle = null;
    target.rvAds = null;
    target.llEmpty = null;
    target.view_back = null;
  }
}
