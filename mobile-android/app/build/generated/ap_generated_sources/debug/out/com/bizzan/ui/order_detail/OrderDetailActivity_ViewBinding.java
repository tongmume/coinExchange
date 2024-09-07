// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.order_detail;

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

public class OrderDetailActivity_ViewBinding implements Unbinder {
  private OrderDetailActivity target;

  @UiThread
  public OrderDetailActivity_ViewBinding(OrderDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OrderDetailActivity_ViewBinding(OrderDetailActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.ibDetail = Utils.findRequiredViewAsType(source, R.id.ibDetail, "field 'ibDetail'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.tvOrderSn = Utils.findRequiredViewAsType(source, R.id.tvOrderSn, "field 'tvOrderSn'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tvPrice, "field 'tvPrice'", TextView.class);
    target.tvCount = Utils.findRequiredViewAsType(source, R.id.tvCount, "field 'tvCount'", TextView.class);
    target.tvTotal = Utils.findRequiredViewAsType(source, R.id.tvTotal, "field 'tvTotal'", TextView.class);
    target.tvZhifubao = Utils.findRequiredViewAsType(source, R.id.tvZhifubao, "field 'tvZhifubao'", TextView.class);
    target.tvWeChat = Utils.findRequiredViewAsType(source, R.id.tvWeChat, "field 'tvWeChat'", TextView.class);
    target.tvBankNo = Utils.findRequiredViewAsType(source, R.id.tvBankNo, "field 'tvBankNo'", TextView.class);
    target.tvStatus = Utils.findRequiredViewAsType(source, R.id.tvStatus, "field 'tvStatus'", TextView.class);
    target.tvPayDone = Utils.findRequiredViewAsType(source, R.id.tvPayDone, "field 'tvPayDone'", TextView.class);
    target.tvCancle = Utils.findRequiredViewAsType(source, R.id.tvCancle, "field 'tvCancle'", TextView.class);
    target.tvAppeal = Utils.findRequiredViewAsType(source, R.id.tvAppeal, "field 'tvAppeal'", TextView.class);
    target.llPayInfo = Utils.findRequiredViewAsType(source, R.id.llPayInfo, "field 'llPayInfo'", LinearLayout.class);
    target.llAppeal = Utils.findRequiredViewAsType(source, R.id.llAppeal, "field 'llAppeal'", LinearLayout.class);
    target.tvRelease = Utils.findRequiredViewAsType(source, R.id.tvRelease, "field 'tvRelease'", TextView.class);
    target.llOperate = Utils.findRequiredViewAsType(source, R.id.llOperate, "field 'llOperate'", LinearLayout.class);
    target.ivZhifubaoQR = Utils.findRequiredViewAsType(source, R.id.ivZhifubaoQR, "field 'ivZhifubaoQR'", ImageView.class);
    target.ivWeChatQR = Utils.findRequiredViewAsType(source, R.id.ivWeChatQR, "field 'ivWeChatQR'", ImageView.class);
    target.tvOtherSide = Utils.findRequiredViewAsType(source, R.id.tvOtherSide, "field 'tvOtherSide'", TextView.class);
    target.tvTime = Utils.findRequiredViewAsType(source, R.id.tvTime, "field 'tvTime'", TextView.class);
    target.tvLastTime = Utils.findRequiredViewAsType(source, R.id.tvLastTime, "field 'tvLastTime'", TextView.class);
    target.llLastTime = Utils.findRequiredViewAsType(source, R.id.llLastTime, "field 'llLastTime'", LinearLayout.class);
    target.tvBranch = Utils.findRequiredViewAsType(source, R.id.tvBranch, "field 'tvBranch'", TextView.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
    target.tvBank = Utils.findRequiredViewAsType(source, R.id.tvBank, "field 'tvBank'", TextView.class);
    target.line_zhankai = Utils.findRequiredViewAsType(source, R.id.line_zhankai, "field 'line_zhankai'", LinearLayout.class);
    target.img_zhankai = Utils.findRequiredViewAsType(source, R.id.img_zhankai, "field 'img_zhankai'", ImageView.class);
    target.line_xingming = Utils.findRequiredViewAsType(source, R.id.line_xingming, "field 'line_xingming'", LinearLayout.class);
    target.tvrealName = Utils.findRequiredViewAsType(source, R.id.tvrealName, "field 'tvrealName'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.ibDetail = null;
    target.llTitle = null;
    target.tvOrderSn = null;
    target.tvPrice = null;
    target.tvCount = null;
    target.tvTotal = null;
    target.tvZhifubao = null;
    target.tvWeChat = null;
    target.tvBankNo = null;
    target.tvStatus = null;
    target.tvPayDone = null;
    target.tvCancle = null;
    target.tvAppeal = null;
    target.llPayInfo = null;
    target.llAppeal = null;
    target.tvRelease = null;
    target.llOperate = null;
    target.ivZhifubaoQR = null;
    target.ivWeChatQR = null;
    target.tvOtherSide = null;
    target.tvTime = null;
    target.tvLastTime = null;
    target.llLastTime = null;
    target.tvBranch = null;
    target.view_back = null;
    target.tvBank = null;
    target.line_zhankai = null;
    target.img_zhankai = null;
    target.line_xingming = null;
    target.tvrealName = null;
  }
}
