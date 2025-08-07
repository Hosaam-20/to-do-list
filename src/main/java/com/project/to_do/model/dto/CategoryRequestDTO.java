package com.project.to_do.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryRequestDTO {

    @NotNull(message = "category name must not be null")
    @NotEmpty(message = "category name must not be empty")
    private String CategoryName;

}
