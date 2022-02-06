package com.booktracker.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.data.cassandra")
public class SpringDataPropertiesConfiguration {

	private String schemaAction;

	public String getSchemaAction() {
		return schemaAction;
	}

	public void setSchemaAction(String schemaAction) {
		this.schemaAction = schemaAction;
	}

}
