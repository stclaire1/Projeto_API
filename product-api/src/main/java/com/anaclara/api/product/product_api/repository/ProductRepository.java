package com.anaclara.api.product.product_api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.anaclara.api.product.product_api.model.Category;
import com.anaclara.api.product.product_api.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByCategoryId(Category categoryId);
    
    Product findByProductIdentifier(String productIdentifier);
}
