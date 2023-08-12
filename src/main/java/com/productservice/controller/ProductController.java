package com.productservice.controller;

import com.productservice.entity.Product;
import com.productservice.repository.ProductRepository;
import com.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(method = GET)
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> resourceWithUrls = service.getAllProducts();
        return new ResponseEntity<>(resourceWithUrls, OK);
    }

    @RequestMapping(value = "/search", method = GET)
    public ResponseEntity<Iterable<Product>> getProducts(@DefaultValue("") @RequestParam(name="productName") Optional<String> name,
                                                         @DefaultValue("0") @RequestParam(name="minPrice") Optional<Double> minPrice,
                                                         @DefaultValue("0")@RequestParam(name="maxPrice") Optional<Double> maxPrice,
                                                         @RequestParam(name="minPostedDate") @DateTimeFormat(pattern = "yyyy-MM-dd")  Optional<Date> minPostedDate,
                                                         @RequestParam(name="maxPostedDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<Date> maxPostedDate) {
        Iterable<Product> resourceWithUrls = service.getSpecificProducts(name.orElse(""), minPrice.orElse(0d), maxPrice.orElse(0d), minPostedDate.orElse(new Date()), maxPostedDate.orElse(new Date()));
        return new ResponseEntity<>(resourceWithUrls, OK);
    }

    @RequestMapping(method = POST)
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(service.addProduct(product), OK);
    }

    @RequestMapping(value = "/{productId}", method = PUT)
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Integer productId, @RequestBody Product product) {
        return new ResponseEntity<>(service.updateProduct(productId, product), OK);
    }

    @RequestMapping(value = "/{productId}", method = DELETE)
    public void deleteProduct(@PathVariable("productId") Integer productId) {
        service.deleteProduct(productId);
    }

}
