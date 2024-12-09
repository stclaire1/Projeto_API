package com.anaclara.api.shopping.shopping_api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.anaclara.api.shopping.shopping_api.model.Shop;

// como "Items" está embutido em Shop, não é necessário criar um repositório para ele
@Repository
public interface ShopRepository extends MongoRepository<Shop, String> {
    // buscar pelo identificador do usuário
    List<Shop> findByUserIdentifier(String userIdentifier);

    // buscar pela data
    List<Shop> findByDate(LocalDateTime date);

    // buscar pelo identificador do produto
    List<Shop> findByItemsProductIdentifier(String productIdentifier);
    
    // buscar por intervalo de datas
    List<Shop> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // buscar por intervalo de datas e total maior que um valor
    List<Shop> findByDateBetweenAndTotalGreaterThan(LocalDateTime startDate, LocalDateTime endDate, String total);

    // buscar por total maior que um valor
    List<Shop> findByTotalGreaterThan(String total);
}
