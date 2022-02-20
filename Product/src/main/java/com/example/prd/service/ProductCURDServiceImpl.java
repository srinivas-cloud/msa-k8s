package com.example.prd.service;

import com.example.prd.dao.entity.Price;
import com.example.prd.dao.PriceRepository;
import com.example.prd.dao.entity.Product;
import com.example.prd.dao.ProductRepository;
import com.example.prd.exce.NoProductFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductCURDServiceImpl implements ProductCRUDService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PriceRepository priceRepository;

    @Override
    public Product findProductById(Integer productId) {
        Optional<Product> productResult = productRepository.findById(productId);
        if(productResult.isPresent()) {
            return productRepository.findById(productId).get();
        } else {
            throw new NoProductFound("Item not found ");
        }
    }

    @Override
    public List<Product> findProductByName(String productName) {
        return productRepository.findByProductNameLike(productName);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void saveProducts(List<Product> products) {
        productRepository.saveAll(products);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Price findPriceBySkuID(Integer skuId) {
        return priceRepository.findBySkuId(skuId);
    }

    @Override
    public void savePrices(List<Price> prices) {
        priceRepository.saveAll(prices);
    }

    @Override
    public List<Price> findAllPrices() {
        return priceRepository.findAll();
    }
}
