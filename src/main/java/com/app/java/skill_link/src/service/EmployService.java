package com.app.java.skill_link.src.service;

import com.app.java.skill_link.src.entity.Employee;
import com.app.java.skill_link.src.entity.dto.SalaryDetails;
import com.app.java.skill_link.src.repository.EmployRepository;
import com.app.java.skill_link.src.utils.TaxCalculationUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployService {

    private TaxCalculationUtils utils;

    private EmployRepository employRepository;

    public EmployService(TaxCalculationUtils utils, EmployRepository repo){
        this.utils = utils;
        this.employRepository = repo;
    }

    public Employee saveEmployee(Employee employee){
        if(employee!=null){
            return employRepository.saveAndFlush(employee);
        }
        return null;
    }

    public SalaryDetails taxDeductions(String employeeId){
        var empData = employRepository.findByEmployeeId(employeeId);
        if(empData!= null){
            return utils.calculateTax(empData);
        }
        return null;
    }


    public Employee getEmployById(Long cId){
        return employRepository.findById(cId).orElse(null);
    }

    public List<Employee> getAllEmployees() {
        return employRepository.findAll();
    }

}
