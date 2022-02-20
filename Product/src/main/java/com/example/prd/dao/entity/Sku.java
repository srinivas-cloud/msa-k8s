package com.example.prd.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;

@Entity
@Table(name = "SKU", uniqueConstraints = {
        @UniqueConstraint(columnNames = "SKU_ID") })

public class Sku{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SKU_ID", unique = true, nullable = false)
    private Integer id;
    @Column(name = "NAME", unique = false, nullable = false, length = 400)
    private  String name;
    @Column(name = "SOR_ID", unique = false, nullable = false)
    private String skuId;

    public Sku() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
