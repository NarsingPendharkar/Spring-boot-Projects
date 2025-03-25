package org.school.entity;

import jakarta.persistence.*;

@Entity
public class Marks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId; // Stores Student ID instead of direct reference
    private Long examId;    // Stores Exam ID instead of direct reference

    private int score;

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

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Marks() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Marks(Long id, Long studentId, Long examId, int score) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.examId = examId;
		this.score = score;
	}

   
}
