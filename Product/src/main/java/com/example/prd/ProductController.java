package com.example.prd;

import com.example.prd.dao.entity.Price;
import com.example.prd.dao.entity.Product;
import com.example.prd.dao.entity.Sku;
import com.example.prd.exce.NoProductFound;
import com.example.prd.service.ProductCRUDService;
import com.example.prd.vo.PriceVo;
import com.example.prd.vo.ProductVo;
import com.example.prd.vo.SkuVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/")
public class ProductController {

    private final static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductCRUDService productService;

    @RequestMapping(value = {"/getProduct/{productName}", "/getProduct", "/test"})
    public ResponseEntity<List<ProductVo>> findProduct(@PathVariable(required = false) Optional<String> productName) {
        List<ProductVo> products = new ArrayList<ProductVo>();

        if(productName.isPresent() && "sample".equalsIgnoreCase(productName.get())) {
            ProductVo productVo = new ProductVo();
            productVo.setProductID(123);
            productVo.setProductName("sample product");
            productVo.setProductDesc("sample prod discussion");
            products.add(productVo);
        }

        if(products.isEmpty()) {
            throw new NoProductFound("Item Not Found");
        } else {
            return new ResponseEntity<List<ProductVo>>(products, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> findProductByID(@PathVariable String id) {
        Product product = productService.findProductById(Integer.parseInt(id));

        if(product != null) {
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        } else {
            throw new NoProductFound(id + " : Product not found");
        }
    }

    @RequestMapping(value = "/product/name/{productName}", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findProductByName(@PathVariable String productName) {
        List<Product> product = productService.findProductByName(productName);

        if(product != null) {
            return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
        } else {
            throw new NoProductFound(productName + " : Product not found");
        }
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAll() {
        List<Product> product = productService.findAll();

        if(product != null) {
            return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
        } else {
            throw new NoProductFound(" Product not found");
        }
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ResponseEntity<String> updateProduct(@RequestBody ProductVo product) {
        logger.info("entry to product save method " + product.toString());
        Product productTemp = new Product();
        productTemp.setProductName(product.getProductName());
        productTemp.setProductDesc(product.getProductDesc());
        //productTemp.setProductAttributes(product.productAttributes());

        if(product.getSkus() != null && !product.getSkus().isEmpty()) {
            Set<Sku> skus = new HashSet<Sku>();
            for (SkuVo sku : product.getSkus()) {
                Sku skuTemp = new Sku();
                skuTemp.setSkuId(sku.getSkuId());
                skuTemp.setName(sku.getName());
                skus.add(skuTemp);
            }
            productTemp.setSkus(skus);
        }
        productService.saveProduct(productTemp);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/product/price/{id}", method = RequestMethod.GET)
    public ResponseEntity<Price> findProductPrice(@PathVariable String id) {
        Price price = productService.findPriceBySkuID(Integer.parseInt(id));

        if(price != null) {
            return new ResponseEntity<Price>(price, HttpStatus.OK);
        } else {
            throw new NoProductFound(id + " : Product not found");
        }
    }

    @RequestMapping(value = "/product/prices", method = RequestMethod.POST)
    public ResponseEntity<String> savePrices(@RequestBody PriceVo prices) {
        if(prices.getPrices() != null && !prices.getPrices().isEmpty()) {
            List<Price> pricesItems = new ArrayList<Price>();
            for(Map.Entry<Integer, Double> price : prices.getPrices().entrySet()) {
                Price priceItem = new Price();
                priceItem.setSkuId(price.getKey());
                priceItem.setPrice(price.getValue());
                pricesItems.add(priceItem);
            }
            productService.savePrices(pricesItems);
            return new ResponseEntity<String>("Success", HttpStatus.OK);
        }
        return new ResponseEntity<String>("No Items saved", HttpStatus.OK);
    }

    @RequestMapping(value = "/product/prices", method = RequestMethod.GET)
    public ResponseEntity<List<Price>> findPrices() {
        List<Price> pricesItems = productService.findAllPrices();
        return new ResponseEntity<List<Price>>(pricesItems, HttpStatus.OK);
    }
}
