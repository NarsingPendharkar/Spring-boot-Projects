package com.redis.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
@Table(name = "person")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "first name should not be blank !")
    private String firstName;
    @Email(message = "enter valid email !")
    private String email;
    @Positive(message = "Enter valid age")
    private int  age;


}
