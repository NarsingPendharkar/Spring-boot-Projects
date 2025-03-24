package test.jwtAuthProject.Util;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class MethodsOfJwt{
	private static Logger logger=LoggerFactory.getLogger(MethodsOfJwt.class);
	
	@Value("${narsing.app.Secret}")
	private  String sercretKey;
    @Value("${narsing.app.ExpirationMs}")
	private  int expiryTime;

	public  String generateTokenFromUsername(UserDetails userDetails) throws InvalidKeyException {
		String username=userDetails.getUsername();
		
		return Jwts.
				builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(new Date((new Date()).getTime() + expiryTime))
				.signWith(key())
				.compact();
	}
	
	
	public String getUsernamefromToken(String token) {
		String username=Jwts.parser().verifyWith((SecretKey)key()).build().parseSignedClaims(token).getPayload().getSubject();
		return username;
	}
	
	public boolean validateToken(String authToken) {
		try {
			 System.out.println("Validate");
	            Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(authToken);
	            return true;
		}  catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
	}

	 private  Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(sercretKey));
    }
	 
	 public String getJwtFromHeader(HttpServletRequest request) {
	        String bearerToken = request.getHeader("Authorization");
	        logger.debug("Authorization Header: {}", bearerToken);
	        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
	            return bearerToken.substring(7); // Remove Bearer prefix
	        }
	        return null;
	    }

}
