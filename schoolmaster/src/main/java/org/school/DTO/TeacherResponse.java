package org.school.DTO;

public class TeacherResponse {

	private Long id;
	private String username;
    private String firstName;
    private String lastName;
    private String subject;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public TeacherResponse(Long id, String username, String firstName, String lastName, String subject) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.subject = subject;
	}
	@Override
	public String toString() {
		return String.format("TeacherResponse [id=%s, username=%s, firstName=%s, lastName=%s, subject=%s]", id,
				username, firstName, lastName, subject);
	}
	public TeacherResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
