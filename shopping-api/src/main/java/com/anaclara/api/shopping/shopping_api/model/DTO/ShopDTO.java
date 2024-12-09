package com.anaclara.api.shopping.shopping_api.model.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.anaclara.api.shopping.shopping_api.model.Shop;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Document(collection = "shop")
public class ShopDTO {
    @Id
    private String id;
    @NotBlank(message = "User identifier is required")
    private String userIdentifier;
    private LocalDateTime date;
    private List<ItemDTO> items;
    private String total;

    public static ShopDTO convert(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setId(shop.getId());
        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shopDTO.setDate(shop.getDate());
        shopDTO.setItems(
            shop.getItems().stream()
                .map(item -> {
                    ItemDTO itemDTO = new ItemDTO();
                    itemDTO.setProductIdentifier(item.getProductIdentifier());
                    itemDTO.setPrice(item.getPrice());
                    return itemDTO;
                }).collect(Collectors.toList())
        );
        shopDTO.setTotal(shop.getTotal());
        return shopDTO;
    }
}