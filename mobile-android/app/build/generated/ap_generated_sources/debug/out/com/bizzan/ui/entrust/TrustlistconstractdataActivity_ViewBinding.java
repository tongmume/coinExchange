// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.entrust;

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

public class TrustlistconstractdataActivity_ViewBinding implements Unbinder {
  private TrustlistconstractdataActivity target;

  @UiThread
  public TrustlistconstractdataActivity_ViewBinding(TrustlistconstractdataActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TrustlistconstractdataActivity_ViewBinding(TrustlistconstractdataActivity target,
      View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageView.class);
    target.tvType = Utils.findRequiredViewAsType(source, R.id.tv_type, "field 'tvType'", TextView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tvTime'", TextView.class);
    target.tvOpenFlat = Utils.findRequiredViewAsType(source, R.id.tv_open_flat, "field 'tvOpenFlat'", TextView.class);
    target.tvFangxiang = Utils.findRequiredViewAsType(source, R.id.tv_fangxiang, "field 'tvFangxiang'", TextView.class);
    target.tvEntrustType = Utils.findRequiredViewAsType(source, R.id.tv_entrust_type, "field 'tvEntrustType'", TextView.class);
    target.tvTriggerPrince = Utils.findRequiredViewAsType(source, R.id.tv_trigger_prince, "field 'tvTriggerPrince'", TextView.class);
    target.tvEnterPrice = Utils.findRequiredViewAsType(source, R.id.tv_enter_price, "field 'tvEnterPrice'", TextView.class);
    target.tvTransactionPrice = Utils.findRequiredViewAsType(source, R.id.tv_transaction_price, "field 'tvTransactionPrice'", TextView.class);
    target.tvGuaranteeMoney = Utils.findRequiredViewAsType(source, R.id.tv_guarantee_money, "field 'tvGuaranteeMoney'", TextView.class);
    target.tvEntrustNumber = Utils.findRequiredViewAsType(source, R.id.tv_entrust_number, "field 'tvEntrustNumber'", TextView.class);
    target.tvPoundage = Utils.findRequiredViewAsType(source, R.id.tv_poundage, "field 'tvPoundage'", TextView.class);
    target.tvCompute = Utils.findRequiredViewAsType(source, R.id.tv_compute, "field 'tvCompute'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.ll_title, "field 'llTitle'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TrustlistconstractdataActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.tvType = null;
    target.tvTime = null;
    target.tvOpenFlat = null;
    target.tvFangxiang = null;
    target.tvEntrustType = null;
    target.tvTriggerPrince = null;
    target.tvEnterPrice = null;
    target.tvTransactionPrice = null;
    target.tvGuaranteeMoney = null;
    target.tvEntrustNumber = null;
    target.tvPoundage = null;
    target.tvCompute = null;
    target.llTitle = null;
  }
}
