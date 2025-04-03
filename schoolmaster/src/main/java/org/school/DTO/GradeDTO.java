package org.school.DTO;

import java.time.LocalDate;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class GradeDTO {

	private Long id;

	private Long studentId; 

	private long courseId; 

	private String grade; 

	@Temporal(TemporalType.DATE)
	private LocalDate dateAwarded;

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

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public LocalDate getDateAwarded() {
		return dateAwarded;
	}

	public void setDateAwarded(LocalDate dateAwarded) {
		this.dateAwarded = dateAwarded;
	}

	public GradeDTO(Long id, Long studentId, long courseId, String grade, LocalDate dateAwarded) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.courseId = courseId;
		this.grade = grade;
		this.dateAwarded = dateAwarded;
	}

	public GradeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return String.format("GradeDTO [id=%s, studentId=%s, courseId=%s, grade=%s, dateAwarded=%s]", id, studentId,
				courseId, grade, dateAwarded);
	} 
	
	
	
	

}
