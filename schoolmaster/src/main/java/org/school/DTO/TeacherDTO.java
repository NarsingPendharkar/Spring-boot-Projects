package org.school.DTO;

public class TeacherDTO {
    private String firstName;
    private String lastName;
    private String employeeId;
    private String subjectSpecialization;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getSubjectSpecialization() {
		return subjectSpecialization;
	}
	public void setSubjectSpecialization(String subjectSpecialization) {
		this.subjectSpecialization = subjectSpecialization;
	}
	public TeacherDTO(String firstName, String lastName, String employeeId,
			String subjectSpecialization) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	
		this.employeeId = employeeId;
		this.subjectSpecialization = subjectSpecialization;
	}
	public TeacherDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
