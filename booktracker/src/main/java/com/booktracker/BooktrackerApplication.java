package com.booktracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans({ @ComponentScan("com.booktracker.configuration"), @ComponentScan("com.booktracker.controllers") })
public class BooktrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooktrackerApplication.class, args);
	}

}
