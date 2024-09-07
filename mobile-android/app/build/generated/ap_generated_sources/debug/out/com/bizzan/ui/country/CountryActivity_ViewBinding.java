// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.country;

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

public class CountryActivity_ViewBinding implements Unbinder {
  private CountryActivity target;

  @UiThread
  public CountryActivity_ViewBinding(CountryActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CountryActivity_ViewBinding(CountryActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
    target.ibHelp = Utils.findRequiredViewAsType(source, R.id.ibHelp, "field 'ibHelp'", ImageButton.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.rvCountry = Utils.findRequiredViewAsType(source, R.id.rvCountry, "field 'rvCountry'", RecyclerView.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    CountryActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.tvTitle = null;
    target.ibHelp = null;
    target.llTitle = null;
    target.rvCountry = null;
    target.view_back = null;
  }
}
