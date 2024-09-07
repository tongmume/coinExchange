// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bizzan.R;
import com.bizzan.customview.intercept.WonderfulScrollView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OneFragment_ViewBinding implements Unbinder {
  private OneFragment target;

  private View view2131296335;

  private View view2131296337;

  private View view2131296339;

  private View view2131296338;

  private View view2131296336;

  @UiThread
  public OneFragment_ViewBinding(final OneFragment target, View source) {
    this.target = target;

    View view;
    target.ibMessage = Utils.findRequiredViewAsType(source, R.id.ibMessage, "field 'ibMessage'", ImageButton.class);
    target.banner = Utils.findRequiredViewAsType(source, R.id.banner, "field 'banner'", Banner.class);
    target.line_zichan = Utils.findRequiredViewAsType(source, R.id.line_zichan, "field 'line_zichan'", LinearLayout.class);
    target.line_ctc = Utils.findRequiredViewAsType(source, R.id.line_ctc, "field 'line_ctc'", LinearLayout.class);
    target.marqueeView = Utils.findRequiredViewAsType(source, R.id.marqueeView, "field 'marqueeView'", MarqueeView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", ViewPager.class);
    target.main_linear = Utils.findRequiredViewAsType(source, R.id.main_linear, "field 'main_linear'", LinearLayout.class);
    target.line_help = Utils.findRequiredViewAsType(source, R.id.line_help, "field 'line_help'", LinearLayout.class);
    target.text_gengduo = Utils.findRequiredViewAsType(source, R.id.text_gengduo, "field 'text_gengduo'", TextView.class);
    target.line_gonggao = Utils.findRequiredViewAsType(source, R.id.line_gonggao, "field 'line_gonggao'", LinearLayout.class);
    target.text_login = Utils.findRequiredViewAsType(source, R.id.text_login, "field 'text_login'", TextView.class);
    target.text_loginbtn = Utils.findRequiredViewAsType(source, R.id.text_loginbtn, "field 'text_loginbtn'", TextView.class);
    target.line_lab = Utils.findRequiredView(source, R.id.line_lab, "field 'line_lab'");
    target.ivService = Utils.findRequiredView(source, R.id.ibService, "field 'ivService'");
    target.text_totalassets = Utils.findRequiredViewAsType(source, R.id.text_totalassets, "field 'text_totalassets'", TextView.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.mRecyclerView, "field 'mRecyclerView'", RecyclerView.class);
    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refreshLayout, "field 'refreshLayout'", SmartRefreshLayout.class);
    target.scrollView = Utils.findRequiredViewAsType(source, R.id.scrollView, "field 'scrollView'", WonderfulScrollView.class);
    target.ivchatTip = Utils.findRequiredViewAsType(source, R.id.ivchatTip, "field 'ivchatTip'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.btnCharge, "method 'onBtnClick'");
    view2131296335 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBtnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnInvite, "method 'onBtnClick'");
    view2131296337 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBtnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnMining, "method 'onBtnClick'");
    view2131296339 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBtnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnKefu, "method 'onBtnClick'");
    view2131296338 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBtnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnContract, "method 'onBtnClick'");
    view2131296336 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBtnClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    OneFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibMessage = null;
    target.banner = null;
    target.line_zichan = null;
    target.line_ctc = null;
    target.marqueeView = null;
    target.llTitle = null;
    target.viewPager = null;
    target.main_linear = null;
    target.line_help = null;
    target.text_gengduo = null;
    target.line_gonggao = null;
    target.text_login = null;
    target.text_loginbtn = null;
    target.line_lab = null;
    target.ivService = null;
    target.text_totalassets = null;
    target.mRecyclerView = null;
    target.refreshLayout = null;
    target.scrollView = null;
    target.ivchatTip = null;

    view2131296335.setOnClickListener(null);
    view2131296335 = null;
    view2131296337.setOnClickListener(null);
    view2131296337 = null;
    view2131296339.setOnClickListener(null);
    view2131296339 = null;
    view2131296338.setOnClickListener(null);
    view2131296338 = null;
    view2131296336.setOnClickListener(null);
    view2131296336 = null;
  }
}
