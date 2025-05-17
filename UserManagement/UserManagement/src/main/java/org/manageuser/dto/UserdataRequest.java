package org.manageuser.dto;

public class UserdataRequest {
    private String name;
    private String email;
    private String roles;
    private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return String.format("UserdataDTO [name=%s, email=%s, roles=%s, password=%s]", name, email, roles, password);
	}
	public UserdataRequest(String name, String email, String roles, String password) {
		super();
		this.name = name;
		this.email = email;
		this.roles = roles;
		this.password = password;
	}
	public UserdataRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
