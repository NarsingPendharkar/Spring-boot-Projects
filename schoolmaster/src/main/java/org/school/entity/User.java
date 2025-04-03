package org.school.entity;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
public class User {
    @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "userseq")
    @SequenceGenerator(name="userseq",sequenceName = "userseq", initialValue = 1000, allocationSize = 1)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    
    @Column(nullable = false)
    private String role;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User(Long id, String username, String password, String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}


	@Override
	public String toString() {
		return String.format("User [id=%s, username=%s, password=%s, role=%s]", id, username, password, role);
	}
    
    
    
    
}
