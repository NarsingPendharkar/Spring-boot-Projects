package org.oauth;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	
	@GetMapping("/")
	public String home() {
        return "Welcome to OAuth2 Tutorial!";
    }
	 @GetMapping("/user")
	    public String user(@AuthenticationPrincipal OAuth2User oauthUser) {
	        return "Logged in user details: " + oauthUser.getAttributes().toString();
	    }
	 
	 @GetMapping("/userdata")
	    public Map<String, Object> userDetails(@AuthenticationPrincipal OAuth2User user) {
	        return user.getAttributes();  // Returns all available info from OAuth provider
	    }
}
