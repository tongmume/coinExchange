// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.aboutus;

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

public class AboutJianJieActivity_ViewBinding implements Unbinder {
  private AboutJianJieActivity target;

  @UiThread
  public AboutJianJieActivity_ViewBinding(AboutJianJieActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AboutJianJieActivity_ViewBinding(AboutJianJieActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.ibRegist = Utils.findRequiredViewAsType(source, R.id.ibRegist, "field 'ibRegist'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.ivLogo = Utils.findRequiredViewAsType(source, R.id.ivLogo, "field 'ivLogo'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AboutJianJieActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.ibRegist = null;
    target.llTitle = null;
    target.ivLogo = null;
  }
}
