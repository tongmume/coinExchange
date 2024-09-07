// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HeaderSelectDialogFragment_ViewBinding implements Unbinder {
  private HeaderSelectDialogFragment target;

  @UiThread
  public HeaderSelectDialogFragment_ViewBinding(HeaderSelectDialogFragment target, View source) {
    this.target = target;

    target.tvTakePhone = Utils.findRequiredViewAsType(source, R.id.tvTakePhone, "field 'tvTakePhone'", TextView.class);
    target.llTakePhoto = Utils.findRequiredViewAsType(source, R.id.llTakePhoto, "field 'llTakePhoto'", LinearLayout.class);
    target.tvAlbum = Utils.findRequiredViewAsType(source, R.id.tvAlbum, "field 'tvAlbum'", TextView.class);
    target.llChoseAlbum = Utils.findRequiredViewAsType(source, R.id.llChoseAlbum, "field 'llChoseAlbum'", LinearLayout.class);
    target.llContent = Utils.findRequiredViewAsType(source, R.id.llContent, "field 'llContent'", LinearLayout.class);
    target.line_quxiao = Utils.findRequiredViewAsType(source, R.id.line_quxiao, "field 'line_quxiao'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HeaderSelectDialogFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTakePhone = null;
    target.llTakePhoto = null;
    target.tvAlbum = null;
    target.llChoseAlbum = null;
    target.llContent = null;
    target.line_quxiao = null;
  }
}
