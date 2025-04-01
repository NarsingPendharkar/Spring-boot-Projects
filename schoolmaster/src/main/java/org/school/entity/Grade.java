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
@Table(name = "grades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long studentId; // Foreign key reference to Student

    @Column(nullable = false)
    private Long courseId; // Foreign key reference to Course

    @Column(nullable = false)
    private String grade;

    @Temporal(TemporalType.DATE)
    private Date dateAwarded;

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

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Date getDateAwarded() {
		return dateAwarded;
	}

	public void setDateAwarded(Date dateAwarded) {
		this.dateAwarded = dateAwarded;
	}

	public Grade(Long id, Long studentId, Long courseId, String grade, Date dateAwarded) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.courseId = courseId;
		this.grade = grade;
		this.dateAwarded = dateAwarded;
	}

	public Grade() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return String.format("Grade [id=%s, studentId=%s, courseId=%s, grade=%s, dateAwarded=%s]", id, studentId,
				courseId, grade, dateAwarded);
	}
    
    
}