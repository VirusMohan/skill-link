package com.app.java.skill_link.src.controller;

import com.app.java.skill_link.src.entity.Employee;
import com.app.java.skill_link.src.service.EmployService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class EmployController {

    private final EmployService employService;

    public EmployController(EmployService employService){
        this.employService = employService;
    }

    @PostMapping(value = "/employees")
    public ResponseEntity<Object> save(@RequestBody @Valid Employee employee){
        try {
            return new ResponseEntity<>(employService.saveEmployee(employee), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = {"employees/{employeeId}/tax-deductions"})
    public ResponseEntity<Object> taxDeductions(@PathVariable String employeeId){
        try
        {
            return new ResponseEntity<>(employService.taxDeductions(employeeId),HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    //testing purpose
    @GetMapping(value = "/employee-by-id")
    public ResponseEntity<Employee> getById(@RequestParam Long id){
        return new ResponseEntity<>(employService.getEmployById(id),HttpStatus.OK);
    }

    //testing purpose
    @GetMapping(value = "/all-employees")
    public ResponseEntity<List<Employee>> getAll(){
        return new ResponseEntity<>(employService.getAllEmployees(),HttpStatus.OK);
    }

}
