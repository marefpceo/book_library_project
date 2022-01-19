package com.bookshare.dataloader.data.configuration;

import java.nio.file.Path;

import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration

public class BeanConfiguration {

	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(
			DataAstraPropertiesConfiguration astraPropertiesConfiguration) {
		Path bundle = astraPropertiesConfiguration.getSecureConnectBundle().toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle);
	}

}
