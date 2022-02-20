package com.example.prd.vo;

import java.util.Map;

public class PriceVo {
    private Map<Integer, Double> prices;

    public Map<Integer, Double> getPrices() {
        return prices;
    }

    public void setPrices(Map<Integer, Double> prices) {
        this.prices = prices;
    }
}
