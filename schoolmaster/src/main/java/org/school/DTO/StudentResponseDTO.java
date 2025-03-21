package org.school.DTO;

import java.time.LocalDate;

public class StudentResponseDTO {
	
	private Long id;
	private String firstName;
    private String lastName;
    private String admissionNumber;
    private LocalDate admissionDate;  
    private String className;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public StudentResponseDTO(Long id, String firstName, String lastName, String admissionNumber,
			LocalDate admissionDate, String className) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.admissionNumber = admissionNumber;
		this.admissionDate = admissionDate;
		this.className = className;
	}
	public StudentResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

}
