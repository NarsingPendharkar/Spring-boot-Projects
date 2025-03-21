package org.school.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user; // Separate User entity

	private String employeeId;
	private String subjectSpecialization;

	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
	private List<ClassEntity> assignedClasses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

	public Teacher(Long id, User user, String employeeId, String subjectSpecialization,
			List<ClassEntity> assignedClasses) {
		super();
		this.id = id;
		this.user = user;
		this.employeeId = employeeId;
		this.subjectSpecialization = subjectSpecialization;
		this.assignedClasses = assignedClasses;
	}

	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

}
