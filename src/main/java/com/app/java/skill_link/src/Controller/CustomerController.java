package com.app.java.skill_link.src.Controller;

import com.app.java.skill_link.src.entity.Customer;
import com.app.java.skill_link.src.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService service){
        customerService = service;
    }

    @PostMapping(value = "/save-customer")
    public ResponseEntity<Customer> save(@RequestBody Customer customer){
        customer.setAccCreated(LocalDateTime.now());
        customer.setAccUpdated(LocalDateTime.now());
        return new ResponseEntity<>(customerService.saveCustomer(customer), HttpStatus.OK);
    }

    @GetMapping(value = "/customer-by-id")
    public ResponseEntity<Customer> getById(@RequestParam Long id){
        return new ResponseEntity<>(customerService.getCustomerById(id),HttpStatus.OK);
    }

    @GetMapping(value = "/customers")
    public ResponseEntity<List<Customer>> getAll(){
        return new ResponseEntity<>(customerService.getAllCustomers(),HttpStatus.OK);
    }

}
