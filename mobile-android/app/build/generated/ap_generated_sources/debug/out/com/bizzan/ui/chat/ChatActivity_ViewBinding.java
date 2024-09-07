// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.chat;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChatActivity_ViewBinding implements Unbinder {
  private ChatActivity target;

  @UiThread
  public ChatActivity_ViewBinding(ChatActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChatActivity_ViewBinding(ChatActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.tvOrder = Utils.findRequiredViewAsType(source, R.id.tvOrder, "field 'tvOrder'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.rvMessage = Utils.findRequiredViewAsType(source, R.id.rvMessage, "field 'rvMessage'", RecyclerView.class);
    target.etContent = Utils.findRequiredViewAsType(source, R.id.etContent, "field 'etContent'", EditText.class);
    target.tvSend = Utils.findRequiredViewAsType(source, R.id.tvSend, "field 'tvSend'", TextView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refreshLayout, "field 'refreshLayout'", SwipeRefreshLayout.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    ChatActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.tvOrder = null;
    target.llTitle = null;
    target.rvMessage = null;
    target.etContent = null;
    target.tvSend = null;
    target.tvTitle = null;
    target.refreshLayout = null;
    target.view_back = null;
  }
}
