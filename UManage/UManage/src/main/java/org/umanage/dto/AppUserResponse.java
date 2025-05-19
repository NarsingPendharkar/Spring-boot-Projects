package org.umanage.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AppUserResponse {
	private Long id;
	private String name;
	private String email;
	private String roles;
	private boolean enabled;
	private String createdBy;
	private String modifiedBy;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
}
