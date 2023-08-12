package com.productservice.service;

import com.google.common.collect.Lists;
import com.productservice.entity.Product;
import com.productservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

@Component
public class ProductService {
    Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ApprovalService approvalService;

    public List<Product> getAllProducts(){
        return Lists.newArrayList(productRepository.findAll());
    }

    public List<Product> getSpecificProducts(String name, double minPrice, double maxPrice, Date minPostedDate, Date maxPostedDate){
        return Lists.newArrayList(productRepository.findAllByNameLikeOrPriceBetweenOrPostedDateBetween(name, minPrice, maxPrice, minPostedDate, maxPostedDate));
    }

    public Product addProduct(Product product){
        if(product.getPrice() > 5000){
            product.setStatus("pending");
            Product p = productRepository.save(product);
            approvalService.approvalRequest(p);
            log.info("Product sent for approval productId : ", p.getId());
            return p;
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Integer productId, Product product){
        Product previous = productRepository.findById(productId).get();
        if (previous != null){
            previous.setPrice(product.getPrice());
            previous.setName(product.getName());
            if (product.getPrice() > 1.5*previous.getPrice()){
                previous.setStatus("pending");
                approvalService.approvalRequest(product);
                log.info("Product sent for approval productId : ", productId);
            }else{
                previous.setStatus(product.getStatus());
            }
            return productRepository.save(previous);
        }
        log.info("No product found to update for productId : ", productId);
        return null;
    }

    public void deleteProduct(Integer productId){
         productRepository.deleteById(productId);
    }

}
