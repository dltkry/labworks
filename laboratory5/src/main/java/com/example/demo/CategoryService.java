package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void saveCategory(Category category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new IllegalArgumentException("Category name already exists");
        }
        categoryRepository.save(category);
    }

    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
