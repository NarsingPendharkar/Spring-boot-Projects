package org.userangular.config;

import java.net.Authenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.userangular.repository.UserRepository;
import org.userangular.service.UserService;

@Configuration
@EnableWebSecurity
public class UserSecurityConfig {

	@Autowired private UserRepository authRepository;
	
	@Autowired private UserService authService;
	
	@Autowired private JwtAuthFilter jwtAuthFilter;

    
	
	@Bean
	public BCryptPasswordEncoder getpasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// authersiaon
	/*
	 * @Bean public SecurityFilterChain filter(HttpSecurity http) throws Exception {
	 * http.csrf(csrf -> csrf.disable()) .authorizeHttpRequests(auth -> auth
	 * .requestMatchers("/admin/**").hasAuthority("ADMIN")
	 * .requestMatchers("/user/**").hasAuthority("USER")
	 * .requestMatchers("/register","/home").permitAll()
	 * .anyRequest().authenticated() ) .formLogin(form -> form
	 * .defaultSuccessUrl("/home", true) .permitAll() ) .logout(logout -> logout
	 * .permitAll() ) .exceptionHandling(exception -> exception
	 * .accessDeniedHandler((request, response, accessDeniedException) -> {
	 * response.setStatus(403); // Forbidden
	 * response.getWriter().write("You are not authorized to access this resource!"
	 * ); }) );
	 * 
	 * return http.build(); }
	 */
	
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/api/auth/**").permitAll()
	            .requestMatchers("/admin/**").hasAuthority("ADMIN")
	            .requestMatchers("/user/**").hasAuthority("USER")
	            .anyRequest().authenticated()
	        )
	        .sessionManagement(sess -> sess
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        )
	        .authenticationProvider(daoAuthProvider())
	        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}

	
	//authentication
	
	 @Bean
	 public DaoAuthenticationProvider daoAuthProvider() {
	     DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	     provider.setUserDetailsService(authService);
	     provider.setPasswordEncoder(getpasswordEncoder());
	     return provider;
	 }

	 @Bean
		public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration configuration) throws Exception {
			return configuration.getAuthenticationManager();
		}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	

}
