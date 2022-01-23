package com.booktracker.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableAsync
@EnableTransactionManagement
@EnableWebMvc
public class ApplicationConfig {


	@Bean(name = "HSQLDatasourceInMemory")
	public DataSource dataSource() {
		System.out.println("In datasource bean.");
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL).addScript("classpath:table.sql")
				.addScript("classpath:userdata.sql").build();
		return db;
	}

	@Bean("namedJDBCTemplate")
	public NamedParameterJdbcOperations namedTemplate(@Qualifier("HSQLDatasourceInMemory") DataSource dataSource) {
		NamedParameterJdbcOperations jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

		return jdbcTemplate;
	}
	
}
