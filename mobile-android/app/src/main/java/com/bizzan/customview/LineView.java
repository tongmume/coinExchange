package com.bizzan.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/11/22.
 */

public class LineView extends AppCompatImageView {

    Paint paint = new Paint();


    public LineView(Context context) {
        super(context);
    }

    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int mWidth = getMeasuredWidth();
        int mHeight = getMeasuredHeight();

        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#66EEEEEE"));
        paint.setStrokeWidth(0f);//设置线宽
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(new Rect(0, 0, mWidth, (int) (0.08 * mHeight)), paint);
        canvas.drawRect(new Rect(0, (int) (0.08 * mHeight), (int) (0.1 * mWidth), mHeight), paint);
        canvas.drawRect(new Rect((int) (0.1 * mWidth), (int) (mHeight - 0.08 * mHeight), mWidth, mHeight), paint);
        canvas.drawRect(new Rect((int) (mWidth - 0.1 * mWidth), (int) (0.08 * mHeight), mWidth, mHeight - (int) (0.08 * mHeight)), paint);

        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6f);//设置线宽
        paint.setAlpha(100);
        canvas.drawLine((float) (mWidth * 0.1), (float) (mHeight * 0.08), (float) (mWidth * 0.1) + 80, (float) (mHeight * 0.08), paint);
        canvas.drawLine((float) (mWidth * 0.1), (float) (mHeight * 0.08), (float) (mWidth * 0.1), (float) (mHeight * 0.08) + 80, paint);
        canvas.drawLine((float) (mWidth - 0.1 * mWidth), (float) (mHeight * 0.08), (float) (mWidth - mWidth * 0.1) - 80, (float) (mHeight * 0.08), paint);
        canvas.drawLine((float) (mWidth - 0.1 * mWidth), (float) (mHeight * 0.08), (float) (mWidth - mWidth * 0.1), (float) (mHeight * 0.08) + 80, paint);
        canvas.drawLine((float) (mWidth * 0.1), (float) (mHeight - mHeight * 0.08), (float) (mWidth * 0.1) + 80, (float) (mHeight - mHeight * 0.08), paint);
        canvas.drawLine((float) (mWidth * 0.1), (float) (mHeight - mHeight * 0.08), (float) (mWidth * 0.1), (float) (mHeight - mHeight * 0.08) - 80, paint);
        canvas.drawLine((float) (mWidth - 0.1 * mWidth), (float) (mHeight - mHeight * 0.08), (float) (mWidth - mWidth * 0.1) - 80, (float) (mHeight - mHeight * 0.08), paint);
        canvas.drawLine((float) (mWidth - 0.1 * mWidth), (float) (mHeight - mHeight * 0.08), (float) (mWidth - mWidth * 0.1), (float) (mHeight - mHeight * 0.08) - 80, paint);

    }
}
