package com.bizzan.ui.mychart;

/**
 * Created by Administrator on 2018/3/15.
 */

public class MinutesBean {
    public String time;
    public float open;
    public float close;
    public float high;
    public float low;
    public float cjprice;
    public float cjnum;
    public float avprice = Float.NaN;
    public float per;
    public float cha;
    public float total;
    public int color = 0xff000000;

    public String getTime() {
        return time;
    }

    public float getOpen() {
        return open;
    }

    public float getClose() {
        return close;
    }

    public float getHigh() {
        return high;
    }

    public float getLow() {
        return low;
    }

    public float getCjprice() {
        return cjprice;
    }

    public float getCjnum() {
        return cjnum;
    }

    public float getAvprice() {
        return avprice;
    }

    public float getPer() {
        return per;
    }

    public float getCha() {
        return cha;
    }

    public float getTotal() {
        return total;
    }

    public int getColor() {
        return color;
    }
}
