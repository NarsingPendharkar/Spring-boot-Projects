package org.school.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // Stores User ID instead of direct reference

    private String admissionNumber;
    private LocalDate admissionDate;
    private Long classId; // Stores Class ID instead of direct reference
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
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(Long id, Long userId, String admissionNumber, LocalDate admissionDate, Long classId) {
		super();
		this.id = id;
		this.userId = userId;
		this.admissionNumber = admissionNumber;
		this.admissionDate = admissionDate;
		this.classId = classId;
	}

    // Getters and Setters
    
}
