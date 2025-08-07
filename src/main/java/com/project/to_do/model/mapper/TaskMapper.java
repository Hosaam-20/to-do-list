package com.project.to_do.model.mapper;

import com.project.to_do.model.dto.TaskRequestDTO;
import com.project.to_do.model.dto.TaskResponseDTO;
import com.project.to_do.model.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    public Task fromRequestDtoToEntity(TaskRequestDTO taskRequestDTO);
    public TaskRequestDTO fromEntityToRequestDto(Task task);
    public TaskResponseDTO fromEntityToResponseDto(Task task);
}
