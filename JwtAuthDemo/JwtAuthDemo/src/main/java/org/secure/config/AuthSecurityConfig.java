package org.secure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AuthSecurityConfig {
	

@Bean
public  EntryPoint entryPoint() {
	return new EntryPoint();
}

@Bean
public TokenFilter getTokenFilter() {
	return new TokenFilter();
}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	 @Bean
	    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
	        http.authorizeHttpRequests(authorizeRequests ->
	                authorizeRequests.requestMatchers("/register","/login","/").permitAll()
	                        .requestMatchers("/authuser").permitAll()
	                        .requestMatchers("/admin").hasRole("ADMIN")
	                        .anyRequest().authenticated());
	        http.sessionManagement(session ->
	                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	        http.exceptionHandling(exception -> exception.authenticationEntryPoint(entryPoint()));
	        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
	        http.csrf(csrf -> csrf.disable());
	        http.addFilterBefore(getTokenFilter(), UsernamePasswordAuthenticationFilter.class);

	        return http.build();
	    }
	
	@Bean
	public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
}