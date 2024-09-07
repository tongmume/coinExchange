// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.message_detail;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MessageDetailActivity_ViewBinding implements Unbinder {
  private MessageDetailActivity target;

  @UiThread
  public MessageDetailActivity_ViewBinding(MessageDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MessageDetailActivity_ViewBinding(MessageDetailActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.ibDetail = Utils.findRequiredViewAsType(source, R.id.ibDetail, "field 'ibDetail'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.wb = Utils.findRequiredViewAsType(source, R.id.wb, "field 'wb'", WebView.class);
    target.text = Utils.findRequiredViewAsType(source, R.id.text, "field 'text'", TextView.class);
    target.text_time = Utils.findRequiredViewAsType(source, R.id.text_time, "field 'text_time'", TextView.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
    target.ibSahre = Utils.findRequiredViewAsType(source, R.id.ibSahre, "field 'ibSahre'", ImageButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MessageDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.ibDetail = null;
    target.llTitle = null;
    target.wb = null;
    target.text = null;
    target.text_time = null;
    target.view_back = null;
    target.ibSahre = null;
  }
}
