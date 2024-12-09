package com.anaclara.api.shopping.shopping_api.model.DTO;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Document(collection = "item")
public class ItemDTO {
    @NotBlank(message = "Product identifier is required")
    private String productIdentifier;
    private String price;
}
