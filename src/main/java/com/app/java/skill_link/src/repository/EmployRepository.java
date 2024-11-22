package com.app.java.skill_link.src.repository;

import com.app.java.skill_link.src.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployRepository extends JpaRepository<Employee,Long> {

    Employee findByEmployeeId(String cId);
}
