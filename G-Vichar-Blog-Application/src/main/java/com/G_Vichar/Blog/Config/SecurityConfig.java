
package com.G_Vichar.Blog.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.G_Vichar.Blog.Filter.JwtAuthFilter;
import com.G_Vichar.Blog.Utils.JwtAuthenticationEntryPoint;

import lombok.extern.slf4j.Slf4j;

@Configuration

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebMvc
@Slf4j
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint point;

	@Autowired
	private JwtAuthFilter authFilter;
	@Autowired
	private CorsConfig corsConfig;
	public static final String[] PUBLIC_URLS = {"/api/posts/**", "/api/categories/**","/api/auth/**", "/api/comments/**", "/api/users/saveUser","/api/likes/share/subscribe/**",
			"/v3/api-docs", "/v2/api-docs", "/swagger-resources/**", "/configuration/security","/swagger-ui/**", "/webjars/**","swagger-ui.html" };

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.cors(cor->cor.configurationSource(corsConfig))
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers(PUBLIC_URLS).permitAll()
				.requestMatchers(HttpMethod.GET).permitAll()
				.requestMatchers("/api/users/**")
				.hasAnyAuthority("NORMAL_USER", "ADMIN_USER")
			  //.requestMatchers("/api/categories/**").hasAuthority("ADMIN_USER")
				.anyRequest().authenticated())
				.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
				.sessionManagement(sessoin -> sessoin.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		log.info("Before Authentication*******");
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		log.info("Before Authentication*******: " + userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		log.info("Before Authentication*******: " + passwordEncoder());
		return authenticationProvider;
	}
	

	
	
	/*
	 * @Bean public FilterRegistrationBean corsFilter() {
	 * UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource(); CorsConfiguration corsConfiguration = new
	 * CorsConfiguration(); corsConfiguration.setAllowCredentials(true);
	 * corsConfiguration.addAllowedOriginPattern("*");
	 * corsConfiguration.addAllowedHeader("Authorization");
	 * corsConfiguration.addAllowedHeader("Content-Type");
	 * corsConfiguration.addAllowedHeader("Accept");
	 * corsConfiguration.addAllowedMethod("POST");
	 * corsConfiguration.addAllowedMethod("GET");
	 * corsConfiguration.addAllowedMethod("DELETE");
	 * corsConfiguration.addAllowedMethod("PUT");
	 * corsConfiguration.addAllowedMethod("OPTIONS");
	 * corsConfiguration.setMaxAge(3600L);
	 * 
	 * source.registerCorsConfiguration("/**", corsConfiguration);
	 * FilterRegistrationBean bean = new FilterRegistrationBean<CorsFilter>(new
	 * CorsFilter(source)); bean.setOrder(-110); return bean;
	 *
	  }*/
	 
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
