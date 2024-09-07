// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.ctc;

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

public class CTCOrderDetailActivity_ViewBinding implements Unbinder {
  private CTCOrderDetailActivity target;

  @UiThread
  public CTCOrderDetailActivity_ViewBinding(CTCOrderDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CTCOrderDetailActivity_ViewBinding(CTCOrderDetailActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.tvDirection = Utils.findRequiredViewAsType(source, R.id.tvDirection, "field 'tvDirection'", TextView.class);
    target.tvCountDown = Utils.findRequiredViewAsType(source, R.id.tvCountDown, "field 'tvCountDown'", TextView.class);
    target.tvAmount = Utils.findRequiredViewAsType(source, R.id.tvAmount, "field 'tvAmount'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tvPrice, "field 'tvPrice'", TextView.class);
    target.tvMoney = Utils.findRequiredViewAsType(source, R.id.tvMoney, "field 'tvMoney'", TextView.class);
    target.llBank = Utils.findRequiredViewAsType(source, R.id.llBank, "field 'llBank'", LinearLayout.class);
    target.llWechatpay = Utils.findRequiredViewAsType(source, R.id.llWechatpay, "field 'llWechatpay'", LinearLayout.class);
    target.llAlipay = Utils.findRequiredViewAsType(source, R.id.llAlipay, "field 'llAlipay'", LinearLayout.class);
    target.tvMyAccountTitle = Utils.findRequiredViewAsType(source, R.id.tvMyAccountTitle, "field 'tvMyAccountTitle'", TextView.class);
    target.tvRealName = Utils.findRequiredViewAsType(source, R.id.tvRealName, "field 'tvRealName'", TextView.class);
    target.tvBankName = Utils.findRequiredViewAsType(source, R.id.tvBankName, "field 'tvBankName'", TextView.class);
    target.tvBankBranch = Utils.findRequiredViewAsType(source, R.id.tvBankBranch, "field 'tvBankBranch'", TextView.class);
    target.tvBankCardNo = Utils.findRequiredViewAsType(source, R.id.tvBankCardNo, "field 'tvBankCardNo'", TextView.class);
    target.tvWechatNo = Utils.findRequiredViewAsType(source, R.id.tvWechatNo, "field 'tvWechatNo'", TextView.class);
    target.tvAliNo = Utils.findRequiredViewAsType(source, R.id.tvAliNo, "field 'tvAliNo'", TextView.class);
    target.ivCodeImageAli = Utils.findRequiredViewAsType(source, R.id.ivCodeImageAli, "field 'ivCodeImageAli'", ImageView.class);
    target.ivCodeImageWechat = Utils.findRequiredViewAsType(source, R.id.ivCodeImageWechat, "field 'ivCodeImageWechat'", ImageView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tvTime, "field 'tvTime'", TextView.class);
    target.tvOrderSn = Utils.findRequiredViewAsType(source, R.id.tvOrderSn, "field 'tvOrderSn'", TextView.class);
    target.tvTips = Utils.findRequiredViewAsType(source, R.id.tvTips, "field 'tvTips'", TextView.class);
    target.btnPay = Utils.findRequiredViewAsType(source, R.id.btnPay, "field 'btnPay'", TextView.class);
    target.btnCancel = Utils.findRequiredViewAsType(source, R.id.btnCancel, "field 'btnCancel'", TextView.class);
    target.llBottom = Utils.findRequiredViewAsType(source, R.id.llBottom, "field 'llBottom'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CTCOrderDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.view_back = null;
    target.llTitle = null;
    target.tvDirection = null;
    target.tvCountDown = null;
    target.tvAmount = null;
    target.tvPrice = null;
    target.tvMoney = null;
    target.llBank = null;
    target.llWechatpay = null;
    target.llAlipay = null;
    target.tvMyAccountTitle = null;
    target.tvRealName = null;
    target.tvBankName = null;
    target.tvBankBranch = null;
    target.tvBankCardNo = null;
    target.tvWechatNo = null;
    target.tvAliNo = null;
    target.ivCodeImageAli = null;
    target.ivCodeImageWechat = null;
    target.tvTime = null;
    target.tvOrderSn = null;
    target.tvTips = null;
    target.btnPay = null;
    target.btnCancel = null;
    target.llBottom = null;
  }
}
