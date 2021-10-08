package com.springboot.changecurrency.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.springboot.changecurrency.auth.service.JWTService;
import com.springboot.changecurrency.auth.service.JWTServiceImpl;



public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	private JWTService jwtservice;
	

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager,JWTService jwtservice) {
		super(authenticationManager);
		this.jwtservice = jwtservice;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String header = request.getHeader(JWTServiceImpl.HEADER_STRING);

		if (!requiresAuthentication(header)) {
			chain.doFilter(request, response);
			return;
		}
		
		UsernamePasswordAuthenticationToken authentication=null;
		if(jwtservice.validate(header)) {		
			
			authentication= new UsernamePasswordAuthenticationToken(jwtservice.getUserName(header),null,jwtservice.getRoles(header));
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}
	
	protected boolean requiresAuthentication(String header) {
		
		if (header == null || !header.startsWith(JWTServiceImpl.TOKEN_PREFIX)) {
			return false;
		}else 
			return true;
	}
}
