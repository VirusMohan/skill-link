package com.app.java.skill_link.src.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "worker", schema = "skill_link")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workerId;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailId;
    private LocalDateTime accCreated;
    private LocalDateTime accUpdated;

    private String expertise;

    @ManyToMany
    @JoinTable(name = "workerSkills",
            joinColumns = @JoinColumn(name = "workerId"),
            inverseJoinColumns = @JoinColumn(name = "skillId"))
    private Set<Skill> skills;

}
