package com.project.to_do.service;

import com.project.to_do.model.dto.CategoryRequestDTO;
import com.project.to_do.model.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    public List<CategoryResponseDTO> getAllCategory(Long userId);
    public CategoryResponseDTO getCategoryById(Long categoryId, Long userId);

    public CategoryResponseDTO createCategory(CategoryRequestDTO category, Long userId);
    public CategoryResponseDTO updateCategory(CategoryRequestDTO category, Long categoryId, Long userId);
    public CategoryResponseDTO deleteCategory(Long categoryId, Long userId);
}
