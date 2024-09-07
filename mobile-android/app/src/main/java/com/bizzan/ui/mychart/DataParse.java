package com.bizzan.ui.mychart;

import android.util.SparseArray;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.bizzan.app.GlobalConstant;
import com.bizzan.utils.WonderfulDateUtils;

import org.json.JSONArray;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2018/2/10.
 */

public class DataParse implements Serializable {

    private ArrayList<MinutesBean> datas = new ArrayList<>();
    private ArrayList<KLineBean> kDatas = new ArrayList<>();
    private ArrayList<String> xVals = new ArrayList<>();//X轴数据
    private ArrayList<BarEntry> barEntries = new ArrayList<>();//成交量数据
    private ArrayList<CandleEntry> candleEntries = new ArrayList<>();//K线数据
    private ArrayList<Entry> minutesLine = new ArrayList<>();//分时数据

    private ArrayList<Entry> ma5DataL = new ArrayList<>();
    private ArrayList<Entry> ma10DataL = new ArrayList<>();
    private ArrayList<Entry> ma20DataL = new ArrayList<>();
    private ArrayList<Entry> ma30DataL = new ArrayList<>();

    private ArrayList<Entry> ma5DataV = new ArrayList<>();
    private ArrayList<Entry> ma10DataV = new ArrayList<>();
    private ArrayList<Entry> ma20DataV = new ArrayList<>();
    private ArrayList<Entry> ma30DataV = new ArrayList<>();

    private float baseValue;
    private float permaxmin;
    private float volmax;

    private SparseArray<String> xValuesLabel = new SparseArray<>();

    public void parseMinutes(JSONArray object, float baseValue) {
        if (object == null) return;
        /*数据解析依照自己需求来定，如果服务器直接返回百分比数据，则不需要客户端进行计算*/
        this.baseValue = baseValue;
        int count = object.length();
        for (int i = 0; i < count; i++) {
            JSONArray data = object.optJSONArray(i);
//            String[] t = object.optString(i).split(" ");/*  "0930 9.50 4707",*/
            MinutesBean minutesData = new MinutesBean();
            minutesData.open = (float) data.optDouble(1);
            minutesData.close = (float) data.optDouble(4);
            minutesData.high = (float) data.optDouble(2);
            minutesData.low = (float) data.optDouble(3);

            minutesData.time = WonderfulDateUtils.getFormatTime("HH:mm", new Date(data.optLong(0)));
            minutesData.cjprice = (float) data.optDouble(4);
            minutesData.cjnum = (float) data.optDouble(5);
            minutesData.total = minutesData.cjnum * minutesData.cjprice;
            minutesData.avprice = minutesData.cjprice;
            minutesData.cha = minutesData.cjprice - baseValue;
            minutesData.per = (minutesData.cha / baseValue);
            double cha = minutesData.cjprice - baseValue;
            if (Math.abs(cha) > permaxmin) {
                permaxmin = (float) Math.abs(cha);
            }
            volmax = Math.max(minutesData.cjnum, volmax);
            datas.add(minutesData);
        }
        if (permaxmin == 0) {
            permaxmin = baseValue * 0.02f;
        }
    }


    /**
     * 将返回的k线数据转化成 自定义实体类
     */
    public void parseKLine(JSONArray array) {
        //Entry高低 开收           //开高低收
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        for (int i = 0, len = array.length(); i < len; i++) {
            JSONArray data = array.optJSONArray(i);
            String date = WonderfulDateUtils.getFormatTime("HH:mm", new Date(data.optLong(0)));
            //K线实体类
            KLineBean kLineData = new KLineBean(date, (float) data.optDouble(1), (float) data.optDouble(4),
                    (float) data.optDouble(2), (float) data.optDouble(3), (float) data.optDouble(5));
            kDatas.add(kLineData);
            volmax = Math.max(kLineData.vol, volmax);
            xValuesLabel.put(i, kLineData.date);
            minutesLine.add(new Entry((float) data.optDouble(4), i));
        }
    }

    /**
     * 将返回的k线数据转化成 自定义实体类
     */
    public void parseKLine(JSONArray array, int tag) {
        //Entry高低 开收           //开高低收
        for (int i = 0, len = array.length(); i < len; i++) {
            JSONArray data = array.optJSONArray(i);
            String pattern = "MM-dd HH:mm";
            if (tag == GlobalConstant.TAG_DAY)
                pattern = "yyyy-MM-dd";
            String date = WonderfulDateUtils.getFormatTime(pattern, new Date(data.optLong(0)));
            //K线实体类
            KLineBean kLineData = new KLineBean(data.optString(0), (float) data.optDouble(1), (float) data.optDouble(4),
                    (float) data.optDouble(2), (float) data.optDouble(3), (float) data.optDouble(5));
            kDatas.add(kLineData);
            volmax = Math.max(kLineData.vol, volmax);
            xValuesLabel.put(i, kLineData.date);
            minutesLine.add(new Entry((float) data.optDouble(4), i));
        }
    }


    /**
     * 构建Entry数据
     *
     * @param datas
     */
    public void initLineDatas(ArrayList<KLineBean> datas) {
        if (null == datas) return;
        xVals = new ArrayList<>();//X轴数据
        barEntries = new ArrayList<>();//成交量数据
        candleEntries = new ArrayList<>();//K线数据
        for (int i = 0, j = 0; i < datas.size(); i++, j++) {
            xVals.add(datas.get(i).date + "");
            barEntries.add(new BarEntry(i, datas.get(i).high, datas.get(i).low, datas.get(i).open, datas.get(i).close, datas.get(i).vol));
            candleEntries.add(new CandleEntry(i, datas.get(i).high, datas.get(i).low, datas.get(i).open, datas.get(i).close));
        }
    }

    /**
     * 初始化K线图MA均线
     */
    public void initKLineMA(ArrayList<KLineBean> datas) {
        if (null == datas) {
            return;
        }
        ma5DataL = new ArrayList<>();
        ma10DataL = new ArrayList<>();
        ma20DataL = new ArrayList<>();
        ma30DataL = new ArrayList<>();

        KMAEntity kmaEntity5 = new KMAEntity(datas, 5);
        KMAEntity kmaEntity10 = new KMAEntity(datas, 10);
        KMAEntity kmaEntity20 = new KMAEntity(datas, 20);
        KMAEntity kmaEntity30 = new KMAEntity(datas, 30);
        for (int i = 0; i < kmaEntity5.getMAs().size(); i++) {
            if (i >= 5)
                ma5DataL.add(new Entry(kmaEntity5.getMAs().get(i), i));
            if (i >= 10)
                ma10DataL.add(new Entry(kmaEntity10.getMAs().get(i), i));
            if (i >= 20)
                ma20DataL.add(new Entry(kmaEntity20.getMAs().get(i), i));
            if (i >= 30)
                ma30DataL.add(new Entry(kmaEntity30.getMAs().get(i), i));
        }

    }

    /**
     * 初始化成交量MA均线
     */
    public void initVlumeMA(ArrayList<KLineBean> datas) {
        if (null == datas) {
            return;
        }
        ma5DataV = new ArrayList<>();
        ma10DataV = new ArrayList<>();
        ma20DataV = new ArrayList<>();
        ma30DataV = new ArrayList<>();

        VMAEntity vmaEntity5 = new VMAEntity(datas, 5);
        VMAEntity vmaEntity10 = new VMAEntity(datas, 10);
        VMAEntity vmaEntity20 = new VMAEntity(datas, 20);
        VMAEntity vmaEntity30 = new VMAEntity(datas, 30);
        for (int i = 0; i < vmaEntity5.getMAs().size(); i++) {
            ma5DataV.add(new Entry(vmaEntity5.getMAs().get(i), i));
            ma10DataV.add(new Entry(vmaEntity10.getMAs().get(i), i));
            ma20DataV.add(new Entry(vmaEntity20.getMAs().get(i), i));
            ma30DataV.add(new Entry(vmaEntity30.getMAs().get(i), i));
        }

    }

    public ArrayList<Entry> getMinutesLine() {
        return minutesLine;
    }

    public void setMinutesLine(ArrayList<Entry> minutesLine) {
        this.minutesLine = minutesLine;
    }

    public ArrayList<MinutesBean> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<MinutesBean> datas) {
        this.datas = datas;
    }

    public ArrayList<KLineBean> getKLineDatas() {
        return kDatas;
    }

    public void setkDatas(ArrayList<KLineBean> kDatas) {
        this.kDatas = kDatas;
    }

    public ArrayList<String> getxVals() {
        return xVals;
    }

    public void setxVals(ArrayList<String> xVals) {
        this.xVals = xVals;
    }

    public ArrayList<BarEntry> getBarEntries() {
        return barEntries;
    }

    public void setBarEntries(ArrayList<BarEntry> barEntries) {
        this.barEntries = barEntries;
    }

    public ArrayList<CandleEntry> getCandleEntries() {
        return candleEntries;
    }

    public void setCandleEntries(ArrayList<CandleEntry> candleEntries) {
        this.candleEntries = candleEntries;
    }

    public ArrayList<Entry> getMa5DataL() {
        return ma5DataL;
    }

    public void setMa5DataL(ArrayList<Entry> ma5DataL) {
        this.ma5DataL = ma5DataL;
    }

    public ArrayList<Entry> getMa10DataL() {
        return ma10DataL;
    }

    public void setMa10DataL(ArrayList<Entry> ma10DataL) {
        this.ma10DataL = ma10DataL;
    }

    public ArrayList<Entry> getMa20DataL() {
        return ma20DataL;
    }

    public void setMa20DataL(ArrayList<Entry> ma20DataL) {
        this.ma20DataL = ma20DataL;
    }

    public ArrayList<Entry> getMa30DataL() {
        return ma30DataL;
    }

    public void setMa30DataL(ArrayList<Entry> ma30DataL) {
        this.ma30DataL = ma30DataL;
    }

    public ArrayList<Entry> getMa5DataV() {
        return ma5DataV;
    }

    public void setMa5DataV(ArrayList<Entry> ma5DataV) {
        this.ma5DataV = ma5DataV;
    }

    public ArrayList<Entry> getMa10DataV() {
        return ma10DataV;
    }

    public void setMa10DataV(ArrayList<Entry> ma10DataV) {
        this.ma10DataV = ma10DataV;
    }

    public ArrayList<Entry> getMa20DataV() {
        return ma20DataV;
    }

    public void setMa20DataV(ArrayList<Entry> ma20DataV) {
        this.ma20DataV = ma20DataV;
    }

    public ArrayList<Entry> getMa30DataV() {
        return ma30DataV;
    }

    public void setMa30DataV(ArrayList<Entry> ma30DataV) {
        this.ma30DataV = ma30DataV;
    }

    public float getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(float baseValue) {
        this.baseValue = baseValue;
    }

    public float getPermaxmin() {
        return permaxmin;
    }

    public void setPermaxmin(float permaxmin) {
        this.permaxmin = permaxmin;
    }

    public float getVolmax() {
        return volmax;
    }

    public void setVolmax(float volmax) {
        this.volmax = volmax;
    }

    public SparseArray<String> getxValuesLabel() {
        return xValuesLabel;
    }

    public void setxValuesLabel(SparseArray<String> xValuesLabel) {
        this.xValuesLabel = xValuesLabel;
    }

    /**
     * 得到Y轴最小值
     */
    public float getMin() {
        return baseValue - permaxmin;
    }

    /**
     * 得到Y轴最大值
     */
    public float getMax() {
        return baseValue + permaxmin;
    }

    /**
     * 得到百分百最大值
     *
     * @return
     */
    public float getPercentMax() {
        return permaxmin / baseValue;
    }

    /**
     * 得到百分比最小值
     *
     * @return
     */
    public float getPercentMin() {
        return -getPercentMax();
    }


}
