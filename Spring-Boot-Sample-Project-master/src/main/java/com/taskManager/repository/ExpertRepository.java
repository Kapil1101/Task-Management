package com.taskManager.repository;

import com.taskManager.model.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Long> {

//    Expert findByExpertId(String expertId);
//
//    void deleteByExpertId(String expertId);
//
}
