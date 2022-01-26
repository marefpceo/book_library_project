package com.userdb.configuration;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().configurationSource(new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setMaxAge(3600l);
				return config;
			}
		}).and().csrf().disable().

		
				authorizeRequests((requests) -> requests.antMatchers("/api/dashboard/*", "api/user/login")
						.authenticated()
						.mvcMatchers("api/author/*", "api/book/*", "api/search/*", "api/user/register")
						.permitAll());
		http.formLogin();
		http.httpBasic();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new SCryptPasswordEncoder();
	}
}
