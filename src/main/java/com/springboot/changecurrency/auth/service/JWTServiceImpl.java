package com.springboot.changecurrency.auth.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.springboot.changecurrency.auth.filter.SimpleGrantedAuthorityMixin;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTServiceImpl implements JWTService{
	
	public static final String SECRET = Base64Utils.encodeToString("Clave.Secreta.123456".getBytes());
	
	public static final long EXPIRATION_DATE = 140000000L;
	
	public static final String TOKEN_PREFIX = "Bearer ";
	
	public static final String HEADER_STRING = "Authorization";

	public String create(Authentication auth) throws IOException {
		String username = ((User) auth.getPrincipal()).getUsername();
		Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
		Claims claims = Jwts.claims();
		claims.put("Authorities", new ObjectMapper().writeValueAsString(roles));
		String token = Jwts.builder().setClaims(claims).setSubject(username)
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).setIssuedAt(new Date())
				.compact();
				//.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DATE)).compact();
		return token;
	}
	
	@Override
	public String createResetToken(String username) throws IOException{
		
		Object r = "[{\"authority\":\"ROLE_PASWD\"}]";
		
		Collection<? extends GrantedAuthority> authorities = Arrays
				.asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
						.readValue(r.toString().getBytes(), SimpleGrantedAuthority[].class));
		
		
		
		Claims claims = Jwts.claims();
		claims.put("Authorities", new ObjectMapper().writeValueAsString(authorities));
		String token = Jwts.builder().setClaims(claims).setSubject(username)
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DATE)).compact();
		return token;
	}


	public boolean validate(String token) {
		// TODO Auto-generated method stub
		try {
			getClaims(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	public Claims getClaims(String token) {
		// TODO Auto-generated method stub
		Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes())
				.parseClaimsJws(resolve(token)).getBody();
		return claims;
	}

	public String getUserName(String token) {
		// TODO Auto-generated method stub
		return getClaims(token).getSubject();
	}

	public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException {
		// TODO Auto-generated method stub
		Object roles = getClaims(token).get("Authorities");

		Collection<? extends GrantedAuthority> authorities = Arrays
				.asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
						.readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));
		return authorities;
	}

	public String resolve(String token) {
		// TODO Auto-generated method stub
		if(token!=null && token.startsWith(TOKEN_PREFIX))
			return token.replace(TOKEN_PREFIX, "");
		else 
			return null;
	}


}
