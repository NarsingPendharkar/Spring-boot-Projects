package org.school.DTO;

public class TeacherDTO {
	  private Long id;
    private String firstName;
    private Long userId;
    private String lastName;
    private String employeeId;
    private String subjectSpecialization;
    

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
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
	
	public TeacherDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TeacherDTO(Long id, String firstName, Long userId, String lastName, String employeeId,
			String subjectSpecialization) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.userId = userId;
		this.lastName = lastName;
		this.employeeId = employeeId;
		this.subjectSpecialization = subjectSpecialization;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TeacherDTO [id=").append(id).append(", firstName=").append(firstName).append(", userId=")
				.append(userId).append(", lastName=").append(lastName).append(", employeeId=").append(employeeId)
				.append(", subjectSpecialization=").append(subjectSpecialization).append("]");
		return builder.toString();
	}
    
	
}
