package com.anaclara.api.product.product_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.anaclara.api.product.product_api.model.dto.ProductDTO;

import lombok.Data;

@Data
@Document(collection = "product")
public class Product {
    @Id
    private String id;
    @Field(name = "productIdentifier")
    private String productIdentifier;
    private String name;
    private String description;
    private String price;

    @DBRef
    private Category category;
    
    public static Product convert(ProductDTO productDTO, Category category) {
        Product product = new Product();
        product.setProductIdentifier(productDTO.getProductIdentifier());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        return product;
    }
}
