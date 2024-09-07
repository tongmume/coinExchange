// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.entrust;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TrustDetailActivity_ViewBinding implements Unbinder {
  private TrustDetailActivity target;

  @UiThread
  public TrustDetailActivity_ViewBinding(TrustDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TrustDetailActivity_ViewBinding(TrustDetailActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.barTitle = Utils.findRequiredViewAsType(source, R.id.bar_title, "field 'barTitle'", TextView.class);
    target.mDetailType = Utils.findRequiredViewAsType(source, R.id.mDetailType, "field 'mDetailType'", TextView.class);
    target.mDetailName = Utils.findRequiredViewAsType(source, R.id.mDetailName, "field 'mDetailName'", TextView.class);
    target.mDetailOne = Utils.findRequiredViewAsType(source, R.id.mDetailOne, "field 'mDetailOne'", TextView.class);
    target.mDetailTwo = Utils.findRequiredViewAsType(source, R.id.mDetailTwo, "field 'mDetailTwo'", TextView.class);
    target.mDetailThree = Utils.findRequiredViewAsType(source, R.id.mDetailThree, "field 'mDetailThree'", TextView.class);
    target.mDetailLayout = Utils.findRequiredViewAsType(source, R.id.detailLayout, "field 'mDetailLayout'", LinearLayout.class);
    target.mDetailFour = Utils.findRequiredViewAsType(source, R.id.mDetailFour, "field 'mDetailFour'", TextView.class);
    target.mDetailFive = Utils.findRequiredViewAsType(source, R.id.mDetailFive, "field 'mDetailFive'", TextView.class);
    target.mDetailTotal = Utils.findRequiredViewAsType(source, R.id.mDetailTotal, "field 'mDetailTotal'", TextView.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.mRecyclerView, "field 'mRecyclerView'", RecyclerView.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    TrustDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.llTitle = null;
    target.barTitle = null;
    target.mDetailType = null;
    target.mDetailName = null;
    target.mDetailOne = null;
    target.mDetailTwo = null;
    target.mDetailThree = null;
    target.mDetailLayout = null;
    target.mDetailFour = null;
    target.mDetailFive = null;
    target.mDetailTotal = null;
    target.mRecyclerView = null;
    target.view_back = null;
  }
}
