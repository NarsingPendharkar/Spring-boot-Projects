
package org.school.config;

import org.modelmapper.ModelMapper;
import org.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration

@EnableWebSecurity

@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private UserService userService;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public BCryptPasswordEncoder passwordcoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	// authorisation

	@Bean public SecurityFilterChain filter(HttpSecurity httpSecurity) throws
  Exception { httpSecurity.csrf((csrf)->csrf.disable())
  .authorizeHttpRequests((auth)->{
  auth.requestMatchers("/admin/**").hasAuthority("ADMIN")
  .requestMatchers("/student/**").hasAuthority("STUDENT")
  .requestMatchers("/teacher/**").hasAuthority("TEACHER")
  .requestMatchers("/parent/**").hasAuthority("PARENT")
  .requestMatchers("/login","/logout","/register","/saveuser","/create").
  permitAll() .anyRequest().authenticated(); }).formLogin(login -> login
  .loginPage("/login") .loginProcessingUrl("/login")
  .defaultSuccessUrl("/dashboards", true) .permitAll() ) .logout(logout ->
  logout .logoutUrl("/logout") .logoutSuccessUrl("/login") .permitAll() )
  .exceptionHandling(exception -> exception .authenticationEntryPoint((request,
  response, authException) -> { response.sendRedirect("/login"); 
  }) );
  
  return httpSecurity.build();
  
  }

	// authentication

	@Bean
	public AuthenticationManager authmanager() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userService);
		provider.setPasswordEncoder(passwordcoder());
		return new ProviderManager(provider);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
