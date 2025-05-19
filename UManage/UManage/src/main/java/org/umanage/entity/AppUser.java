package org.umanage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public class AppUser extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1, initialValue = 101)
    private Long id;

    @NotBlank(message = "Invalid user name !")
    @Column(nullable = false, length = 50)
    private String name;

    @Email(message = "Enter valid email !")
    @NotBlank(message = "Email is required")
    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @NotBlank(message = "Enter valid roles")
    @Column(nullable = false, length = 50)
    private String roles;

    private boolean enabled = true;

    @NotBlank(message = "Enter valid password")
    @Column(nullable = false, length = 100)
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

   
    
}