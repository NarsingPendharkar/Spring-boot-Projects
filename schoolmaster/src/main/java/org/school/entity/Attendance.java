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

@Entity
@Table(name = "attendance")
public class Attendance {

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
    private LocalDate attendanceDate; // Date of attendance

    @Column(nullable = false)
    private String status; // PRESENT, ABSENT, LATE

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

	public LocalDate getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(LocalDate attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Attendance(Long id, Student student, Course course, LocalDate attendanceDate, String status) {
		super();
		this.id = id;
		this.student = student;
		this.course = course;
		this.attendanceDate = attendanceDate;
		this.status = status;
	}

	public Attendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return String.format("Attendance [id=%s, student=%s, course=%s, attendanceDate=%s, status=%s]", id, student,
				course, attendanceDate, status);
	}

    // Constructors, Getters & Setters
    
}

