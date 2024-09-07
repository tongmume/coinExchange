// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.seller;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.angmarch.views.NiceSpinner;

public class SellerApplyCommitActivity_ViewBinding implements Unbinder {
  private SellerApplyCommitActivity target;

  @UiThread
  public SellerApplyCommitActivity_ViewBinding(SellerApplyCommitActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SellerApplyCommitActivity_ViewBinding(SellerApplyCommitActivity target, View source) {
    this.target = target;

    target.tvCommit = Utils.findRequiredViewAsType(source, R.id.tv_commit, "field 'tvCommit'", TextView.class);
    target.etSellerPhone = Utils.findRequiredViewAsType(source, R.id.et_seller_phone, "field 'etSellerPhone'", EditText.class);
    target.etSellerWechat = Utils.findRequiredViewAsType(source, R.id.et_seller_wechat, "field 'etSellerWechat'", EditText.class);
    target.etSellerQq = Utils.findRequiredViewAsType(source, R.id.et_seller_qq, "field 'etSellerQq'", EditText.class);
    target.tvSellerCoinType = Utils.findRequiredViewAsType(source, R.id.tv_seller_coin_type, "field 'tvSellerCoinType'", TextView.class);
    target.tvSellerCoinAmount = Utils.findRequiredViewAsType(source, R.id.tv_seller_coin_amount, "field 'tvSellerCoinAmount'", TextView.class);
    target.tvSellerAssetImage = Utils.findRequiredViewAsType(source, R.id.tv_seller_asset_image, "field 'tvSellerAssetImage'", TextView.class);
    target.tvSellerExchangeImage = Utils.findRequiredViewAsType(source, R.id.tv_seller_exchange_image, "field 'tvSellerExchangeImage'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.sp_coin_type = Utils.findRequiredViewAsType(source, R.id.sp_coin_type, "field 'sp_coin_type'", NiceSpinner.class);
    target.iv_back = Utils.findRequiredViewAsType(source, R.id.iv_back, "field 'iv_back'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SellerApplyCommitActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvCommit = null;
    target.etSellerPhone = null;
    target.etSellerWechat = null;
    target.etSellerQq = null;
    target.tvSellerCoinType = null;
    target.tvSellerCoinAmount = null;
    target.tvSellerAssetImage = null;
    target.tvSellerExchangeImage = null;
    target.llTitle = null;
    target.sp_coin_type = null;
    target.iv_back = null;
  }
}
