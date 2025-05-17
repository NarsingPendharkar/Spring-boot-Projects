package org.manageuser.dto;
 import java.time.LocalDate;
 public class UserdataResponse {
 	    private Long id;
 	    private String name;
 	    private String email;
 	    private String roles;
 	    private boolean enabled ;
 	    private LocalDate createdAt;
 	    private LocalDate updatedAt;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
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
		public boolean isEnabled() {
			return enabled;
		}
		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
		public LocalDate getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(LocalDate createdAt) {
			this.createdAt = createdAt;
		}
		public LocalDate getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(LocalDate updatedAt) {
			this.updatedAt = updatedAt;
		}
		public UserdataResponse(Long id, String name, String email, String roles, boolean enabled, LocalDate createdAt,
				LocalDate updatedAt) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.roles = roles;
			this.enabled = enabled;
			this.createdAt = createdAt;
			this.updatedAt = updatedAt;
		}
		public UserdataResponse() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return String.format(
					"UserResponseDTO [id=%s, name=%s, email=%s, roles=%s, enabled=%s, createdAt=%s, updatedAt=%s]", id,
					name, email, roles, enabled, createdAt, updatedAt);
		}
 	    
 	    
 }
