package org.umanage.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
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
