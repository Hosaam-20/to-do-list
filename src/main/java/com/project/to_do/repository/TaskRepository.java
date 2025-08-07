package com.project.to_do.repository;

import com.project.to_do.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


    //filter by date from date to date

    @Query(value = "SELECT * FROM tasks t WHERE t.user_id = :userId AND t.created_at::date BETWEEN :startDate AND :endDate",
            nativeQuery = true)
    List<Task> getTasksFromDateToDate(@Param("userId") Long userId,
                                      @Param("startDate") LocalDate startDate,
                                      @Param("endDate") LocalDate endDate);



}
