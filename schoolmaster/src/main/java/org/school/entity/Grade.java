package org.school.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student; // References Student entity

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course; // References Course entity

    @Column(nullable = false)
    private String grade; // A, B, C, D, F

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dateAwarded; // Date grade was given

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
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

	public Grade(Long id, Student student, Course course, String grade, LocalDate dateAwarded) {
		super();
		this.id = id;
		this.student = student;
		this.course = course;
		this.grade = grade;
		this.dateAwarded = dateAwarded;
	}

	public Grade() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return String.format("Grade [id=%s, student=%s, course=%s, grade=%s, dateAwarded=%s]", id, student, course,
				grade, dateAwarded);
	}

    // Constructors, Getters & Setters
	
}
