package com.redis.service;

import com.redis.entity.Customer;
import com.redis.repository.CustomerRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // save customer
    @CacheEvict(value="customers",allEntries = true)
    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }
    // find all
    @Cacheable("customers")
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }
    // find by id
    @Cacheable(value = "customer",key = "#id")
    public Optional<Customer> findById(Long id){
        return customerRepository.findById(id);
    }
    //delete by id should  remove deleted customer as well as all customers
    @Caching(evict = {
            @CacheEvict(value = "customer", key = "#id"),
            @CacheEvict(allEntries = true)
    })
    public  void delete(Long id){
        customerRepository.deleteById(id);
    }

    @CachePut(value = "customer", key = "#customer.id")
    public Customer update(Customer customer){
        return customerRepository.save(customer);
    }


}
