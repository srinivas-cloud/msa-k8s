package com.example.prd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.prd.dao.entity.Price;
import com.example.prd.vo.PriceVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.HashMap;
import java.util.Map;

public class TestClass {
    public static void main(String[] args) {
        PriceVo testPrice = new PriceVo();
        Map<Integer, Double> prices = new HashMap<>();
        prices.put(1001, 1000.50);
        prices.put(1002, 2000.50);
        prices.put(1003, 3000.50);
        prices.put(1010, 4000.50);
        prices.put(1011,5000.50);
        testPrice.setPrices(prices);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        try {
            json = ow.writeValueAsString(testPrice);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);
    }
}
