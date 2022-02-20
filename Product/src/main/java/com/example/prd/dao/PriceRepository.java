package com.example.prd.dao;

import com.example.prd.dao.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Integer> {
    public Price findBySkuId(Integer skuId);
}