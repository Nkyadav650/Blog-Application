
package com.G_Vichar.Blog.Filter;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.G_Vichar.Blog.ExceptionHandler.InvalidTokenException;
import com.G_Vichar.Blog.ExceptionHandler.TokenExpiredException;
import com.G_Vichar.Blog.ServiceImp.CustomUserDetailsService;
import com.G_Vichar.Blog.Utils.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	public JwtService jwtService;

	@Autowired
	public CustomUserDetailsService userDetailsService;

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		System.out.println("AuthFilter Authorization : "+authHeader);
		String token = null;
		String username = null;
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			try {
			username = jwtService.extractUsername(token);
			System.out.println("username in extract token in authcontroller header : "+username );
		}catch(ExpiredJwtException e){
			throw new TokenExpiredException("Token is Expired !!");
		} catch (MalformedJwtException e) {
			throw new InvalidTokenException("token is not valid !!");
		}
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			System.out.println("JwtAuthFilter.doFilterInternal()" + userDetails.toString());
			if (jwtService.validateToken(token, userDetails)) {
				System.out.println("JwtAuthFilter.doFilterInternal()" + token);
				System.out.println("JwtAuthFilter.doFilterInternal(): password = " + userDetails.getPassword());
				System.out.println("JwtAuthFilter.doFilterInternal() :Authority = "+ userDetails.getAuthorities());
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,
						userDetails.getPassword(), userDetails.getAuthorities());

				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}
