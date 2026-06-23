package com.redis.controller;

import com.redis.entity.Customer;
import com.redis.service.CustomerService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    // save
    @PostMapping("/save")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }
    // find all
    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }
    // find by id
    @GetMapping("/find/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        Customer customer=customerService.findById(id).orElseThrow(()->new RuntimeException("Customer not found "));
        return customer;
    }
}
