package com.app.java.skill_link.src.repository;

import com.app.java.skill_link.src.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepo extends JpaRepository<Worker, Long> {
}
