package com.anaclara.api.shopping.shopping_api.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.anaclara.api.shopping.shopping_api.model.DTO.ShopDTO;

import lombok.Data;

@Data
@Document(collection = "shop")
public class Shop {
    @Id
    private String id;
    private String userIdentifier;
    private LocalDateTime date;
    private List<Item> items;
    private String total;

    public static Shop convert(ShopDTO shopDTO) {
        Shop shop = new Shop();
        shop.setUserIdentifier(shopDTO.getUserIdentifier());
        shop.setDate(LocalDateTime.now());
        shop.setItems(
            shopDTO.getItems()
                .stream()
                .map(itemDTO -> {
                    Item item = new Item();
                    item.setProductIdentifier(itemDTO.getProductIdentifier());
                    item.setPrice(itemDTO.getPrice());
                    return item;
                }).collect(Collectors.toList())
        );
        shop.setTotal(shopDTO.getTotal());
        return shop;
    }

    @Data
    public static class Item {
        private String productIdentifier;
        private String price;
    }
}