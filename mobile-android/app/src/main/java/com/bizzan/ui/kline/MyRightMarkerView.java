package com.bizzan.ui.kline;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.bizzan.R;

import java.text.DecimalFormat;

public class MyRightMarkerView extends MarkerView {

    private TextView markerTv;
    private DecimalFormat mFormat;
    private float num;

    public MyRightMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        mFormat = new DecimalFormat("#0.0");
        markerTv = (TextView) findViewById(R.id.tvInfo);
        markerTv.setTextSize(9);

    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        markerTv.setText(mFormat.format(num));
    }

    public void setNum(float num) {
        this.num = num;
    }

    @Override
    public int getXOffset(float xpos) {
        return 0;
    }

    @Override
    public int getYOffset(float ypos) {
        return 0;
    }
}
