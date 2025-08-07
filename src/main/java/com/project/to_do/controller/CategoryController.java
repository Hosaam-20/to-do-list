package com.project.to_do.controller;

import com.project.to_do.model.dto.CategoryRequestDTO;
import com.project.to_do.model.dto.CategoryResponseDTO;
import com.project.to_do.service.impl.CategoryImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryImpl categoryImpl;

    @GetMapping(path = "/{userId}")
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(this.categoryImpl.getAllCategory(userId));
    }

    @GetMapping(path = "/{categoryId}/{userId}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable("userId") Long userId,
                                                               @PathVariable("categoryId") Long categoryId){
        return ResponseEntity.ok(this.categoryImpl.getCategoryById(categoryId, userId));
    }

    @PostMapping(path = "/{userId}")
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody @Valid CategoryRequestDTO category,
                                                              @PathVariable("userId") Long userId){
        return new ResponseEntity(this.categoryImpl.createCategory(category, userId), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{categoryId}/{userId}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@RequestBody @Valid CategoryRequestDTO category,
                                                              @PathVariable("categoryId") Long categoryId,
                                                              @PathVariable("userId") Long userId){
        return  ResponseEntity.ok(this.categoryImpl.updateCategory(category,categoryId,userId));
    }

    @DeleteMapping(path = "/{categoryId}/{userId}")
    public ResponseEntity<CategoryResponseDTO> deleteCategory(@PathVariable("categoryId") Long categoryId,
                                                              @PathVariable("userId") Long userId){
        return  ResponseEntity.ok(this.categoryImpl.deleteCategory(categoryId,userId));
    }
}
