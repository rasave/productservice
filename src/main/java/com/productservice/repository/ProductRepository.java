package com.productservice.repository;

import com.productservice.entity.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Iterable<Product> findAllByNameLikeOrPriceBetweenOrPostedDateBetween(String name, double minPrice, double maxPrice, Date minPostedDate, Date maxPostedDate);
    @Transactional
    @Modifying
    @Query("update Product p set p.status = ?1 where p.id = ?2")
    int updateStatus(String status, int id);
}
