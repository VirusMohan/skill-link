package com.app.java.skill_link.src.service;

import com.app.java.skill_link.src.entity.Customer;
import com.app.java.skill_link.src.repository.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepo customerRepo;

    public CustomerService(CustomerRepo repo){
        this.customerRepo = repo;
    }

    public Customer saveCustomer(Customer customer){

        if(customer!=null){
            return customerRepo.findByPhoneNumberAndEmailId(customer.getPhoneNumber(),customer.getEmailId()).stream()
                    .skip(1)
                    .findFirst()
                    .orElse(customerRepo.saveAndFlush(customer));
        }
        return null;
    }

    public Customer getCustomerById(Long cId){
        return customerRepo.findByCustomerId(cId).orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

}
