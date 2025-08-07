package com.project.to_do.service;

import com.project.to_do.model.dto.ApiResponse;
import com.project.to_do.model.dto.TaskRequestDTO;
import com.project.to_do.model.dto.TaskResponseDTO;

import java.util.List;

public interface TaskService {

    public List<TaskResponseDTO> getAllTasks(Long userId);
    public TaskResponseDTO getTaskByUserId(Long taskId, Long userId);

    public TaskResponseDTO addTaskToUser(TaskRequestDTO task, Long userId, Long categoryId);
//    public TaskResponseDTO addTaskToUserWithCategory(TaskRequestDTO task, Long userId, Long categoryId);

    public TaskResponseDTO updateTask(TaskRequestDTO task, Long taskId, Long userId, Long categoryId);

    public TaskResponseDTO deleteTask(Long taskId, Long userId);

}
