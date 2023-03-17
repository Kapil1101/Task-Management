package com.taskManager.Service;

import com.taskManager.model.Customer;
import com.taskManager.model.Expert;
import com.taskManager.model.Task;
import com.taskManager.repository.CustomerRepository;
import com.taskManager.repository.ExpertRepository;
import com.taskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskManagerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private TaskRepository taskRepository;

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Expert> getExpertById(Long id) {
        return expertRepository.findById(id);
    }

    public Expert createExpert(Expert expert) {
        return expertRepository.save(expert);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id.intValue());
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id.intValue());
    }

    public Optional<Task> assignTaskToExpert(Expert expert) {
        LocalDateTime now = LocalDateTime.now();
        List<Task> tasks = expert.getTasks().stream().collect(Collectors.toList());
        Optional<Task> task = tasks.stream()
                .filter(t -> t.getExpert() == null && t.getDeadline().isAfter(now))
                .sorted((t1, t2) -> t1.getDeadline().compareTo(t2.getDeadline()))
                .findFirst();

        task.ifPresent(t -> {
            t.setExpert(expert);
            taskRepository.save(t);
        });

        return task;
    }

    public void removeExpertFromTask(Task task) {
        task.setExpert(null);
        taskRepository.save(task);
    }
}
