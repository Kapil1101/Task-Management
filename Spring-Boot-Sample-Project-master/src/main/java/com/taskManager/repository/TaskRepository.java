package com.taskManager.repository;

import com.taskManager.model.Expert;
import com.taskManager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    
//    List<Task> findByExpertIsNullOrderByTaskDeadlineAsc();
//
//    List<Task> findByExpert(Expert expert);
//
//    List<Task> findByIsCompleted(boolean isCompleted);

}