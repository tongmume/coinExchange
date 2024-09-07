package com.bizzan.customview.intercept;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2018/1/7.
 */

public class WonderfulViewPager extends ViewPager {

    private boolean isNotIntercept = true;

    public WonderfulViewPager(Context context) {
        super(context);
    }

    public WonderfulViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isNotIntercept && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isNotIntercept && super.onTouchEvent(ev);
    }

    public void setNotIntercept(boolean notIntercept) {
        isNotIntercept = notIntercept;
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);
    }
}
