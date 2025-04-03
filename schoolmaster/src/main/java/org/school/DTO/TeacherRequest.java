package org.school.DTO;

import jakarta.persistence.Column;

public class TeacherRequest {
	
	    private String username;
	    private String password;
	    private String firstName;
	    private String lastName;
	    private String subject;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
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
		public TeacherRequest(String username, String password, String firstName, String lastName, String subject) {
			super();
			this.username = username;
			this.password = password;
			this.firstName = firstName;
			this.lastName = lastName;
			this.subject = subject;
		}
		public TeacherRequest() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return String.format("TeacherRequest [username=%s, password=%s, firstName=%s, lastName=%s, subject=%s]",
					username, password, firstName, lastName, subject);
		}
	    
	    
	    

}
