package org.school.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Teacher extends User {
    private String employeeId;
    private String subjectSpecialization;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClassEntity> assignedClasses; // mappedBy="teacher" now correctly refers to the field in ClassEntity

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getSubjectSpecialization() {
		return subjectSpecialization;
	}

	public void setSubjectSpecialization(String subjectSpecialization) {
		this.subjectSpecialization = subjectSpecialization;
	}

	public List<ClassEntity> getAssignedClasses() {
		return assignedClasses;
	}

	public void setAssignedClasses(List<ClassEntity> assignedClasses) {
		this.assignedClasses = assignedClasses;
	}

	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Teacher(Long id, String firstName, String lastName,
			@Size(min = 10, max = 100, message = "Username should have a length between 10 and 100 characters.") @NotNull String username,
			String password, String role) {
		super(id, firstName, lastName, username, password, role);
		// TODO Auto-generated constructor stub
	}

	public Teacher(String employeeId, String subjectSpecialization, List<ClassEntity> assignedClasses) {
		super();
		this.employeeId = employeeId;
		this.subjectSpecialization = subjectSpecialization;
		this.assignedClasses = assignedClasses;
	}
    
    
    
     
}
