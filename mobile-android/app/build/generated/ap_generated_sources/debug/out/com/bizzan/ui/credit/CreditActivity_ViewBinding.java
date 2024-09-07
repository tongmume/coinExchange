// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.credit;

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

public class CreditActivity_ViewBinding implements Unbinder {
  private CreditActivity target;

  @UiThread
  public CreditActivity_ViewBinding(CreditActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CreditActivity_ViewBinding(CreditActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.tv_save = Utils.findRequiredViewAsType(source, R.id.tv_save, "field 'tv_save'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.ivIdFace = Utils.findRequiredViewAsType(source, R.id.ivIdFace, "field 'ivIdFace'", ImageView.class);
    target.ivBack = Utils.findRequiredViewAsType(source, R.id.ivBack, "field 'ivBack'", ImageView.class);
    target.ivHold = Utils.findRequiredViewAsType(source, R.id.ivHold, "field 'ivHold'", ImageView.class);
    target.text_shili = Utils.findRequiredViewAsType(source, R.id.text_shili, "field 'text_shili'", TextView.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    CreditActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.tv_save = null;
    target.llTitle = null;
    target.ivIdFace = null;
    target.ivBack = null;
    target.ivHold = null;
    target.text_shili = null;
    target.view_back = null;
  }
}
