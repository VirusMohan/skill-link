package com.app.java.skill_link.src.repository;

import com.app.java.skill_link.src.entity.Creds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredsRepo extends JpaRepository<Creds, Long> {

    Creds findByUsername(String username);
}
