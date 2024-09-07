//package com.bizzan.entity;
//
//import com.github.tifezh.kchartlib.chart.entity.IMinuteLine;
//
//import java.util.Date;
//
///**
// * 分时图实体
// */
//
//public class MinuteLineEntity implements IMinuteLine {
//    /**
//     * time : 09:30
//     * price : 3.53
//     * avg : 3.5206
//     * vol : 9251
//     */
//
//    private Date time;
//    private float price;
//    private float avg;
//    private float volume;
//    private float Close;
//    private float MA30Price;
//    private float mb;
//
//    public void setMb(float mb) {
//        this.mb = mb;
//    }
//
//    @Override
//    public float getAvgPrice() {
//        return avg;
//    }
//
//    @Override
//    public float getPrice() {
//        return price;
//    }
//
//    @Override
//    public Date getDate() {
//        return time;
//    }
//
//    @Override
//    public float getVolume() {
//        return volume;
//    }
//
//    @Override
//    public float getMA30Price() {
//        return MA30Price;
//    }
//
//    @Override
//    public float getClosePrice() {
//        return Close;
//    }
//
//    @Override
//    public float getMb() {
//        return mb;
//    }
//
//
//    public void setTime(Date time) {
//        this.time = time;
//    }
//
//    public void setPrice(float price) {
//        this.price = price;
//    }
//
//    public void setAvg(float avg) {
//        this.avg = avg;
//    }
//
//    public void setVolume(float volume) {
//        this.volume = volume;
//    }
//
//    public void setClose(float close) {
//        Close = close;
//    }
//
//    public void setMA30Price(float MA30Price) {
//        this.MA30Price = MA30Price;
//    }
//}
