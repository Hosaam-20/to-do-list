package com.project.to_do.service.impl;

import com.project.to_do.exception.CategoryNotFoundException;
import com.project.to_do.exception.UserNotFoundException;
import com.project.to_do.model.dto.CategoryRequestDTO;
import com.project.to_do.model.dto.CategoryResponseDTO;
import com.project.to_do.model.entity.Category;
import com.project.to_do.model.entity.Task;
import com.project.to_do.model.entity.User;
import com.project.to_do.model.mapper.CategoryMapper;
import com.project.to_do.repository.CategoryRepository;
import com.project.to_do.repository.UserRepository;
import com.project.to_do.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDTO> getAllCategory(Long userId) {

        User user = this.userRepository.findById(userId).orElseThrow(
                () -> new CategoryNotFoundException("category not found")
        );

        return user.getCategories().stream()
                .map(category -> this.categoryMapper.fromEntityToResponseDto(category))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long categoryId, Long userId) {

        User user = this.userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("user not found")
        );

        Category category = this.categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("category not found")
        );

        if(!category.getUser().getUserId().equals(user.getUserId())){
            throw new RuntimeException("you have not the right to get access");
        }

        return this.categoryMapper.fromEntityToResponseDto(category);
    }

    @Override @Transactional
    public CategoryResponseDTO createCategory(CategoryRequestDTO category, Long userId) {

        User user = this.userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("user not found")
        );

        Category entity = this.categoryMapper.fromRequestDtoToEntity(category);
        entity.setUser(user);

        if(!entity.getUser().getUserId().equals(user.getUserId())){
            throw new RuntimeException("you have not the right to get access");
        }
        Category savedCategory = this.categoryRepository.save(entity);

        entity.setUser(user);
        user.getCategories().add(savedCategory);
        return this.categoryMapper.fromEntityToResponseDto(savedCategory);
    }

    @Override @Transactional
    public CategoryResponseDTO updateCategory(CategoryRequestDTO category, Long categoryId, Long userId) {

        User user = this.userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("user not found")
        );

        Category isCategoryExists = this.categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("category not found")
        );

        if(!isCategoryExists.getUser().getUserId().equals(user.getUserId())){
            throw new RuntimeException("you have not the right to get access");
        }

        isCategoryExists.setCategoryName(category.getCategoryName());
        this.categoryRepository.save(isCategoryExists);

        return this.categoryMapper.fromEntityToResponseDto(isCategoryExists);
    }

    @Override @Transactional
    public CategoryResponseDTO deleteCategory(Long categoryId, Long userId) {

        User user = this.userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("user not found")
        );

        Category isCategoryExists = this.categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("category not found")
        );

        if(!isCategoryExists.getUser().getUserId().equals(user.getUserId())){
            throw new RuntimeException("you have not the right to get access");
        }

        user.getCategories().remove(isCategoryExists);
        CategoryResponseDTO category = this.categoryMapper.fromEntityToResponseDto(isCategoryExists);
        this.categoryRepository.deleteById(categoryId);
        isCategoryExists.setUser(null);


        return category;
    }
}
