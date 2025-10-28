package com.buy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.buy.service.UserServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenFilter extends OncePerRequestFilter {
	
	  private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);
	  
	  @Autowired
	  private UserServiceImpl personalService;
	  @Autowired
	  private JwtUtil jwtmethod;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, java.io.IOException {
		  logger.debug("AuthTokenFilter called for URI: {}", request.getRequestURI());
		  try {
			  String jwt = parseJwt(request);
			  if(jwt!=null && jwtmethod.validateToken(jwt)) {
				  String username=jwtmethod.getUsernameFromToken(jwt);
				  UserDetails userDetails = personalService.loadUserByUsername(username);

	                UsernamePasswordAuthenticationToken authentication =
	                        new UsernamePasswordAuthenticationToken(userDetails,
	                                null,
	                                userDetails.getAuthorities());
	                logger.debug("Roles from JWT: {}", userDetails.getAuthorities());

	                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	                SecurityContextHolder.getContext().setAuthentication(authentication);
			  }
		} catch (Exception e) {
			logger.error("Cannot set user authentication: {}", e);
		}
		  filterChain.doFilter(request, response);
		
	}

	private String parseJwt(HttpServletRequest request) {
		String jwt = jwtmethod.getJwtFromHeader(request);
        logger.debug("AuthTokenFilter.java: {}", jwt);
        return jwt;
	}

}