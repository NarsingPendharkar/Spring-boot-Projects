package org.manageuser.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Userdata")
public class Userdata {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1, initialValue = 1001)
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

    @Column(nullable = false,updatable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    // Pre-persist/update hooks
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDate.now();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return String.format(
				"Userdata [id=%s, name=%s, email=%s, roles=%s, enabled=%s, password=%s, createdAt=%s, updatedAt=%s]",
				id, name, email, roles, enabled, password, createdAt, updatedAt);
	}

	public Userdata(Long id, @NotBlank(message = "Invalid user name !") String name,
			@Email(message = "Enter valid email !") @NotBlank(message = "Email is required") String email,
			@NotBlank(message = "Enter valid roles") String roles, boolean enabled,
			@NotBlank(message = "Enter valid password") @Pattern(regexp = "[a-zA-Z0-9]{6,}", message = "Password must be alphanumeric and at least 6 characters") String password,
			LocalDate createdAt, LocalDate updatedAt) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.roles = roles;
		this.enabled = enabled;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Userdata() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}