package com.booktracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.booktracker.configuration.DataAstraPropertiesConfiguration;
import com.booktracker.configuration.SpringDataPropertiesConfiguration;

@SpringBootApplication
@ComponentScans({ @ComponentScan("com.booktracker.configuration"), @ComponentScan("com.booktracker.controllers"), @ComponentScan("com.bookshare.dataloader.service") })
@EnableCassandraRepositories("com.bookshare.dataloader.data.repository")
@EnableConfigurationProperties({ DataAstraPropertiesConfiguration.class, SpringDataPropertiesConfiguration.class })

public class BooktrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooktrackerApplication.class, args);
	}
	
	

}
