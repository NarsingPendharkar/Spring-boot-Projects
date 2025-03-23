package com.TwoDBProject.secondary.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TwoDBProject.secondary.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
