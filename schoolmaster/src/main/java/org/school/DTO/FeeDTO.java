package org.school.DTO;

import java.util.Date;

public class FeeDTO {
    private Long studentId;
    private Double amount;
    private Date dueDate;
    private Boolean isPaid;
	public FeeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FeeDTO(Long studentId, Double amount, Date dueDate, Boolean isPaid) {
		super();
		this.studentId = studentId;
		this.amount = amount;
		this.dueDate = dueDate;
		this.isPaid = isPaid;
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
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Boolean getIsPaid() {
		return isPaid;
	}
	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}
    
}
