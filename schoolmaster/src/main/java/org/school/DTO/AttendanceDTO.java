package org.school.DTO;

import java.util.Date;

public class AttendanceDTO {
    private Long studentId;
    private Date date;
    private boolean present;
	public AttendanceDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AttendanceDTO(Long studentId, Date date, boolean present) {
		super();
		this.studentId = studentId;
		this.date = date;
		this.present = present;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isPresent() {
		return present;
	}
	public void setPresent(boolean present) {
		this.present = present;
	}
    
}
