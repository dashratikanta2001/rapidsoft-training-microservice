package com.ss.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.service.CustumUserDetalisService;

@Component
public class JwtAthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private CustumUserDetalisService custumUserDetalisService;

	@Autowired
	private JwtHelper jwtUtil;

	@Autowired
	private Jackson2ObjectMapperBuilder objectMapperBuilder;

//	public JwtAthenticationFilter(AuthenticationManager authenticationManager) {
//		// TODO Auto-generated constructor stub
//	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (request != null && request.getHeader("Authorization") != null) {
			String header = request.getHeader("Authorization");

			String username = null;
			String jwtToken = null;

			if (header != null && header.startsWith("Bearer ")) {
				jwtToken = header.substring(7);
				try {
					username = this.jwtUtil.getUsernameFromToken(jwtToken);

				} catch (Exception e) {

					ResponseEntity customResponse = new ResponseEntity("Invalid Token", HttpStatus.FORBIDDEN);

					sendJsonResponse(response, customResponse, HttpStatus.FORBIDDEN);

					return;

				}
			}

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				try {
					UserDetails userDetails = this.custumUserDetalisService.loadUserByUsername(username);
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);

				} catch (UsernameNotFoundException e) {

					ResponseEntity customResponse = new ResponseEntity("User Not Found", HttpStatus.UNAUTHORIZED);

					sendJsonResponse(response, customResponse, HttpStatus.UNAUTHORIZED);

					return;
				}
			}
		}

		filterChain.doFilter(request, response);
	}

	private void sendJsonResponse(HttpServletResponse response, ResponseEntity customResponse, HttpStatus status)
			throws IOException {
		ObjectMapper objectMapper = objectMapperBuilder.build();
		response.setStatus(status.value());
		response.setContentType("application/json");
		response.getWriter().write(objectMapper.writeValueAsString(customResponse));
		response.getWriter().flush();
	}

//	 @Autowired
//	    private CustumUserDetalisService custumUserDetalisService;
//
//	    @Autowired
//	    private JwtHelper jwtUtil;
//	    
//	    @Autowired
//	    private Jackson2ObjectMapperBuilder objectMapperBuilder;
//	    
//
//	    
//	    public JwtAthenticationFilter(AuthenticationManager authenticationManager) {
//			// TODO Auto-generated constructor stub
//		}
//
//		@Override
//	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//	            throws ServletException, IOException {
//
//	        if (request != null && request.getHeader("Authorization") != null) {
//	            String header = request.getHeader("Authorization");
//
//	            String username = null;
//	            String jwtToken = null;
//
//	            if (header != null && header.startsWith("Bearer ")) {
//	                jwtToken = header.substring(7);
//	                try {
//	                    username = this.jwtUtil.getUsernameFromToken(jwtToken);
//	                    
//	                } catch (Exception e) {
//	                	
////	                	Response customResponse = new Response(null, "Invalid Token", HttpStatus.FORBIDDEN.value());
//	                	ResponseEntity<String> responseEntity = new ResponseEntity<>("Invalid Token", HttpStatus.FORBIDDEN);
//	                       sendJsonResponse(response, responseEntity, HttpStatus.FORBIDDEN);
//
//	                       return;         
//	                      
//	                }
//	            }
//
//	            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//	                try {
//	                    UserDetails userDetails = this.custumUserDetalisService.loadUserByUsername(username);
//	                    UsernamePasswordAuthenticationToken authToken =
//	                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//	                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//	                    SecurityContextHolder.getContext().setAuthentication(authToken);
//	                    
//	                } catch (UsernameNotFoundException e) {
//	                	
////	                	Response customResponse = new Response(null, "User Not Found", HttpStatus.UNAUTHORIZED.value());
//	                	ResponseEntity<String> responseEntity = new ResponseEntity<>("User Not Found", HttpStatus.FORBIDDEN);
//
//	                      sendJsonResponse(response, responseEntity, HttpStatus.UNAUTHORIZED);
//	                      
//	                     return;
//	                }
//	            }
//	        }
//
//	        filterChain.doFilter(request, response);
//	    }
//	    
//	    private void sendJsonResponse(HttpServletResponse response, ResponseEntity<String> customResponse, HttpStatus status) throws IOException {
//	        ObjectMapper objectMapper = objectMapperBuilder.build();
//	        response.setStatus(status.value());
//	        response.setContentType("application/json");
//	        response.getWriter().write(objectMapper.writeValueAsString(customResponse));
//	        response.getWriter().flush();
//	    }

}
