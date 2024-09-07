// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.credit;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CreditInfoActivity_ViewBinding implements Unbinder {
  private CreditInfoActivity target;

  @UiThread
  public CreditInfoActivity_ViewBinding(CreditInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CreditInfoActivity_ViewBinding(CreditInfoActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.tv_save = Utils.findRequiredViewAsType(source, R.id.tv_save, "field 'tv_save'", TextView.class);
    target.et_name = Utils.findRequiredViewAsType(source, R.id.et_name, "field 'et_name'", EditText.class);
    target.et_id_card_no = Utils.findRequiredViewAsType(source, R.id.et_id_card_no, "field 'et_id_card_no'", EditText.class);
    target.ll_id_card_photo = Utils.findRequiredViewAsType(source, R.id.ll_id_card_photo, "field 'll_id_card_photo'", LinearLayout.class);
    target.tv_photo_state = Utils.findRequiredViewAsType(source, R.id.tv_photo_state, "field 'tv_photo_state'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.text_yuanyin = Utils.findRequiredViewAsType(source, R.id.text_yuanyin, "field 'text_yuanyin'", TextView.class);
    target.line_wei = Utils.findRequiredViewAsType(source, R.id.line_wei, "field 'line_wei'", LinearLayout.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    CreditInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.tv_save = null;
    target.et_name = null;
    target.et_id_card_no = null;
    target.ll_id_card_photo = null;
    target.tv_photo_state = null;
    target.llTitle = null;
    target.text_yuanyin = null;
    target.line_wei = null;
    target.view_back = null;
  }
}
