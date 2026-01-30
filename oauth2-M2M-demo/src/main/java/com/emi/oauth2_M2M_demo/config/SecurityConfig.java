package com.emi.oauth2_M2M_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	
	@Bean
	 SecurityFilterChain secfilter(HttpSecurity http)throws Exception{
		return http.cors(AbstractHttpConfigurer::disable)
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.oauth2ResourceServer(a -> a.jwt(Customizer.withDefaults()))
				.build()
				;
	}
}
