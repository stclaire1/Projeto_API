package com.anaclara.api.shopping.shopping_api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.anaclara.api.shopping.shopping_api.model.DTO.ShopDTO;
import com.anaclara.api.shopping.shopping_api.model.Shop;
import com.anaclara.api.shopping.shopping_api.repository.ShopRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepo;

    public List<ShopDTO> getAll() {
        List<Shop> shoppings = shopRepo.findAll();
        return shoppings.stream()
            .map(ShopDTO::convert)
            .collect(Collectors.toList());
    }

    public ShopDTO findById(String id) {
        Shop shop = shopRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Purchase not found"));
        return ShopDTO.convert(shop);
    }

    public ShopDTO save(ShopDTO shopDTO) {
        Shop shop = shopRepo.save(Shop.convert(shopDTO));
        return ShopDTO.convert(shop);
    }

    public List<ShopDTO> findByUser(String userIdentifier) {
        List<Shop> shoppings = shopRepo.findByUserIdentifier(userIdentifier);
        return shoppings
            .stream()
            .map(ShopDTO::convert)
            .collect(Collectors.toList());
    }

    public List<ShopDTO> findByDate(LocalDateTime date) {
        List<Shop> shops = shopRepo.findByDate(date);
        if (shops.isEmpty()) {
            throw new RuntimeException("No purchases found for this date");
        }
        return shops.stream()
                    .map(ShopDTO::convert)
                    .collect(Collectors.toList());
    }

    public List<ShopDTO> findByProductIdentifier(String productIdentifier) {
        List<Shop> shops = shopRepo.findByItemsProductIdentifier(productIdentifier);
        if (shops.isEmpty()) {
            throw new RuntimeException("No purchases found for this product");
        }
        return shops.stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> findShopsByFilter(LocalDateTime startDate, LocalDateTime endDate, String minimumValue) {
        List<Shop> shops;

        if(startDate != null && endDate != null && minimumValue != null) {
            shops = shopRepo.findByDateBetweenAndTotalGreaterThan(startDate, endDate, minimumValue);
        } else if(startDate != null && endDate != null) {
            shops = shopRepo.findByDateBetween(startDate, endDate);
        } else if(minimumValue != null) {
            shops = shopRepo.findByTotalGreaterThan(minimumValue);
        } else {
            throw new IllegalArgumentException("At least one filter must be provided.");
        }
        return shops.stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getReportByDate(LocalDateTime startDate, LocalDateTime endDate) {
        List<Shop> shops = shopRepo.findByDateBetween(startDate, endDate);
        if (shops.isEmpty()) {
            throw new RuntimeException("No purchases found for this date range");
        }
        return shops
            .stream()
            .map(ShopDTO::convert)
            .collect(Collectors.toList());
    }
}
