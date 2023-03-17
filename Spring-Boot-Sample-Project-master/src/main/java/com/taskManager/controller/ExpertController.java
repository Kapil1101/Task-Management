package com.taskManager.controller;

import com.taskManager.repository.ExpertRepository;
import com.taskManager.model.Expert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/experts")
public class ExpertController {
    @Autowired
    private ExpertRepository expertRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Expert> getExpertById(@PathVariable Long id) {
        Optional<Expert> expert = expertRepository.findById(id);
        return expert.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Expert> createExpert(@RequestBody Expert expert) {
        Expert newExpert = expertRepository.save(expert);
        return ResponseEntity.status(HttpStatus.CREATED).body(newExpert);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expert> updateExpert(@PathVariable Long id, @RequestBody Expert expert) {
        Optional<Expert> existingExpert = expertRepository.findById(id);
        if (existingExpert.isPresent()) {
            Expert updatedExpert = existingExpert.get();
            updatedExpert.setName(expert.getName());
            updatedExpert.setEmail(expert.getEmail());
            updatedExpert.setPhoneNumber(expert.getPhoneNumber());
            updatedExpert.setAvailableHours(expert.getAvailableHours());
            expertRepository.save(updatedExpert);
            return ResponseEntity.ok(updatedExpert);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpert(@PathVariable Long id) {
        Optional<Expert> existingExpert = expertRepository.findById(id);
        if (existingExpert.isPresent()) {
            expertRepository.delete(existingExpert.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
