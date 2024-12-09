package com.anaclara.api.shopping.shopping_api.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.anaclara.api.shopping.shopping_api.model.DTO.ShopDTO;
import com.anaclara.api.shopping.shopping_api.service.ShopService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/shopping")
@RequiredArgsConstructor
public class ShopController {
    
    private final ShopService shopService;

    @GetMapping
    public List<ShopDTO> getShoppings() {
        return shopService.getAll();
    }

    @GetMapping("/{id}")
    public ShopDTO getShoppingById(@PathVariable String id) {
        return shopService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShopDTO createShopping(@RequestBody @Valid ShopDTO shopDTO) {
        return shopService.save(shopDTO);
    }

    @GetMapping("/shopByUser")
    public List<ShopDTO> findShoppingByUser(@RequestParam String userIdentifier) {
        return shopService.findByUser(userIdentifier);
    }

    // ao testar esse endpoint o formato da data deve ser yyyy-MM-ddTHH:mm:ss (e.g 2024-12-09T11:48:04.042)
    @GetMapping("/shopByDate")
    public List<ShopDTO> findShoppingByDate(@RequestParam String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return shopService.findByDate(localDateTime);
    }

    @GetMapping("/identifier/{productIdentifier}")
    public List<ShopDTO> findShoppingByProductIdentifier(@PathVariable String productIdentifier) {
        return shopService.findByProductIdentifier(productIdentifier);
    }

    @GetMapping("/search")
    public List<ShopDTO> findShopsByFilter(
        @RequestParam(required = false) LocalDateTime startDate,
        @RequestParam(required = false) LocalDateTime endDate,
        @RequestParam(required = false) String minimumValue) {
            return shopService.findShopsByFilter(startDate, endDate, minimumValue);
        }

    @GetMapping("/report")
    public List<ShopDTO> getReportByDate(
        @RequestParam LocalDateTime startDate,
        @RequestParam LocalDateTime endDate) {
            return shopService.getReportByDate(startDate, endDate);
        }
}
