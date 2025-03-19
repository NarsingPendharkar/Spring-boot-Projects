package org.jwtauth.configur;

import java.util.Base64;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	@Value("${app.secret.key}")
	private String secretKey;
	
	public String generatToken(String subject) {
		String tokenId=String.valueOf(new Random().nextInt(1000));
		
		return Jwts.builder()
				.setId(tokenId)
				.setSubject(subject)
				.setIssuer("narsing")
				.setExpiration(new Date(System.currentTimeMillis()))
				.signWith(SignatureAlgorithm.HS512,Base64.getEncoder().encode(secretKey.getBytes())).compact();
		
	}
	
	// code to get Claims
		public Claims getClaims(String token) {

			return Jwts.parser()
					.setSigningKey(Base64.getEncoder().encode(secretKey.getBytes()))
					.parseClaimsJws(token)
					.getBody();
		}

		// code to check if token is valid
		public boolean isValidToken(String token) {
			return getClaims(token).getExpiration().after(new Date(System.currentTimeMillis()));
		}
		
		// code to check if token is valid as per username
		public boolean isValidToken(String token,String username) {
			String tokenUserName=getSubject(token);
			return (username.equals(tokenUserName) && !isTokenExpired(token));
		}
		
		// code to check if token is expired
		public boolean isTokenExpired(String token) {
			return getExpirationDate(token).before(new Date(System.currentTimeMillis()));
		}
		
		//code to get expiration date
		public Date getExpirationDate(String token) {
			return getClaims(token).getExpiration();
		}
		
		//code to get expiration date
		public String getSubject(String token) {
			return getClaims(token).getSubject();
		}
	}


