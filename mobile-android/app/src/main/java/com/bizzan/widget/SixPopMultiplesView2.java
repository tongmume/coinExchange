package com.bizzan.widget;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import com.bizzan.R;
import com.bizzan.adapter.AddMultiples;


/**
 * Created by Haocxx
 * on 2019/8/12
 */
public class SixPopMultiplesView2 extends PopupWindow {

    private final AddMultiples adapter;
    private View view;
    private Button bu_close2, bu_true2;
    private RecyclerView rv_multiple;
    private ImageView img_into;
    private List<String> addlist = new ArrayList<>();
    private int currentPosition = -1;

    //获取当前倍数
    public String getnumber() {
        if (currentPosition != -1) {
            return addlist.get(currentPosition);
        } else {
            return "";
        }
    }

    public SixPopMultiplesView2(final Activity activity, String progress,String leverage, View.OnClickListener itemsOnClick) {
        this.view = LayoutInflater.from(activity).inflate(R.layout.pop_six_multiples2, null);
        bu_close2 = view.findViewById(R.id.bu_close2);
        bu_true2 = view.findViewById(R.id.bu_true2);
        rv_multiple = view.findViewById(R.id.rv_multiple);
        img_into = view.findViewById(R.id.img_into);

        add(leverage);
        ifprogress(progress);

        LinearLayoutManager manager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        rv_multiple.setLayoutManager(manager);
        adapter = new AddMultiples(R.layout.adapter_addmultiples, addlist);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //这里赋值
                currentPosition = position;
                //每点击一次item就刷新适配器
                adapter.notifyDataSetChanged();
            }
        });
        adapter.setItemSelectedCallBack(new AddMultiples.ItemSelectedCallBack() {
            @Override
            public void convert(BaseViewHolder holder, int position) {
                //初始化组件
                TextView tv = holder.getView(R.id.tv_add);
                //判断传入的position是否和当前一致
                if (position == currentPosition) {
                    tv.setBackgroundResource(R.drawable.shape_six_multiples_item_color_orange);
                } else {
                    tv.setBackgroundResource(R.color.transparent);
                }
            }
        });
        adapter.bindToRecyclerView(rv_multiple);
        rv_multiple.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //用来标记是否正在向最后一个滑动
            boolean isSlidingToLast = false;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //设置什么布局管理器,就获取什么的布局管理器
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当停止滑动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition ,角标值
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    //所有条目,数量值
                    int totalItemCount = manager.getItemCount();

                    // 判断是否滚动到底部，并且是向右滚动
                    System.out.println(lastVisibleItem);
                    System.out.println((totalItemCount - 1));
                    System.out.println(isSlidingToLast);
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        //加载更多功能的代码
//                       img_into.setVisibility(View.GONE);
                    }else {
//                        img_into.setVisibility(View.VISIBLE);
                    }
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向  
                //dx>0:向右滑动,dx<0:向左滑动
                //dy>0:向下滑动,dy<0:向上滑动
                if (dx > 0) {
                    isSlidingToLast = true;
                } else {
                    isSlidingToLast = false;
                }
            }
        });

        // 设置按钮监听
        bu_true2.setOnClickListener(itemsOnClick);
        bu_close2.setOnClickListener(itemsOnClick);

        // 设置外部可点击
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = (float) 1.0; //0.0-1.0
                activity.getWindow().setAttributes(lp);
            }
        });

        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = (float) 0.6; //0.0-1.0
        activity.getWindow().setAttributes(lp);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 设置弹出窗体的背景
        this.setBackgroundDrawable(Drawable.createFromPath("#00000000"));

        this.setAnimationStyle(R.style.bottomDialog);
    }

    private void ifprogress(String progress) {
        for (int i = 0; i < addlist.size(); i++) {
            if (progress.equals(addlist.get(i))) {
                currentPosition = i;
                return;
            } else {
                currentPosition = 0;
            }
        }
    }

    private void add(String leverage) {
        String[] split = leverage.split(",");
        for (int i = 0; i < split.length; i++) {
            addlist.add(split[i]);
        }
    }

}
