package com.bizzan.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Wonderful on 2017/7/29.
 * 签名画布
 */

public class WriteView extends View {
    private Paint mPaint;// 画笔
    private Path mPath;// 绘制路径
    private Canvas mCanvas;// 画面Canvas
    private int mLastX;// 手指移动后X位置
    private int mLastY;// 手指移动后Y位置
    private Bitmap mBitmap;// 图片

    int width;
    int height;

    public WriteView(Context context) {
        this(context, null, 0);
    }

    public WriteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViewsParams();
    }

    public WriteView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 初始化相关数据及参数
     *
     * @description：
     */
    private void initViewsParams() {
        mPath = new Path();
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();// 获取到宽度
        height = getMeasuredHeight();// 获取到高度
        initPaint();
    }

    private void initPaint() {
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);// 根据参数创建新位图
        mCanvas = new Canvas(mBitmap);
//        //重设设置背景
        int color = ((ColorDrawable) this.getBackground()).getColor();
        mCanvas.drawColor(color);
//        mPaint.setColor(color);
        mPaint.setAntiAlias(true);// 设置抗紧锯齿
        mPaint.setDither(true);// 防抖动
        // 设置画笔参数
        mPaint.setColor(Color.BLACK);// 设置颜色

        mPaint.setStrokeJoin(Paint.Join.ROUND);// 设置图形结合处的样子
        mPaint.setStrokeCap(Paint.Cap.ROUND);// 设置笔刷的图形样式，如圆形样式
        mPaint.setStyle(Paint.Style.STROKE);// 设置画笔样式为空心
        mPaint.setStrokeWidth(10);// 设置画笔粗细
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();//当前x
        int y = (int) event.getY();//当前y
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                mPath.moveTo(mLastX, mLastY);//移动Path
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = Math.abs(x - mLastX);
                int dy = Math.abs(y - mLastY);
                if (dx > 3 || dy > 3) {
                    mPath.lineTo(x, y);
                }
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        invalidate();
        return true;//这里要返回true才可以哦·····
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas.drawPath(mPath, mPaint);
        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
//        mCanvas.drawPath(mPath, mPaint);
//        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    public void resetMBitmap() {
        initViewsParams();
        initPaint();
        invalidate();
    }

    public Bitmap getmBitmap() {
        return mBitmap;
    }
}