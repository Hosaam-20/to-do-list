package com.project.to_do.controller;

import com.project.to_do.model.dto.TaskRequestDTO;
import com.project.to_do.model.dto.TaskResponseDTO;
import com.project.to_do.service.impl.TaskImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskImpl taskImpl;

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(this.taskImpl.getAllTasks(userId));
    }

    @GetMapping(path = "/{taskId}/user/{userId}")
    public ResponseEntity<TaskResponseDTO> getTaskByUserId(@PathVariable("taskId") Long taskId,
                                                           @PathVariable("userId") Long userId){
        return ResponseEntity.ok(this.taskImpl.getTaskByUserId(taskId, userId));
    }

    @GetMapping(path = "/start/{start}/end/{end}/user/{userId}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksFromDateToDate(@PathVariable("userId") Long userId,
                                                        @PathVariable("start")
                                                        LocalDate startDate,
                                                        @PathVariable("end")
                                                         LocalDate endDate){

        return ResponseEntity.ok(this.taskImpl.getTasksFromDateToDate(userId, startDate, endDate));
    }

    @PostMapping(path = "/user/{userId}")
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody @Valid TaskRequestDTO task,
                                                      @PathVariable("userId") Long userId,
                                                      @RequestParam(required = false) Long categoryId){

        return new ResponseEntity(this.taskImpl.addTaskToUser(task, userId, categoryId), HttpStatus.CREATED);
    }



    @PutMapping(path = "/{taskId}/user/{userId}")
    public ResponseEntity<TaskResponseDTO> updateTask(@RequestBody @Valid TaskRequestDTO task,
                                                      @PathVariable("taskId") Long taskId,
                                                      @PathVariable("userId") Long userId,
                                                      @RequestParam(required = false) Long categoryId){

        return new ResponseEntity<>(this.taskImpl.updateTask(task, taskId, userId, categoryId), HttpStatus.ACCEPTED);
    }


    @DeleteMapping(path = "/{taskId}/user/{userId}")
    public ResponseEntity<TaskResponseDTO> deleteTask(@PathVariable("taskId") Long taskId,
                                                      @PathVariable("userId") Long userId){

        return new ResponseEntity(this.taskImpl.deleteTask(taskId, userId), HttpStatus.ACCEPTED);
    }
}
