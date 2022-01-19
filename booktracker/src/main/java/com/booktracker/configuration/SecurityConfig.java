package com.booktracker.configuration;

import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
		http.cors().configurationSource(new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration corsConfiguration = new CorsConfiguration();
				corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
				corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
				corsConfiguration.setAllowedMethods(Collections.unmodifiableList(Arrays.asList("GET", "PUT")));
				return corsConfiguration;
			}
		}).and().csrf().disable()
		.authorizeRequests((request)->{
			request.mvcMatchers("api/booktracker/dashboard/")
			.authenticated()
			.mvcMatchers("api/booktracker/author/", "api/booktracker/book/", "api/booktracker/search/", "api/booktracker/user/")
			.permitAll();			
		});
	}
}
