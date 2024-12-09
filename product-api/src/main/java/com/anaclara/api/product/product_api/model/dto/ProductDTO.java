package com.anaclara.api.product.product_api.model.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.anaclara.api.product.product_api.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Document(collection = "product")
public class ProductDTO {
    @Id
    private String id;
    private String productIdentifier;
    @NotBlank(message = "Product name is required")
    private String name;
    private String description;
    @NotBlank(message = "Product price is required")
    private String price;

    @JsonIgnore
    private String categoryId;
    private CategoryDTO category;

    public static ProductDTO convert(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductIdentifier(product.getProductIdentifier());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());

        productDTO.setCategory(CategoryDTO.convert(product.getCategory()));
        return productDTO;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
        if(category != null) {
            this.categoryId = category.getId();
        }
    }
}