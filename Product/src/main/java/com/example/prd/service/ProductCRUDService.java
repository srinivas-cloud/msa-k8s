package com.example.prd.service;

import com.example.prd.dao.entity.Price;
import com.example.prd.dao.entity.Product;

import java.util.List;

public interface ProductCRUDService {
    public Product findProductById(Integer productId);
    public List<Product> findProductByName(String productName);
    public void saveProduct(Product product);
    public void saveProducts(List<Product> products);
    public List<Product> findAll();
    public Price findPriceBySkuID(Integer skuId);
    public void savePrices(List<Price> prices);
    public List<Price> findAllPrices();
}
