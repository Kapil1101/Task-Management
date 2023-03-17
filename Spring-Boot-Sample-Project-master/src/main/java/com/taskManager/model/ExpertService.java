package com.taskManager.model;

import com.taskManager.repository.ExpertRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
public class ExpertService {
    @Autowired
    private ExpertRepository expertRepository;

    public List<Expert> getAllExperts() {
        return expertRepository.findAll();
    }

    public Optional<Expert> getExpertById(Long id) {
        return expertRepository.findById(id);
    }

    public Expert createExpert(Expert expert) {
        return expertRepository.save(expert);
    }

    public Expert updateExpert(Long id, Expert expert) {
        Optional<Expert> existingExpert = expertRepository.findById(id);
        if (existingExpert.isPresent()) {
            Expert updatedExpert = existingExpert.get();
            updatedExpert.setName(expert.getName());
            updatedExpert.setHoursPerDay(expert.getHoursPerDay());
            expertRepository.save(updatedExpert);
            return updatedExpert;
        } else {
            return null;
        }
    }

    public void deleteExpert(Long id) {
        Optional<Expert> existingExpert = expertRepository.findById(id);
        existingExpert.ifPresent(expertRepository::delete);
    }

    public Optional<Task> assignTaskToExpert(Expert expert) {
        LocalDateTime now = LocalDateTime.now();
        List<Task> tasks = (List<Task>) expert.getTasks();
        Optional<Task> task = tasks.stream()
                .filter(t -> t.getExpert() == null && t.getDeadline().isAfter(now))
                .sorted((t1, t2) -> t1.getDeadline().compareTo(t2.getDeadline()))
                .findFirst();

        task.ifPresent(t -> {
            t.setExpert(expert);
            expertRepository.save(expert);
        });

        return task;
    }

}
