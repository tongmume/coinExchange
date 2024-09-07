// Generated code from Butter Knife. Do not modify!
package com.bizzan.ui.chatlist;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.bizzan.R;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChatListActivity_ViewBinding implements Unbinder {
  private ChatListActivity target;

  @UiThread
  public ChatListActivity_ViewBinding(ChatListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChatListActivity_ViewBinding(ChatListActivity target, View source) {
    this.target = target;

    target.ibBack = Utils.findRequiredViewAsType(source, R.id.ibBack, "field 'ibBack'", ImageButton.class);
    target.ibRegist = Utils.findRequiredViewAsType(source, R.id.ibRegist, "field 'ibRegist'", TextView.class);
    target.llTitle = Utils.findRequiredViewAsType(source, R.id.llTitle, "field 'llTitle'", LinearLayout.class);
    target.rvChat = Utils.findRequiredViewAsType(source, R.id.rvChat, "field 'rvChat'", SwipeMenuRecyclerView.class);
    target.rlEmpty = Utils.findRequiredViewAsType(source, R.id.rlEmpty, "field 'rlEmpty'", RelativeLayout.class);
    target.view_back = Utils.findRequiredView(source, R.id.view_back, "field 'view_back'");
  }

  @Override
  @CallSuper
  public void unbind() {
    ChatListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibBack = null;
    target.ibRegist = null;
    target.llTitle = null;
    target.rvChat = null;
    target.rlEmpty = null;
    target.view_back = null;
  }
}
