// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.setting;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import com.bizzan.base.MyListView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HelpActivity_ViewBinding implements Unbinder {
  private HelpActivity target;

  @UiThread
  public HelpActivity_ViewBinding(HelpActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HelpActivity_ViewBinding(HelpActivity target, View source) {
    this.target = target;

    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.listview_xinshou = Utils.findRequiredViewAsType(source, R.id.listview_xinshou, "field 'listview_xinshou'", MyListView.class);
    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.listview_changjian = Utils.findRequiredViewAsType(source, R.id.listview_changjian, "field 'listview_changjian'", MyListView.class);
    target.listview_jiaoyi = Utils.findRequiredViewAsType(source, R.id.listview_jiaoyi, "field 'listview_jiaoyi'", MyListView.class);
    target.listview_bizhong = Utils.findRequiredViewAsType(source, R.id.listview_bizhong, "field 'listview_bizhong'", MyListView.class);
    target.listview_hangqing = Utils.findRequiredViewAsType(source, R.id.listview_hangqing, "field 'listview_hangqing'", MyListView.class);
    target.listview_tiaokuan = Utils.findRequiredViewAsType(source, R.id.listview_tiaokuan, "field 'listview_tiaokuan'", MyListView.class);
    target.listview_qita = Utils.findRequiredViewAsType(source, R.id.listview_qita, "field 'listview_qita'", MyListView.class);
    target.text_gengduo1 = Utils.findRequiredViewAsType(source, R.id.text_gengduo1, "field 'text_gengduo1'", TextView.class);
    target.text_gengduo2 = Utils.findRequiredViewAsType(source, R.id.text_gengduo2, "field 'text_gengduo2'", TextView.class);
    target.text_gengduo3 = Utils.findRequiredViewAsType(source, R.id.text_gengduo3, "field 'text_gengduo3'", TextView.class);
    target.text_gengduo4 = Utils.findRequiredViewAsType(source, R.id.text_gengduo4, "field 'text_gengduo4'", TextView.class);
    target.text_gengduo5 = Utils.findRequiredViewAsType(source, R.id.text_gengduo5, "field 'text_gengduo5'", TextView.class);
    target.text_gengduo6 = Utils.findRequiredViewAsType(source, R.id.text_gengduo6, "field 'text_gengduo6'", TextView.class);
    target.text_gengduo7 = Utils.findRequiredViewAsType(source, R.id.text_gengduo7, "field 'text_gengduo7'", TextView.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    HelpActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llTitle = null;
    target.listview_xinshou = null;
    target.ibBack = null;
    target.listview_changjian = null;
    target.listview_jiaoyi = null;
    target.listview_bizhong = null;
    target.listview_hangqing = null;
    target.listview_tiaokuan = null;
    target.listview_qita = null;
    target.text_gengduo1 = null;
    target.text_gengduo2 = null;
    target.text_gengduo3 = null;
    target.text_gengduo4 = null;
    target.text_gengduo5 = null;
    target.text_gengduo6 = null;
    target.text_gengduo7 = null;
    target.view_back = null;
  }
}
