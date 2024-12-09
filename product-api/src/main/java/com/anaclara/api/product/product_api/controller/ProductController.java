package com.anaclara.api.product.product_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.anaclara.api.product.product_api.model.Category;
import com.anaclara.api.product.product_api.model.dto.ProductDTO;
import com.anaclara.api.product.product_api.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> getProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable String id) {
        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestBody @Valid ProductDTO productDTO) {
        productDTO.setProductIdentifier(UUID.randomUUID().toString());         
        if (productDTO.getCategory() != null) {
            productDTO.setCategoryId(productDTO.getCategory().getId());
        }
        return productService.save(productDTO);
    }

    @PutMapping("/{id}")
    public ProductDTO editProduct(@PathVariable String id, @RequestBody ProductDTO productDTO) {
        return productService.update(id, productDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        productService.delete(id);
    }

    @GetMapping("/pageable")
    public Page<ProductDTO> getProductsPageable(Pageable pageable) {
        return productService.getAllPage(pageable);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> getProductByCategoryId(@PathVariable Category categoryId) {
        return productService.findByCategoryId(categoryId);
    }

    @GetMapping("/identifier/{productIdentifier}")
    public ProductDTO getProductByIdentifier(@PathVariable String productIdentifier) {
        return productService.findByProductIdentifier(productIdentifier);
    }
}
