package com.bizzan.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/3/12.
 */

public class Plate {

    private List<Exchange> ask;
    private List<Exchange> bid;

    public List<Exchange> getAsk() {
        return ask;
    }

    public void setAsk(List<Exchange> ask) {
        this.ask = ask;
    }

    public List<Exchange> getBid() {
        return bid;
    }

    public void setBid(List<Exchange> bid) {
        this.bid = bid;
    }

}
