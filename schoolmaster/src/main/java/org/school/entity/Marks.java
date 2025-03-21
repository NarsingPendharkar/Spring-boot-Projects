package org.school.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Marks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    private String subject;
    private Integer marksObtained;
    private Integer totalMarks;
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
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Integer getMarksObtained() {
		return marksObtained;
	}
	public void setMarksObtained(Integer marksObtained) {
		this.marksObtained = marksObtained;
	}
	public Integer getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(Integer totalMarks) {
		this.totalMarks = totalMarks;
	}
	public Marks(Long id, Student student, Exam exam, String subject, Integer marksObtained, Integer totalMarks) {
		super();
		this.id = id;
		this.student = student;
		this.exam = exam;
		this.subject = subject;
		this.marksObtained = marksObtained;
		this.totalMarks = totalMarks;
	}
	public Marks() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
