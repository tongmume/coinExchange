package com.bizzan.ui.mychart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.bizzan.ui.kline.MyRightMarkerView;

public class MyCusCombinedChart extends CombinedChart {

    private MyRightMarkerView myMarkerViewRight;

    public MyCusCombinedChart(Context context) {
        super(context);
    }

    public MyCusCombinedChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCusCombinedChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();
    }

    public void setMarker(MyRightMarkerView markerRight) {
        this.myMarkerViewRight = markerRight;
    }

    @Override
    protected void drawMarkers(Canvas canvas) {
        if (!mDrawMarkerViews || !valuesToHighlight())
            return;
        for (int i = 0; i < mIndicesToHighlight.length; i++) {
            Highlight highlight = mIndicesToHighlight[i];
            int xIndex = mIndicesToHighlight[i].getXIndex();
            int dataSetIndex = mIndicesToHighlight[i].getDataSetIndex();
            float deltaX = mXAxis != null
                    ? mXAxis.mAxisRange
                    : ((mData == null ? 0.f : mData.getXValCount()) - 1.f);
            if (xIndex <= deltaX && xIndex <= deltaX * mAnimator.getPhaseX()) {
                Entry e = mData.getEntryForHighlight(mIndicesToHighlight[i]);
                // make sure entry not null
                if (e == null || e.getXIndex() != mIndicesToHighlight[i].getXIndex())
                    continue;

                float[] pos = getMarkerPosition(e, highlight);
                // check bounds
                if (!mViewPortHandler.isInBounds(pos[0], pos[1]))
                    continue;
                float yValForXIndex1 = highlight.getValue();
//                float yValForXIndex2 = minuteHelper.getDatas().get(mIndicesToHighlight[i].getXIndex()).per;
                myMarkerViewRight.setNum(yValForXIndex1);
                myMarkerViewRight.refreshContent(e, mIndicesToHighlight[i]);
                /*修复bug*/
                // invalidate();
                /*重新计算大小*/
                myMarkerViewRight.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                myMarkerViewRight.layout(0, 0, myMarkerViewRight.getMeasuredWidth(),
                        myMarkerViewRight.getMeasuredHeight());
                myMarkerViewRight.draw(canvas, mViewPortHandler.contentRight(), pos[1] - myMarkerViewRight.getHeight() / 2);
            }
        }
    }
}
