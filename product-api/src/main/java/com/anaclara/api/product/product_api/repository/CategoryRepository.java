package com.anaclara.api.product.product_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.anaclara.api.product.product_api.model.Category;


public interface CategoryRepository extends MongoRepository<Category, String> {
    
}
