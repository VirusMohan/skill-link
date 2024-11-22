package com.app.java.skill_link.src.entity;

import com.app.java.skill_link.src.utils.StringListConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee_data", schema = "assessment_db")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Employee ID is mandatory")
    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^E\\d{3}$", message = "Employee ID should start with 'E' followed by 3 digits (e.g., E123)")
    private String employeeId;

    @NotNull(message = "First name is mandatory")
    @Column(nullable = false)
    private String firstName;

    @NotNull(message = "Last name is mandatory")
    @Column(nullable = false)
    private String lastName;

    @NotNull(message = "Email is mandatory")
    @Email(message = "Email should be in a valid format")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "Date of joining is mandatory")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of joining should be in YYYY-MM-DD format")
    @Column(nullable = false)
    private String doj;

    @NotNull(message = "Salary is mandatory")
    @Positive(message = "Salary should be a positive number")
    @Column(nullable = false)
    private Double salary;

    @Convert(converter = StringListConverter.class)
    @NotNull(message = "Phone number is mandatory")
    //@Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number should be in a valid format (e.g., +1234567890 or 1234567890)")
    @Column(nullable = false)
    private List<@Pattern(
            regexp = "^\\+?[0-9]{10,15}$",
            message = "Phone number should be in a valid format (e.g., +1234567890 or 1234567890)"
    )String> phoneNumbers;
}
