package com.anaclara.api.product.product_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.anaclara.api.product.product_api.model.Category;
import com.anaclara.api.product.product_api.model.Product;
import com.anaclara.api.product.product_api.model.dto.ProductDTO;
import com.anaclara.api.product.product_api.repository.CategoryRepository;
import com.anaclara.api.product.product_api.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepo;

    private final CategoryRepository categoryRepo;

    public List<ProductDTO> getAll() {
        List<Product> products = productRepo.findAll();
        return products.stream()
            .map(ProductDTO::convert)
            .collect(Collectors.toList());
    }
    
    public ProductDTO findById(String id) {
        Product product = productRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductDTO.convert(product);
    }

    public ProductDTO save(ProductDTO productDTO) {
        Category category = categoryRepo.findById(productDTO.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = Product.convert(productDTO, category);
        product = productRepo.save(product);
        return ProductDTO.convert(product);
    }

    public ProductDTO update(String id, ProductDTO productDTO) {
        Product product = productRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        if(productDTO.getName() != null && !product.getName().equals(productDTO.getName())) {
            product.setName(productDTO.getName());
        }
        if(productDTO.getDescription() != null && !product.getDescription().equals(productDTO.getDescription())) {
            product.setDescription(productDTO.getDescription());
        }
        if(productDTO.getPrice() != null && !product.getPrice().equals(productDTO.getPrice())) {
            product.setPrice(productDTO.getPrice());
        }
        product = productRepo.save(product);
        return ProductDTO.convert(product);
    }

    public ProductDTO delete(String id) {
        Product product = productRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));
                productRepo.delete(product);
                return ProductDTO.convert(product);
    }

    public Page<ProductDTO> getAllPage(Pageable pageable) {
        Page<Product> products = productRepo.findAll(pageable);
        return products.map(ProductDTO::convert);
    }

    public List<ProductDTO> findByCategoryId(Category categoryId) {
        List<Product> products = productRepo.findByCategoryId(categoryId);
        return products
            .stream()
            .map(ProductDTO::convert)
            .collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(String productIdentifier) {
        Product product = productRepo.findByProductIdentifier(productIdentifier);
        if(product != null) 
            return ProductDTO.convert(product);
        else
            throw new RuntimeException("Product with identifier " + productIdentifier + " not found");
    }   
}
