// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.extract;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ExtractActivity_ViewBinding implements Unbinder {
  private ExtractActivity target;

  @UiThread
  public ExtractActivity_ViewBinding(ExtractActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ExtractActivity_ViewBinding(ExtractActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.tvCanUse = Utils.findRequiredViewAsType(source, R.id.tvCanUse, "field 'tvCanUse'", TextView.class);
    target.tvUnit1 = Utils.findRequiredViewAsType(source, R.id.tvUnit1, "field 'tvUnit1'", TextView.class);
    target.etAddress = Utils.findRequiredViewAsType(source, R.id.etAddress, "field 'etAddress'", EditText.class);
    target.ivInto = Utils.findRequiredViewAsType(source, R.id.ivInto, "field 'ivInto'", ImageView.class);
    target.etCount = Utils.findRequiredViewAsType(source, R.id.etCount, "field 'etCount'", EditText.class);
    target.etServiceFee = Utils.findRequiredViewAsType(source, R.id.etServiceFee, "field 'etServiceFee'", EditText.class);
    target.tvUnit2 = Utils.findRequiredViewAsType(source, R.id.tvUnit2, "field 'tvUnit2'", TextView.class);
    target.tvAll = Utils.findRequiredViewAsType(source, R.id.tvAll, "field 'tvAll'", TextView.class);
    target.tvUnit3 = Utils.findRequiredViewAsType(source, R.id.tvUnit3, "field 'tvUnit3'", TextView.class);
    target.tvExtract = Utils.findRequiredViewAsType(source, R.id.tvExtract, "field 'tvExtract'", TextView.class);
    target.tvFinalCount = Utils.findRequiredViewAsType(source, R.id.tvFinalCount, "field 'tvFinalCount'", TextView.class);
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.etPassword, "field 'etPassword'", EditText.class);
    target.text_remark = Utils.findRequiredViewAsType(source, R.id.text_remark, "field 'text_remark'", TextView.class);
    target.layout_memo = Utils.findRequiredViewAsType(source, R.id.layout_memo, "field 'layout_memo'", LinearLayout.class);
    target.etRemark = Utils.findRequiredViewAsType(source, R.id.etext_remark, "field 'etRemark'", EditText.class);
    target.yan = Utils.findRequiredViewAsType(source, R.id.yan, "field 'yan'", ImageView.class);
    target.tvGetCode = Utils.findRequiredViewAsType(source, R.id.tvGetCode, "field 'tvGetCode'", TextView.class);
    target.etCode = Utils.findRequiredViewAsType(source, R.id.etCode, "field 'etCode'", EditText.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    ExtractActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.tvTitle = null;
    target.llTitle = null;
    target.tvCanUse = null;
    target.tvUnit1 = null;
    target.etAddress = null;
    target.ivInto = null;
    target.etCount = null;
    target.etServiceFee = null;
    target.tvUnit2 = null;
    target.tvAll = null;
    target.tvUnit3 = null;
    target.tvExtract = null;
    target.tvFinalCount = null;
    target.etPassword = null;
    target.text_remark = null;
    target.layout_memo = null;
    target.etRemark = null;
    target.yan = null;
    target.tvGetCode = null;
    target.etCode = null;
    target.view_back = null;
  }
}
