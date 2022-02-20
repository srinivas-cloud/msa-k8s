package com.example.prd.dao.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "PRODUCT_DETAILS")
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROD_DET_ID")
    private Integer id;
    private String vendor;
    private String dc;
    private String content;
    private boolean launched;
    //@OneToOne(fetch = FetchType.LAZY, optional = false)
    //@JoinColumn(name = "product_id", nullable = false)
    //private Product product;
    public ProductDetails() {}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDc() {
        return dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isLaunched() {
        return launched;
    }

    public void setLaunched(boolean launched) {
        this.launched = launched;
    }

    //public Product getProduct() {
    //    return product;
    //}

    //public void setProduct(Product product) {
    //    this.product = product;
   // }
}
