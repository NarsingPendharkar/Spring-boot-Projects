package org.school.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fees")
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long studentId; // Foreign key reference to Student

    @Column(nullable = false)
    private Double amount;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    
    @Column(nullable = false)
    private String status;  // PAID, PENDING, OVERDUE


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


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Fee(Long id, Long studentId, Double amount, Date dueDate, String status) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.amount = amount;
		this.dueDate = dueDate;
		this.status = status;
	}


	public Fee() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return String.format("Fee [id=%s, studentId=%s, amount=%s, dueDate=%s, status=%s]", id, studentId, amount,
				dueDate, status);
	}
    
    
}
