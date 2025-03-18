package com.kemalakcicek.jwt;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	
	public static final String SECRET_KEY = "yNlgeV5n76i9DOtWYxDoSaDLOSmVMOuGLiPchBfBchw=";

	public String generateToken(UserDetails userDetails) {
		
		return Jwts.builder()
		.setSubject(userDetails.getUsername())
		.setIssuedAt(new Date())
		.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*2))
		.signWith(getKey(), SignatureAlgorithm.HS256)
		.compact();
		
	}
	
	public Claims getClaims(String token) {
		
		Claims claims = Jwts.parserBuilder()
		.setSigningKey(getKey())
		.build()
		.parseClaimsJws(token).getBody();
		
		return claims;
		
	}
	
	public <T> T exporedToken(String token,Function<Claims, T> claimsFunction) {
		
		 Claims claims = getClaims(token);
		 
		 return claimsFunction.apply(claims);
		
	}
	
	public String  getUserNameByToken(String token) {
		
		String subject = exporedToken(token,Claims::getSubject);
		
		return subject;
	}
 	
	public Boolean isTokenExpired(String token) {
		
		Date expiredDate = exporedToken(token, Claims::getExpiration);
		
		
		return new Date().before(expiredDate);
	}
	
	
	public Key getKey() {
		
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		
		return Keys.hmacShaKeyFor(keyBytes);

	}

}
