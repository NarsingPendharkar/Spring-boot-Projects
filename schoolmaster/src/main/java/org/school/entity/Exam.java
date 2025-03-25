package org.school.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String examName;
    private Date examDate;

    private Long classId; // Stores Class ID instead of direct reference

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

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public Exam() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Exam(Long id, String examName, Date examDate, Long classId) {
		super();
		this.id = id;
		this.examName = examName;
		this.examDate = examDate;
		this.classId = classId;
	}

    // Getters and Setters
    
}
