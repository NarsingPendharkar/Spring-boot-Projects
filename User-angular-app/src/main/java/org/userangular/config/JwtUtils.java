package org.userangular.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
    private final String SECRET = "uECZQF5DQy5oNsdR7xskT2zF9pEDkqF8QFz3rwX1mRM=";
    private static final int JWT_EXPIRATION_MS = 3000000;  // 15 minutes
    Logger logger=LoggerFactory.getLogger(JwtUtils.class);
    
    private SecretKey generateKey() {
    	return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }
    
    
    public String generateToken(UserDetails userDetails) {
    	String token=
    	    	Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
    			.setExpiration(new Date((new Date()).getTime() + JWT_EXPIRATION_MS)).signWith(generateKey()).compact();
    	    	return token;
    }

	/*
	 * public String extractUsername(String token) { Claims claims =
	 * Jwts.parserBuilder() .setSigningKey(generateKey()) .build()
	 * .parseClaimsJws(token) .getBody(); return claims.getSubject(); }
	 * 
	 */
    public String extractUsername(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(generateKey()) // Make sure the key is correct
                    .build()
                    .parseClaimsJws(token) // Parse the JWT token
                    .getBody();
            
            return claims.getSubject();  // Get the subject (username) from the token
        } catch (ExpiredJwtException e) {
            logger.error("JWT token expired");
            throw new IllegalArgumentException("Token is expired", e);
        } catch (JwtException e) {
            logger.error("Invalid JWT token");
            throw new IllegalArgumentException("Invalid JWT token", e);
        } catch (Exception e) {
            logger.error("Error parsing JWT token", e);
            throw new RuntimeException("Error parsing JWT token", e);
        }
    }


	/*
	 * public boolean validateToken(String token, UserDetails userDetails) { return
	 * extractUsername(token).equals(userDetails.getUsername()); }
	 */
    
    public boolean validateToken(String authToken) {
        try {
            logger.debug("Validating JWT token...");
            Jwts.parserBuilder()
                .setSigningKey(generateKey())
                .build()
                .parseClaimsJws(authToken);  // This line throws if token is invalid
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
    
}
