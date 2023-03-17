package com.taskManager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "experts")
public class Expert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;
    @Column(name = "hours_per_day")
    private int hoursPerDay;

    private int availableHours;

    @OneToMany(mappedBy = "expert", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ExpertAvailability> availabilities = new HashSet<>();

    @OneToMany(mappedBy = "expert", fetch = FetchType.LAZY)
    private Set<Task> tasks = new HashSet<>();

    public Expert() {}

    public Expert(String name, int hoursPerDay) {
        this.name = name;
        this.hoursPerDay = hoursPerDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(int hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    public Set<ExpertAvailability> getAvailabilities() {
        return availabilities;
    }

    public void addAvailability(ExpertAvailability availability) {
        availabilities.add(availability);
        availability.setExpert(this);
    }

    public void removeAvailability(ExpertAvailability availability) {
        availabilities.remove(availability);
        availability.setExpert(null);
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        task.setExpert(this);
    }
}