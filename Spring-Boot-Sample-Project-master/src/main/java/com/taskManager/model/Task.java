package com.taskManager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expert_id")
    private Expert expert;
    @Enumerated(EnumType.STRING)
    private TaskType type;

    private LocalDateTime deadline;
    private String title;
    private String description;


    private LocalDateTime completionTime;

    public Task() {
    }

    public Task(Customer customer, TaskType type, LocalDateTime deadline) {
        this.customer = customer;
        this.type = type;
        this.deadline = deadline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(LocalDateTime completionTime) {
        this.completionTime = completionTime;
    }

    public boolean isCompleted() {
        return completionTime != null;
    }

    public boolean isOverdue() {
        return deadline.isBefore(LocalDateTime.now());
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }


}
