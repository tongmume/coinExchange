// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.wallet;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OverturnActivity_ViewBinding implements Unbinder {
  private OverturnActivity target;

  private View view2131296571;

  private View view2131296844;

  private View view2131297401;

  private View view2131297010;

  private View view2131297002;

  private View view2131297003;

  private View view2131297342;

  @UiThread
  public OverturnActivity_ViewBinding(OverturnActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OverturnActivity_ViewBinding(final OverturnActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ibBack, "field 'ibBack' and method 'onViewClicked'");
    target.ibBack = Utils.castView(view, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    view2131296571 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.viewBack = Utils.findRequiredView(source, R.id.view_back, "field 'viewBack'");
    target.ibDetail = Utils.findRequiredViewAsType(source, R.id.ibDetail, "field 'ibDetail'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.tvc = Utils.findRequiredViewAsType(source, R.id.tvc, "field 'tvc'", TextView.class);
    target.tvd = Utils.findRequiredViewAsType(source, R.id.tvd, "field 'tvd'", TextView.class);
    target.viewLine = Utils.findRequiredView(source, R.id.view_line, "field 'viewLine'");
    view = Utils.findRequiredView(source, R.id.ll_switch, "field 'llSwitch' and method 'onViewClicked'");
    target.llSwitch = Utils.castView(view, R.id.ll_switch, "field 'llSwitch'", LinearLayout.class);
    view2131296844 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvCoins = Utils.findRequiredViewAsType(source, R.id.tv_coins, "field 'tvCoins'", TextView.class);
    target.ivArrow = Utils.findRequiredViewAsType(source, R.id.iv_arrow, "field 'ivArrow'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.tv_all, "field 'tvAll' and method 'onViewClicked'");
    target.tvAll = Utils.castView(view, R.id.tv_all, "field 'tvAll'", TextView.class);
    view2131297401 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvCoin = Utils.findRequiredViewAsType(source, R.id.tv_coin, "field 'tvCoin'", TextView.class);
    target.editNumber = Utils.findRequiredViewAsType(source, R.id.edit_number, "field 'editNumber'", EditText.class);
    target.tvTop1 = Utils.findRequiredViewAsType(source, R.id.tv_top1, "field 'tvTop1'", TextView.class);
    target.tvTop2 = Utils.findRequiredViewAsType(source, R.id.tv_top2, "field 'tvTop2'", TextView.class);
    target.tvBottom1 = Utils.findRequiredViewAsType(source, R.id.tv_bottom1, "field 'tvBottom1'", TextView.class);
    target.tvBottom2 = Utils.findRequiredViewAsType(source, R.id.tv_bottom2, "field 'tvBottom2'", TextView.class);
    target.ivTop = Utils.findRequiredViewAsType(source, R.id.iv_top, "field 'ivTop'", ImageView.class);
    target.ivBottom = Utils.findRequiredViewAsType(source, R.id.iv_bottom, "field 'ivBottom'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.rl_top, "field 'rlTop' and method 'onViewClicked'");
    target.rlTop = Utils.castView(view, R.id.rl_top, "field 'rlTop'", RelativeLayout.class);
    view2131297010 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_boottom, "field 'rlBoottom' and method 'onViewClicked'");
    target.rlBoottom = Utils.castView(view, R.id.rl_boottom, "field 'rlBoottom'", RelativeLayout.class);
    view2131297002 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_coin, "field 'rlCoin' and method 'onViewClicked'");
    target.rlCoin = Utils.castView(view, R.id.rl_coin, "field 'rlCoin'", RelativeLayout.class);
    view2131297003 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvCoinAccount = Utils.findRequiredViewAsType(source, R.id.tv_coin_account, "field 'tvCoinAccount'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tvOverturn, "method 'onViewClicked'");
    view2131297342 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    OverturnActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.viewBack = null;
    target.ibDetail = null;
    target.llTitle = null;
    target.tvc = null;
    target.tvd = null;
    target.viewLine = null;
    target.llSwitch = null;
    target.tvCoins = null;
    target.ivArrow = null;
    target.tvAll = null;
    target.tvCoin = null;
    target.editNumber = null;
    target.tvTop1 = null;
    target.tvTop2 = null;
    target.tvBottom1 = null;
    target.tvBottom2 = null;
    target.ivTop = null;
    target.ivBottom = null;
    target.rlTop = null;
    target.rlBoottom = null;
    target.rlCoin = null;
    target.tvCoinAccount = null;

    view2131296571.setOnClickListener(null);
    view2131296571 = null;
    view2131296844.setOnClickListener(null);
    view2131296844 = null;
    view2131297401.setOnClickListener(null);
    view2131297401 = null;
    view2131297010.setOnClickListener(null);
    view2131297010 = null;
    view2131297002.setOnClickListener(null);
    view2131297002 = null;
    view2131297003.setOnClickListener(null);
    view2131297003 = null;
    view2131297342.setOnClickListener(null);
    view2131297342 = null;
  }
}
