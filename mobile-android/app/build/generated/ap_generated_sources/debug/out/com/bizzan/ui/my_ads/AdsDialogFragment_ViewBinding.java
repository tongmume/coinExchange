// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.my_ads;

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

public class AdsDialogFragment_ViewBinding implements Unbinder {
  private AdsDialogFragment target;

  @UiThread
  public AdsDialogFragment_ViewBinding(AdsDialogFragment target, View source) {
    this.target = target;

    target.tvEdit = Utils.findRequiredViewAsType(source, R.id.tvEdit, "field 'tvEdit'", LinearLayout.class);
    target.tvReleaseAgain = Utils.findRequiredViewAsType(source, R.id.tvReleaseAgain, "field 'tvReleaseAgain'", LinearLayout.class);
    target.tvDelete = Utils.findRequiredViewAsType(source, R.id.tvDelete, "field 'tvDelete'", LinearLayout.class);
    target.text_shangjia = Utils.findRequiredViewAsType(source, R.id.text_shangjia, "field 'text_shangjia'", TextView.class);
    target.image = Utils.findRequiredViewAsType(source, R.id.image, "field 'image'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AdsDialogFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvEdit = null;
    target.tvReleaseAgain = null;
    target.tvDelete = null;
    target.text_shangjia = null;
    target.image = null;
  }
}
