package org.umanage.dto;

import lombok.Data;

@Data

public class AppUserRequest {
	
    private String name;
    private String email;
    private String roles;
    private String password;
	
}
