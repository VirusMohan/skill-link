package com.app.java.skill_link.src.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer", schema = "skill_link", uniqueConstraints = @UniqueConstraint(columnNames = {"email_id","phone_number"}))
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailId;
    private LocalDateTime accCreated;
    private LocalDateTime accUpdated;
}
