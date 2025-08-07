package com.project.to_do.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponseDTO {


    private String username;

    private Long categoryId;

    private String CategoryName;

    private LocalDateTime createdAt;


}
