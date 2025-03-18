package com.kemalakcicek.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatchers;

import com.kemalakcicek.jwt.AuthEntryPoint;
import com.kemalakcicek.jwt.JwtAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	public static final String AUTHENTICATE = "/authenticate";
	public static final String REGISTER = "/register";
	public static final String REFRESH_TOKEN = "/refreshtoken";
	
	@Autowired
	private AuthEntryPoint authEntryPoint;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	
	public static final String [] SWAGGER_PATHS= {
		  "/swagger-ui/**",
		  "/v3/api-docs/**",
		  "/swagger-ui.html"
	};

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
		.authorizeRequests(
		 request->request.requestMatchers(AUTHENTICATE,REGISTER,REFRESH_TOKEN).permitAll()
		.requestMatchers(SWAGGER_PATHS).permitAll()
		.anyRequest().authenticated())
		.exceptionHandling().authenticationEntryPoint(authEntryPoint).and()
		.sessionManagement(sessison->sessison.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(authenticationFilter,UsernamePasswordAuthenticationFilter.class);
		
		
		return http.build();
	}

}
