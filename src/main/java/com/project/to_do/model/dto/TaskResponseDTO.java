package com.project.to_do.model.dto;

import com.project.to_do.model.entity.Category;
import com.project.to_do.model.entity.User;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskResponseDTO {


    private String username;

    private Long taskId;

    private String title;

    private String description;

    private boolean isCompleted;

    private String categoryName;

    private LocalDateTime createdAt;

}

