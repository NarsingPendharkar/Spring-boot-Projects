package org.security.config;

import org.security.repository.AuthRepository;
import org.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
public class AuthConfig {

	@Autowired private AuthRepository authRepository;
	
	@Autowired private AuthService authService;

    
	
	@Bean
	public BCryptPasswordEncoder getpasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// authersiaon
	 @Bean
	    public SecurityFilterChain filter(HttpSecurity http) throws Exception {
	        http.csrf(csrf -> csrf.disable())
	                .authorizeHttpRequests(auth -> auth
	                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
	                        .requestMatchers("/user/**").hasAuthority("USER")
	                        .requestMatchers("/register","/home").permitAll()
	                        .anyRequest().authenticated()
	                )
	                .formLogin(form -> form
	                        .defaultSuccessUrl("/home", true)
	                        .permitAll()
	                )
	                .logout(logout -> logout
	                        .permitAll()
	                )
	                .exceptionHandling(exception -> exception
	                        .accessDeniedHandler((request, response, accessDeniedException) -> {
	                            response.setStatus(403); // Forbidden
	                            response.getWriter().write("You are not authorized to access this resource!");
	                        })
	                );

	        return http.build();
	    }
	
	//authentication
	
	@Bean
	public AuthenticationManager getauthentication() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(getpasswordEncoder());
		provider.setUserDetailsService(authService);
		return new ProviderManager(provider);
		
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	

}
