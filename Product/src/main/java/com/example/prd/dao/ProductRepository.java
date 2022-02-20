package com.example.prd.dao;

import com.example.prd.dao.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    public List<Product> findByProductNameLike(String productName);
}
