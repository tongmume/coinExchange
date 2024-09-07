// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.kline;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bizzan.R;
import com.bizzan.customview.CustomViewPager;
import com.bizzan.customview.intercept.WonderfulScrollView;
import com.bizzan.ui.kline.kline.KLineView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class KlineActivity_ViewBinding implements Unbinder {
  private KlineActivity target;

  private View view2131297417;

  private View view2131296571;

  private View view2131297327;

  private View view2131297362;

  private View view2131297247;

  private View view2131296642;

  private View view2131297306;

  @UiThread
  public KlineActivity_ViewBinding(KlineActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public KlineActivity_ViewBinding(final KlineActivity target, View source) {
    this.target = target;

    View view;
    target.tvCurrencyName = Utils.findRequiredViewAsType(source, R.id.tvCurrencyName, "field 'tvCurrencyName'", TextView.class);
    target.llLandText = Utils.findRequiredViewAsType(source, R.id.llLandText, "field 'llLandText'", LinearLayout.class);
    target.mDataText = Utils.findRequiredViewAsType(source, R.id.kDataText, "field 'mDataText'", TextView.class);
    target.mDataOne = Utils.findRequiredViewAsType(source, R.id.kDataOne, "field 'mDataOne'", TextView.class);
    target.kCount = Utils.findRequiredViewAsType(source, R.id.kCount, "field 'kCount'", TextView.class);
    target.kUp = Utils.findRequiredViewAsType(source, R.id.kUp, "field 'kUp'", TextView.class);
    target.kLow = Utils.findRequiredViewAsType(source, R.id.kLow, "field 'kLow'", TextView.class);
    target.tab = Utils.findRequiredViewAsType(source, R.id.tab, "field 'tab'", LinearLayout.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.kRange = Utils.findRequiredViewAsType(source, R.id.kRange, "field 'kRange'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_collect, "field 'mTvCollect' and method 'setListener'");
    target.mTvCollect = Utils.castView(view, R.id.tv_collect, "field 'mTvCollect'", TextView.class);
    view2131297417 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setListener(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ibBack, "field 'ibBack' and method 'setListener'");
    target.ibBack = Utils.castView(view, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    view2131296571 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setListener(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvMore, "field 'tvMore' and method 'setListener'");
    target.tvMore = Utils.castView(view, R.id.tvMore, "field 'tvMore'", TextView.class);
    view2131297327 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setListener(p0);
      }
    });
    target.llAllTab = Utils.findRequiredViewAsType(source, R.id.llAllTab, "field 'llAllTab'", LinearLayout.class);
    target.llVertical = Utils.findRequiredViewAsType(source, R.id.llVertical, "field 'llVertical'", LinearLayout.class);
    target.llState = Utils.findRequiredViewAsType(source, R.id.llState, "field 'llState'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.tvSell, "field 'tvSell' and method 'setListener'");
    target.tvSell = Utils.castView(view, R.id.tvSell, "field 'tvSell'", TextView.class);
    view2131297362 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setListener(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvBuy, "field 'tvBuy' and method 'setListener'");
    target.tvBuy = Utils.castView(view, R.id.tvBuy, "field 'tvBuy'", TextView.class);
    view2131297247 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setListener(p0);
      }
    });
    target.depthPager = Utils.findRequiredViewAsType(source, R.id.vpDepth, "field 'depthPager'", CustomViewPager.class);
    target.depthTab = Utils.findRequiredViewAsType(source, R.id.llDepthTab, "field 'depthTab'", TabLayout.class);
    target.scrollView = Utils.findRequiredViewAsType(source, R.id.scrollView, "field 'scrollView'", WonderfulScrollView.class);
    target.klineView = Utils.findRequiredViewAsType(source, R.id.kline_view, "field 'klineView'", KLineView.class);
    view = Utils.findRequiredView(source, R.id.ivFullScreen, "method 'setListener'");
    view2131296642 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setListener(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvIndex, "method 'setListener'");
    view2131297306 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setListener(p0);
      }
    });

    Context context = source.getContext();
    Resources res = context.getResources();
    target.titles = res.getStringArray(R.array.k_line_tab);
  }

  @Override
  @CallSuper
  public void unbind() {
    KlineActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvCurrencyName = null;
    target.llLandText = null;
    target.mDataText = null;
    target.mDataOne = null;
    target.kCount = null;
    target.kUp = null;
    target.kLow = null;
    target.tab = null;
    target.llTitle = null;
    target.kRange = null;
    target.mTvCollect = null;
    target.ibBack = null;
    target.tvMore = null;
    target.llAllTab = null;
    target.llVertical = null;
    target.llState = null;
    target.tvSell = null;
    target.tvBuy = null;
    target.depthPager = null;
    target.depthTab = null;
    target.scrollView = null;
    target.klineView = null;

    view2131297417.setOnClickListener(null);
    view2131297417 = null;
    view2131296571.setOnClickListener(null);
    view2131296571 = null;
    view2131297327.setOnClickListener(null);
    view2131297327 = null;
    view2131297362.setOnClickListener(null);
    view2131297362 = null;
    view2131297247.setOnClickListener(null);
    view2131297247 = null;
    view2131296642.setOnClickListener(null);
    view2131296642 = null;
    view2131297306.setOnClickListener(null);
    view2131297306 = null;
  }
}
