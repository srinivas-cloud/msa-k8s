package com.example.prd.vo;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;


public class SkuVo {
    private int id;
    private String name;
    private String skuId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
}
