// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.setting;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LanguageActivity_ViewBinding implements Unbinder {
  private LanguageActivity target;

  @UiThread
  public LanguageActivity_ViewBinding(LanguageActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LanguageActivity_ViewBinding(LanguageActivity target, View source) {
    this.target = target;

    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.imageZh = Utils.findRequiredViewAsType(source, R.id.imageZh, "field 'imageZh'", ImageView.class);
    target.imageEh = Utils.findRequiredViewAsType(source, R.id.imageEh, "field 'imageEh'", ImageView.class);
    target.imageJp = Utils.findRequiredViewAsType(source, R.id.imageJp, "field 'imageJp'", ImageView.class);
    target.imageDe = Utils.findRequiredViewAsType(source, R.id.imageDe, "field 'imageDe'", ImageView.class);
    target.imageKr = Utils.findRequiredViewAsType(source, R.id.imageKr, "field 'imageKr'", ImageView.class);
    target.imageFr = Utils.findRequiredViewAsType(source, R.id.imageFr, "field 'imageFr'", ImageView.class);
    target.imageHk = Utils.findRequiredViewAsType(source, R.id.imageHk, "field 'imageHk'", ImageView.class);
    target.imageIt = Utils.findRequiredViewAsType(source, R.id.imageIt, "field 'imageIt'", ImageView.class);
    target.imageEs = Utils.findRequiredViewAsType(source, R.id.imageEs, "field 'imageEs'", ImageView.class);
    target.imagePt = Utils.findRequiredViewAsType(source, R.id.imagePt, "field 'imagePt'", ImageView.class);
    target.imageTk = Utils.findRequiredViewAsType(source, R.id.imageTk, "field 'imageTk'", ImageView.class);
    target.imageId = Utils.findRequiredViewAsType(source, R.id.imageId, "field 'imageId'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LanguageActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llTitle = null;
    target.imageZh = null;
    target.imageEh = null;
    target.imageJp = null;
    target.imageDe = null;
    target.imageKr = null;
    target.imageFr = null;
    target.imageHk = null;
    target.imageIt = null;
    target.imageEs = null;
    target.imagePt = null;
    target.imageTk = null;
    target.imageId = null;
  }
}
