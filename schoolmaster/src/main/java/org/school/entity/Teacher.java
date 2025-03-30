package org.school.entity;

import jakarta.persistence.*;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userId; // Stores User ID instead of direct reference
    @Column(nullable = false)
    private String employeeId;
    private String subjectSpecialization;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Teacher(Long id, Long userId, String employeeId, String subjectSpecialization) {
		super();
		this.id = id;
		this.userId = userId;
		this.employeeId = employeeId;
		this.subjectSpecialization = subjectSpecialization;
	}
	@Override
	public String toString() {
		return String.format("Teacher [id=%s, userId=%s, employeeId=%s, subjectSpecialization=%s]", id, userId,
				employeeId, subjectSpecialization);
	}

    // Getters and Setters
}
