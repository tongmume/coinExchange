// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import com.xw.repo.BubbleSeekBar;
import java.lang.IllegalStateException;
import java.lang.Override;
import org.angmarch.views.NiceSpinner;

public class ThreeTextFragment_ViewBinding implements Unbinder {
  private ThreeTextFragment target;

  @UiThread
  public ThreeTextFragment_ViewBinding(ThreeTextFragment target, View source) {
    this.target = target;

    target.ibOpen = Utils.findRequiredViewAsType(source, R.id.ibOpen, "field 'ibOpen'", ImageButton.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
    target.text_title_price = Utils.findRequiredViewAsType(source, R.id.text_title_price, "field 'text_title_price'", TextView.class);
    target.text_title_amount = Utils.findRequiredViewAsType(source, R.id.text_title_amount, "field 'text_title_amount'", TextView.class);
    target.ibMessage = Utils.findRequiredViewAsType(source, R.id.ibMessage, "field 'ibMessage'", ImageView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.mRadioGroup = Utils.findRequiredViewAsType(source, R.id.mRadioGroup, "field 'mRadioGroup'", RadioGroup.class);
    target.mNiceSpinner = Utils.findRequiredViewAsType(source, R.id.nice_spinner, "field 'mNiceSpinner'", NiceSpinner.class);
    target.mToAllLayout = Utils.findRequiredViewAsType(source, R.id.text_to_all, "field 'mToAllLayout'", RelativeLayout.class);
    target.mOneRecycler = Utils.findRequiredViewAsType(source, R.id.recyclerOne, "field 'mOneRecycler'", RecyclerView.class);
    target.mTwoRecycler = Utils.findRequiredViewAsType(source, R.id.recyclerTwo, "field 'mTwoRecycler'", RecyclerView.class);
    target.mThreeRecycler = Utils.findRequiredViewAsType(source, R.id.recyclerThree, "field 'mThreeRecycler'", RecyclerView.class);
    target.mTvThree = Utils.findRequiredViewAsType(source, R.id.mTvThree, "field 'mTvThree'", TextView.class);
    target.mOneLayout = Utils.findRequiredViewAsType(source, R.id.mOneLayout, "field 'mOneLayout'", LinearLayout.class);
    target.mTwoLayout = Utils.findRequiredViewAsType(source, R.id.mTwoLayout, "field 'mTwoLayout'", LinearLayout.class);
    target.mOneShi = Utils.findRequiredViewAsType(source, R.id.mOneShi, "field 'mOneShi'", TextView.class);
    target.mOneXian = Utils.findRequiredViewAsType(source, R.id.mOneXian, "field 'mOneXian'", LinearLayout.class);
    target.mTwoShi = Utils.findRequiredViewAsType(source, R.id.mTwoShi, "field 'mTwoShi'", TextView.class);
    target.mTwoXian = Utils.findRequiredViewAsType(source, R.id.mTwoXian, "field 'mTwoXian'", LinearLayout.class);
    target.mOnePriceEdit = Utils.findRequiredViewAsType(source, R.id.mOnePrice, "field 'mOnePriceEdit'", EditText.class);
    target.mOneAdd = Utils.findRequiredViewAsType(source, R.id.mOneAdd, "field 'mOneAdd'", TextView.class);
    target.mOneSub = Utils.findRequiredViewAsType(source, R.id.mOneSum, "field 'mOneSub'", TextView.class);
    target.mTwoPriceEdit = Utils.findRequiredViewAsType(source, R.id.mTwoPrice, "field 'mTwoPriceEdit'", EditText.class);
    target.mTwoAdd = Utils.findRequiredViewAsType(source, R.id.mTwoAdd, "field 'mTwoAdd'", TextView.class);
    target.mTwoSub = Utils.findRequiredViewAsType(source, R.id.mTwoSub, "field 'mTwoSub'", TextView.class);
    target.mPanJia = Utils.findRequiredViewAsType(source, R.id.mTvPanJia, "field 'mPanJia'", TextView.class);
    target.mPanMoney = Utils.findRequiredViewAsType(source, R.id.mTvMoney, "field 'mPanMoney'", TextView.class);
    target.mOneYuE = Utils.findRequiredViewAsType(source, R.id.mOneYuE, "field 'mOneYuE'", TextView.class);
    target.mTwoYuE = Utils.findRequiredViewAsType(source, R.id.mTwoYuE, "field 'mTwoYuE'", TextView.class);
    target.mOneBuy = Utils.findRequiredViewAsType(source, R.id.mTvOneBuy, "field 'mOneBuy'", TextView.class);
    target.mOneTcpEdit = Utils.findRequiredViewAsType(source, R.id.mOneTCP, "field 'mOneTcpEdit'", EditText.class);
    target.mTwoTcpEdit = Utils.findRequiredViewAsType(source, R.id.mTwoTCP, "field 'mTwoTcpEdit'", EditText.class);
    target.mTwoBuy = Utils.findRequiredViewAsType(source, R.id.mTvTwoBuy, "field 'mTwoBuy'", TextView.class);
    target.btnBuy = Utils.findRequiredViewAsType(source, R.id.btnOneBuy, "field 'btnBuy'", Button.class);
    target.btnSale = Utils.findRequiredViewAsType(source, R.id.btnTwoPost, "field 'btnSale'", Button.class);
    target.mOneDeal = Utils.findRequiredViewAsType(source, R.id.mOneDeal, "field 'mOneDeal'", TextView.class);
    target.mTwoDeal = Utils.findRequiredViewAsType(source, R.id.mTwoDeal, "field 'mTwoDeal'", TextView.class);
    target.btnLogin = Utils.findRequiredViewAsType(source, R.id.btn_toLogin, "field 'btnLogin'", TextView.class);
    target.mOneTextType = Utils.findRequiredViewAsType(source, R.id.mOneTextType, "field 'mOneTextType'", TextView.class);
    target.mTwoTextType = Utils.findRequiredViewAsType(source, R.id.mTwoTextType, "field 'mTwoTextType'", TextView.class);
    target.mOneJiaoYi = Utils.findRequiredViewAsType(source, R.id.mOneJiaoYi, "field 'mOneJiaoYi'", LinearLayout.class);
    target.mTwoJiaoYi = Utils.findRequiredViewAsType(source, R.id.mTwoJiaoYi, "field 'mTwoJiaoYi'", LinearLayout.class);
    target.mOneSeekBar = Utils.findRequiredViewAsType(source, R.id.mOneSeekBar, "field 'mOneSeekBar'", BubbleSeekBar.class);
    target.mTwoSeekBar = Utils.findRequiredViewAsType(source, R.id.mTwoSeekBar, "field 'mTwoSeekBar'", BubbleSeekBar.class);
    target.ivCollect = Utils.findRequiredViewAsType(source, R.id.ivCollect, "field 'ivCollect'", ImageView.class);
    target.text_one_kaishi = Utils.findRequiredViewAsType(source, R.id.text_one_kaishi, "field 'text_one_kaishi'", TextView.class);
    target.text_one_jieshu = Utils.findRequiredViewAsType(source, R.id.text_one_jieshu, "field 'text_one_jieshu'", TextView.class);
    target.text_two_kaishi = Utils.findRequiredViewAsType(source, R.id.text_two_kaishi, "field 'text_two_kaishi'", TextView.class);
    target.text_two_jieshu = Utils.findRequiredViewAsType(source, R.id.text_two_jieshu, "field 'text_two_jieshu'", TextView.class);
    target.mTabOne = Utils.findRequiredViewAsType(source, R.id.mTabOne, "field 'mTabOne'", RadioButton.class);
    target.mTabTwo = Utils.findRequiredViewAsType(source, R.id.mTabTwo, "field 'mTabTwo'", RadioButton.class);
    target.llCurrentTrust = Utils.findRequiredViewAsType(source, R.id.ll_current_trust, "field 'llCurrentTrust'", LinearLayout.class);
    target.llHistoryTrust = Utils.findRequiredViewAsType(source, R.id.ll_history_trust, "field 'llHistoryTrust'", LinearLayout.class);
    target.tvCurrentTrust = Utils.findRequiredViewAsType(source, R.id.tv_current_trust, "field 'tvCurrentTrust'", TextView.class);
    target.tvHistoryTrust = Utils.findRequiredViewAsType(source, R.id.tv_history_trust, "field 'tvHistoryTrust'", TextView.class);
    target.currentTrustUnderline = Utils.findRequiredView(source, R.id.current_trust_underline, "field 'currentTrustUnderline'");
    target.historyTrustUnderline = Utils.findRequiredView(source, R.id.history_trust_underline, "field 'historyTrustUnderline'");
    target.iv_check_box_one = Utils.findRequiredViewAsType(source, R.id.iv_check_box_one, "field 'iv_check_box_one'", ImageView.class);
    target.iv_check_box_two = Utils.findRequiredViewAsType(source, R.id.iv_check_box_two, "field 'iv_check_box_two'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ThreeTextFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibOpen = null;
    target.tvTitle = null;
    target.text_title_price = null;
    target.text_title_amount = null;
    target.ibMessage = null;
    target.llTitle = null;
    target.mRadioGroup = null;
    target.mNiceSpinner = null;
    target.mToAllLayout = null;
    target.mOneRecycler = null;
    target.mTwoRecycler = null;
    target.mThreeRecycler = null;
    target.mTvThree = null;
    target.mOneLayout = null;
    target.mTwoLayout = null;
    target.mOneShi = null;
    target.mOneXian = null;
    target.mTwoShi = null;
    target.mTwoXian = null;
    target.mOnePriceEdit = null;
    target.mOneAdd = null;
    target.mOneSub = null;
    target.mTwoPriceEdit = null;
    target.mTwoAdd = null;
    target.mTwoSub = null;
    target.mPanJia = null;
    target.mPanMoney = null;
    target.mOneYuE = null;
    target.mTwoYuE = null;
    target.mOneBuy = null;
    target.mOneTcpEdit = null;
    target.mTwoTcpEdit = null;
    target.mTwoBuy = null;
    target.btnBuy = null;
    target.btnSale = null;
    target.mOneDeal = null;
    target.mTwoDeal = null;
    target.btnLogin = null;
    target.mOneTextType = null;
    target.mTwoTextType = null;
    target.mOneJiaoYi = null;
    target.mTwoJiaoYi = null;
    target.mOneSeekBar = null;
    target.mTwoSeekBar = null;
    target.ivCollect = null;
    target.text_one_kaishi = null;
    target.text_one_jieshu = null;
    target.text_two_kaishi = null;
    target.text_two_jieshu = null;
    target.mTabOne = null;
    target.mTabTwo = null;
    target.llCurrentTrust = null;
    target.llHistoryTrust = null;
    target.tvCurrentTrust = null;
    target.tvHistoryTrust = null;
    target.currentTrustUnderline = null;
    target.historyTrustUnderline = null;
    target.iv_check_box_one = null;
    target.iv_check_box_two = null;
  }
}
