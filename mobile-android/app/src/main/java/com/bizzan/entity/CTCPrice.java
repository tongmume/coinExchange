package com.bizzan.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class CTCPrice  implements Serializable {

    private BigDecimal buy;
    private BigDecimal sell;

    public BigDecimal getBuy() {
        return buy;
    }

    public void setBuy(BigDecimal buy) {
        this.buy = buy;
    }

    public BigDecimal getSell() {
        return sell;
    }

    public void setSell(BigDecimal sell) {
        this.sell = sell;
    }

}
