package com.anaclara.api.product.product_api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.anaclara.api.product.product_api.model.dto.CategoryDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Document(collection = "category")
public class Category {
    @Id
    private String id;
    @NotBlank(message = "Category name is required")
    private String name;

    public static Category convert(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return category;
    }
}
