package com.example.demo.security;

import com.example.demo.model.UserEntity;
import io.jsonwebtoken.Claims; 
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.crypto.SecretKey;

@Slf4j
@Service
public class TokenProvider {
  private static final String SECRET_KEY = "FlRpX30pMqDbiAkmlfArbrmVkDD4RqISskGZmBFax5oGVxzXXWUzTR5JyskiHMIV9M1Oicegkpi46AdvrcX1E6CmTUBc6IFbTPiD";
  private final SecretKey key;

  public TokenProvider() {
    this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
  }

  public String create(UserEntity userEntity) {
	    Date expiryDate = Date.from(
	        Instant.now()
	            .plus(1, ChronoUnit.DAYS));

	    return Jwts.builder()
	        .subject(userEntity.getId())
	        .issuer("demo app")
	        .issuedAt(new Date())
	        .expiration(expiryDate)
	        .signWith(key)
	        .compact();
	  }

	  public String validateAndGetUserId(String token) {
	    Claims claims = Jwts.parser()
	        .verifyWith(key)
	        .build()
	        .parseSignedClaims(token)
	        .getPayload();

	    return claims.getSubject();
	  }

	  public boolean validateToken(String token) {
		    try {
		      Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
		      return true;
		    } catch (JwtException | IllegalArgumentException e) {
		      log.error("Invalid JWT token: {}", e.getMessage());
		      return false;
		    }
		  }

		  public String getUserIdFromToken(String token) {
		    try {
		      Claims claims = Jwts.parser()
		          .verifyWith(key)
		          .build()
		          .parseSignedClaims(token)
		          .getPayload();
		      return claims.getSubject();
		    } catch (JwtException | IllegalArgumentException e) {
		      log.error("Error getting user id from token: {}", e.getMessage());
		      return null;
		    }
		  }
		}