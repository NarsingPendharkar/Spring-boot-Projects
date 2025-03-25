package org.school.entity;

import jakarta.persistence.*;

@Entity
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId; // Stores Student ID instead of direct reference

    private Double amount;
    private String status; // Paid or Pending
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Fee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Fee(Long id, Long studentId, Double amount, String status) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.amount = amount;
		this.status = status;
	}

    // Getters and Setters
    
	
}
