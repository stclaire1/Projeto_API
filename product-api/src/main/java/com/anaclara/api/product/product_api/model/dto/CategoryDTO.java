package com.anaclara.api.product.product_api.model.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.anaclara.api.product.product_api.model.Category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Document(collection = "category")
public class CategoryDTO {
    @Id
    private String id;
    @NotBlank(message = "Category name is required")
    private String name;

    public static CategoryDTO convert(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }
}
