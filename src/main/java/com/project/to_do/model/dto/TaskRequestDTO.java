package com.project.to_do.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskRequestDTO {

    @NotNull(message = "title must not be null")
    @NotEmpty(message = "title must not be empty")
    private String title;

    @NotNull(message = "description must not be null")
    @NotEmpty(message = "description must not be empty")
    private String description;

    @NotNull(message = "isCompleted must not be null")
    private boolean isCompleted = false;



}
