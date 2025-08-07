package com.project.to_do.service.impl;

import com.project.to_do.exception.CategoryNotFoundException;
import com.project.to_do.exception.TaskNotFoundException;
import com.project.to_do.exception.UserNotFoundException;
import com.project.to_do.model.dto.TaskRequestDTO;
import com.project.to_do.model.dto.TaskResponseDTO;
import com.project.to_do.model.entity.Category;
import com.project.to_do.model.entity.Task;
import com.project.to_do.model.entity.User;
import com.project.to_do.model.mapper.TaskMapper;
import com.project.to_do.repository.CategoryRepository;
import com.project.to_do.repository.TaskRepository;
import com.project.to_do.repository.UserRepository;
import com.project.to_do.service.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;
    private final CategoryRepository  categoryRepository;


    @Override
    public List<TaskResponseDTO> getAllTasks(Long userId) {

        log.info("get all tasks for user {}", userId);
        User isUserExists =  this.userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("user not found")
        );

        List<Task> tasks = isUserExists.getTasks();


        return tasks.stream()
                .map(task ->this.taskMapper.fromEntityToResponseDto(task))
                .collect(Collectors.toList());
    }


    @Override
    public TaskResponseDTO getTaskByUserId(Long taskId, Long userId) {

        log.info("get task {} for user {}",taskId, userId);
        User user = this.userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("user not found")
        );

        Task task = this.taskRepository.findById(taskId).orElseThrow(
                () -> new TaskNotFoundException("task not found")
        );

        if(!task.getUser().getUserId().equals(userId))
            throw new RuntimeException("you have not the right to access");


        return this.taskMapper.fromEntityToResponseDto(task);

    }

    @Override
    @Transactional
    public TaskResponseDTO addTaskToUser(TaskRequestDTO task, Long userId,
                                         Long categoryId) {

        log.info("add task {} for user {} and category {}",task, userId, categoryId);
        User isUserExists = this.userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("User Not Found")
        );

        Task entity = this.taskMapper.fromRequestDtoToEntity(task);

        if(categoryId != null){

            Category isCategoryExists = this.categoryRepository.findById(categoryId).orElseThrow(
                    () -> new CategoryNotFoundException("Category Not Found")
            );

            entity.setCategory(isCategoryExists);
            isCategoryExists.getTasks().add(entity);
        }

        isUserExists.getTasks().add(entity);
        entity.setUser(isUserExists);
        Task savedTask = this.taskRepository.save(entity);

        return this.taskMapper.fromEntityToResponseDto(savedTask);
    }

    @Override @Transactional
    public TaskResponseDTO updateTask(TaskRequestDTO task, Long taskId ,
                                      Long userId, Long categoryId) {

        log.info("user {} update his task {} as {}", userId, taskId, task);
        User isUserExists = this.userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User Not Found")
        );

        Task isTaskExists = this.taskRepository.findById(taskId).orElseThrow(
                () -> new TaskNotFoundException("Task Not Found")
        );

        if(!isTaskExists.getUser().getUserId().equals(userId) )
            throw new RuntimeException("you have not the right to access");

        if(categoryId != null){
            Category isCategoryExists = this.categoryRepository.findById(categoryId).orElseThrow(
                    () -> new CategoryNotFoundException("Category Not Found")
            );

            if(!isCategoryExists.getUser().getUserId().equals(userId) )
                throw new RuntimeException("you have not the right to access");

            isTaskExists.setCategory(isCategoryExists);
            isCategoryExists.getTasks().add(isTaskExists);
        }


        isTaskExists.setTitle(task.getTitle());
        isTaskExists.setDescription(task.getDescription());
        isTaskExists.setCompleted(task.isCompleted());
        this.taskRepository.save(isTaskExists);

        return this.taskMapper.fromEntityToResponseDto(isTaskExists);
    }

    @Override @Transactional
    public TaskResponseDTO deleteTask(Long taskId, Long userId) {

        log.info("user {} delete task with id {}:", userId, taskId);
        User isUserExists = this.userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("user not found")
        );

        Task isTaskExists = this.taskRepository.findById(taskId).orElseThrow(
                () -> new TaskNotFoundException("Task Not Found")
        );

        if(!isTaskExists.getUser().getUserId().equals(userId))
            throw new RuntimeException("you have not the right to access");

        this.taskRepository.delete(isTaskExists);

        return this.taskMapper.fromEntityToResponseDto(isTaskExists);
    }


    public List<TaskResponseDTO> getTasksFromDateToDate(Long userId,
                                                        LocalDate start,
                                                        LocalDate end){

        log.info("filter by date of creation from {} to {}", start, end);
        List<TaskResponseDTO> tasks = this.taskRepository.getTasksFromDateToDate(userId, start, end)
                .stream()
                .map(task -> this.taskMapper.fromEntityToResponseDto(task))
                .collect(Collectors.toList());


        return tasks;
    }
}
