package com.example.prd.dao.entity;


import javax.persistence.*;
import java.util.Set;

@Entity(name = "product")
@Table(name = "PRODUCT", uniqueConstraints = {
        @UniqueConstraint(columnNames = "PRODUCT_ID") })
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID", unique = true, nullable = false)
    private  Integer productID;
    @Column(name = "PRODUCT_NAME", unique = false, nullable = false, length = 100)
    private  String productName;
    @Column(name = "PRODUCT_DESC", unique = false, nullable = false, length = 400)
    private  String productDesc;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_PROD_ID", referencedColumnName = "PRODUCT_ID")
    private Set<Sku> skus;

    //@OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "product")
    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "product_det_id", nullable = true)
    private ProductDetails productDetails;

    public Product() {}
    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Set<Sku> getSkus() {
        return skus;
    }

    public void setSkus(Set<Sku> skus) {
        this.skus = skus;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
   }

    public void setProductDetails(ProductDetails productDetails) {
       this.productDetails = productDetails;
   }
}
