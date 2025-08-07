package com.project.to_do.model.mapper;

import com.project.to_do.model.dto.CategoryRequestDTO;
import com.project.to_do.model.dto.CategoryResponseDTO;
import com.project.to_do.model.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    public Category fromRequestDtoToEntity(CategoryRequestDTO categoryRequestDTO);
    public CategoryRequestDTO fromEntityToRequestDto(Category category);
    public CategoryResponseDTO fromEntityToResponseDto(Category category);
}
