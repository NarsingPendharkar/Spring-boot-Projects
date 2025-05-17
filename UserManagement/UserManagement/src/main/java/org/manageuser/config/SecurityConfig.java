package org.manageuser.config;

import org.manageuser.serviceImpl.UserdataServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable()) // Disable CSRF for H2 console and APIs
	        .headers(headers -> headers.frameOptions().disable()) // ✅ Needed to allow H2 console in a frame
	        .authorizeHttpRequests(auth -> auth
	        	.requestMatchers("/login","/logout","/register").permitAll()
	            .requestMatchers("/h2-console/**").permitAll() // ✅ H2 console access
	            .requestMatchers("/api/admin/**").hasAuthority("ADMIN")
	            .requestMatchers("/api/user/**").hasAnyAuthority("USER","ADMIN")
	            .anyRequest().authenticated()
	        )
	        .httpBasic(httpBasic -> {}) // Basic Auth for testing with Postman
	        .formLogin(form -> form
	            .defaultSuccessUrl("/api/user", true)
	            .permitAll()
	        )
	        .logout(logout -> logout.permitAll())
	        .exceptionHandling(ex -> ex
	            .accessDeniedHandler((request, response, accessDeniedException) -> {
	                response.setStatus(403);
	                response.getWriter().write("You are not authorized to access this resource!");
	            })
	        );

	    return http.build();
	}

	
	// this is optional if we implement userdetailsserce and create bean of password encoder then spring boot automatically 
	// create authentication managaer for authentications
	// when you are using more thatn one authentication like JWT and database then you need to define this bean
	
	  @Bean 
	  public AuthenticationManager authManager(UserdataServiceImpl userdataservice) { DaoAuthenticationProvider
	  authenticationProvider=new DaoAuthenticationProvider();
	  authenticationProvider.setUserDetailsService(userdataservice);
	  authenticationProvider.setPasswordEncoder(passwordEncoder()); 
	  return new ProviderManager(authenticationProvider);
	  }
}
