package com.taskManager.controller;

import com.taskManager.model.Expert;
import com.taskManager.model.Task;
import com.taskManager.repository.ExpertRepository;
import com.taskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ExpertRepository expertRepository;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task newTask = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") Long taskId) {
        Optional<Task> task = taskRepository.findById(Math.toIntExact(taskId));
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long taskId, @RequestBody Task task) {
        Optional<Task> optionalExistingTask = taskRepository.findById(Math.toIntExact(taskId));
        if (optionalExistingTask.isPresent()) {
            Task existingTask = optionalExistingTask.get();
            existingTask.setDescription(task.getDescription());
            existingTask.setCompletionTime(task.getCompletionTime());
            existingTask.setDeadline(task.getDeadline());
            existingTask.setType(task.getType());

            // fetch the Expert instance corresponding to the given expertId
            Optional<Expert> optionalExpert = expertRepository.findById(task.getId());
            if (optionalExpert.isPresent()) {
                Expert expert = optionalExpert.get();
                existingTask.setExpert(expert);
            } else {
                return ResponseEntity.badRequest().build();
            }

            Task updatedTask = taskRepository.save(existingTask);
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
