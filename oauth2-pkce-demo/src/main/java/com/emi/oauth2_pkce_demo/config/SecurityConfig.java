package com.emi.oauth2_pkce_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {



	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return http.cors(Customizer.withDefaults())
		     .csrf(csrf -> csrf.ignoringRequestMatchers("/api/home/**"))
		     
		     .authorizeHttpRequests(req ->{
		    	req.anyRequest().authenticated();
		     })
		     .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))	     
		     .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()))
		     .build()
		     ;
		     
	}
}
