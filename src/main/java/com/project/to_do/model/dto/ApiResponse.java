package com.project.to_do.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ApiResponse<T> {

    private List<T> content;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private Boolean isLast;
}
