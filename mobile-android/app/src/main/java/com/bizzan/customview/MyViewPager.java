package com.bizzan.customview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 1.屏蔽切换的时候需要经过中间页
 * 2.屏蔽ViewPager的滑动
 * 3.预加载问题
 */
public class MyViewPager extends ViewPager {

    /**
     * 是否可以滑动
     */
    private boolean isCanScroll = false;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 解决切换需要经过中间页
     */
    @Override
    public void setCurrentItem(int item) {
        //super.setCurrentItem(item);源码
        super.setCurrentItem(item,false);//false表示切换的时候,不经过两个页面的中间页
    }

    /**
     * 让ViewPager不能左右滑动
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(isCanScroll){
            return super.onTouchEvent(ev);
        }else{
            return false;
        }
    }
    int lastX = -1;
    int lastY = -1;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getRawX();
        int y = (int) ev.getRawY();
        int dealtX = 0;
        int dealtY = 0;

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dealtX = 0;
                dealtY = 0;
                // 保证子View能够接收到Action_move事件
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                dealtX += Math.abs(x - lastX);
                dealtY += Math.abs(y - lastY);

                // 这里是否拦截的判断依据是左右滑动，读者可根据自己的逻辑进行是否拦截
                if (dealtX >= dealtY) { // 左右滑动请求父 View 不要拦截
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 暴露出去的方法,屏蔽ViewPager的滑动,默认不可滑动
     * @param isCanScroll 为true可以左右滑动,为false不可滑动
     */
    public void setIsCanScroll(boolean isCanScroll){
        this.isCanScroll = isCanScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(isCanScroll){
            return super.onInterceptTouchEvent(ev);
        }else{
            return false;
        }
    }

    /* 设置预加载页数的源码,默认为1,当设置的数大于1是有效的,小于1就无效了
       1)如果想预加载多页(默认预加载一页),可以使用setOffscreenPageLimit(int limit),意思为幕后页的限制,默认为1
       2)当你不想要预加载的时候,那是不是只需要将这个值设为0呢?答案是不行的,
      解决办法:复制ViewPager源码将DEFAULT_OFFSCREEN_PAGES这个值改为0就可以了
    public void setOffscreenPageLimit(int limit) {
        if (limit < DEFAULT_OFFSCREEN_PAGES) {
            limit = DEFAULT_OFFSCREEN_PAGES;//可见当设置的值小于1时无效
        }
        if (limit != mOffscreenPageLimit) {
            mOffscreenPageLimit = limit;
            populate();
        }
    }
     */
}