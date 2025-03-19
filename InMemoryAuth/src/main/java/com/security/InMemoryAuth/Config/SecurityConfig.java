package com.security.InMemoryAuth.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.security.InMemoryAuth.Service.UserService;

@Configuration  // Marks this class as a configuration class for Spring Security
@EnableWebSecurity // Enables Spring Security for the application
public class SecurityConfig {
    
    @Autowired
    private UserService userService; // Injecting custom UserService that implements UserDetailsService

    /**
     * Bean for password encoding using BCrypt hashing algorithm.
     * It helps to securely store passwords by hashing them before saving in DB.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the security filter chain, which defines security rules for the application.
     * 
     * @param http HttpSecurity object to define security configurations.
     * @return SecurityFilterChain with configured security rules.
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disables CSRF protection (useful for REST APIs)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")  // Only users with "ROLE_ADMIN" can access /admin/**
                .requestMatchers("/user/**").hasAuthority("ROLE_USER") // Only users with "ROLE_USER" can access /user/**
                .requestMatchers("/", "/home", "/login", "/register").permitAll() // Allows public access to these endpoints
                .anyRequest().authenticated() // All other requests require authentication
            )
            .formLogin(login -> login
                .loginPage("/login")  // Specifies the login page URL
                .defaultSuccessUrl("/dashboard", true) // Redirects to /dashboard after successful login
                .permitAll() // Allows everyone to access the login page
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // Defines the logout URL
                .logoutSuccessUrl("/login") // Redirects to login page after successful logout
                .permitAll() // Allows everyone to access logout
            )
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendRedirect("/login"); // Redirects unauthorized users to the login page
                })
            );

        return http.build(); // Builds and returns the configured security filter chain
    }

    /**
     * Configures the authentication manager with a DaoAuthenticationProvider.
     * DaoAuthenticationProvider retrieves user details from the database using UserDetailsService.
     * return AuthenticationManager used for authentication.
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService); // Sets custom UserDetailsService to fetch user details
        authProvider.setPasswordEncoder(passwordEncoder()); // Sets password encoder for password verification
        return new ProviderManager(authProvider); // Returns AuthenticationManager with configured authentication provider
    }
}
