package org.school.DTO;

import java.time.LocalDate;
import java.util.Date;

public class StudentDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String admissionNumber;
    private LocalDate admissionDate;  
    private String className;
    

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getAdmissionNumber() {
		return admissionNumber;
	}
	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}
	public LocalDate getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public StudentDTO(String firstName, String lastName, String username, String password, String admissionNumber,
			LocalDate admissionDate, String className) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.admissionNumber = admissionNumber;
		this.admissionDate = admissionDate;
		this.className = className;
	}
	public StudentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
