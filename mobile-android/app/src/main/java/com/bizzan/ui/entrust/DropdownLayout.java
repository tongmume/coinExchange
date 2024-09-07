package com.bizzan.ui.entrust;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bizzan.R;

public class DropdownLayout extends LinearLayout {
    private static final String TAG = "DropdownLayout";
    private int mDropSpeed;
    private boolean isOpen = false;
    private View mDropView;
    private int mDropHeight;

    public DropdownLayout(Context context) {
        this(context, null);
        this.setOrientation(VERTICAL);
    }

    public DropdownLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.setOrientation(VERTICAL);
    }

    public DropdownLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOrientation(VERTICAL);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DropdownLayout);
        mDropSpeed = array.getInteger(R.styleable.DropdownLayout_dropSpeed, 10);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mDropHeight != 0) {
            return;
        }
//        Log.i(TAG, "onMeasure dropHeight:" + widthMeasureSpec+"==="+heightMeasureSpec);
        if (null != this.getChildAt(1) && this.getChildAt(1) instanceof ViewGroup) {
            ViewGroup rootDropView = (ViewGroup) this.getChildAt(1);
            int childCount = rootDropView.getChildCount();
            if (childCount > 0) {
                mDropView = rootDropView.getChildAt(0);
                mDropHeight = mDropView.getMeasuredHeight();
//                Log.i(TAG, "onMeasure dropHeight:" + mDropHeight);
                mDropView.setY(-mDropHeight);
            }
        }
    }

    public void show() {
        if (!isOpen) {
            ObjectAnimator translationY = ObjectAnimator.ofFloat(mDropView, "translationY", -mDropHeight, 0);
            translationY.setDuration(mDropSpeed);
            translationY.start();
            isOpen = true;
        }
    }

    public void hide() {
        if (isOpen) {
            ObjectAnimator translationY = ObjectAnimator.ofFloat(mDropView, "translationY", 0, -mDropHeight);
            translationY.setDuration(mDropSpeed);
            translationY.start();
            isOpen = false;
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setDropSpeed(int speed) {
        this.mDropSpeed = speed;
    }
}