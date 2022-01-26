package com.booktracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@ComponentScans({ @ComponentScan("com.booktracker.configuration"), @ComponentScan("com.booktracker.controllers") })
//@EnableWebSecurity(debug = true)
public class BooktrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooktrackerApplication.class, args);
	}

}
