package org.school.DTO;

import org.school.entity.Student;
import org.school.entity.User;

import jakarta.validation.Valid;

public class StudentData {
	
	@Valid
	private User user;
	@Valid
	private Student student;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public StudentData(@Valid User user, @Valid Student student) {
		super();
		this.user = user;
		this.student = student;
	}
	public StudentData() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return String.format("StudentData [user=%s, student=%s]", user, student);
	}
	
	
	
	

}
