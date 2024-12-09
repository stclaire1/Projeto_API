package com.anaclara.api.product.product_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.anaclara.api.product.product_api.model.Category;
import com.anaclara.api.product.product_api.model.dto.CategoryDTO;
import com.anaclara.api.product.product_api.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepo;

    public List<CategoryDTO> getAll() {
        List<Category> categories = categoryRepo.findAll();
        return categories.stream()
            .map(CategoryDTO::convert)
            .collect(Collectors.toList());
    }

    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = categoryRepo.save(Category.convert(categoryDTO));
        return CategoryDTO.convert(category);
    }

    public CategoryDTO update(String id, CategoryDTO categoryDTO) {
        Category category = categoryRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found"));
        if(categoryDTO.getName() != null && !category.getName().equals(categoryDTO.getName())) {
            category.setName(categoryDTO.getName());
        }
        category = categoryRepo.save(category);
        return CategoryDTO.convert(category);
    }

    public CategoryDTO delete(String id) {
        Category category = categoryRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found"));
                categoryRepo.delete(category);
                return CategoryDTO.convert(category);
    }

    public Page<CategoryDTO> getAllPage(Pageable pageable) {
        Page<Category> categories = categoryRepo.findAll(pageable);
        return categories.map(CategoryDTO::convert);
    }
}
