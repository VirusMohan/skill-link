package com.app.java.skill_link.src.repository;

import com.app.java.skill_link.src.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
    Optional<Customer> findByCustomerId(Long cId);

    List<Customer> findByPhoneNumberAndEmailId(String phoneNumber, String emailId);
}
