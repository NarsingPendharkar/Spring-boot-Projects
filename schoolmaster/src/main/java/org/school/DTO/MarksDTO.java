package org.school.DTO;

public class MarksDTO {
    private Long studentId;
    private Long examId;
    private String subject;
    private Integer marksObtained;
    private Integer totalMarks;
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
	public MarksDTO(Long studentId, Long examId, String subject, Integer marksObtained, Integer totalMarks) {
		super();
		this.studentId = studentId;
		this.examId = examId;
		this.subject = subject;
		this.marksObtained = marksObtained;
		this.totalMarks = totalMarks;
	}
	public MarksDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

