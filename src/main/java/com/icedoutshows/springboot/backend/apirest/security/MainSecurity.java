package com.icedoutshows.springboot.backend.apirest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.icedoutshows.springboot.backend.apirest.security.jwt.JwtEntryPoint;
import com.icedoutshows.springboot.backend.apirest.security.jwt.JwtTokenFilter;
import com.icedoutshows.springboot.backend.apirest.security.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MainSecurity {

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	JwtEntryPoint jwtEntryPoint;
	
	@Autowired
	JwtTokenFilter jwtTokenFilter;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	AuthenticationManager authenticationManager;
	
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		AuthenticationManagerBuilder builder=httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
		builder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
		authenticationManager=builder.build();
		httpSecurity.authenticationManager(authenticationManager);
		httpSecurity.csrf().disable();
		httpSecurity.cors();
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.authorizeHttpRequests().requestMatchers("/auth/**").permitAll()
		.anyRequest().authenticated();
		httpSecurity.exceptionHandling().authenticationEntryPoint(jwtEntryPoint);
		httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
	
}
