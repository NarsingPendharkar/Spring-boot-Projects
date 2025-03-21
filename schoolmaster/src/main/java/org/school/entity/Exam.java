package org.school.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String examName;
    private Date examDate;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public ClassEntity getClassEntity() {
		return classEntity;
	}

	public void setClassEntity(ClassEntity classEntity) {
		this.classEntity = classEntity;
	}

	public Exam(Long id, String examName, Date examDate, ClassEntity classEntity) {
		super();
		this.id = id;
		this.examName = examName;
		this.examDate = examDate;
		this.classEntity = classEntity;
	}

	public Exam() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}

