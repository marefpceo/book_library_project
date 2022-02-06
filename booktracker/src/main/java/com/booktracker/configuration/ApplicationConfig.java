package com.booktracker.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableAsync
@EnableTransactionManagement
@EnableWebMvc
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
	@Value("${spring.datasource.url}")
	private String databaseUrl;
	
	@Value("${spring.datasource.user}")
	private String databaseUser;
	
	@Value("${spring.datasource.driver}")
	private String databaseDriver;
	
	
	
	@Value("${spring.hsql.database.url}")
	private String hsqlatabaseUrl;
	
	@Value("${spring.hsql.database.user}")
	private String hsqlDatabaseUser;
	
	@Value("${spring.hsql.database.driver}")
	private String hsqlDatabaseDriver;
	
	@Bean(name="hsqlDatasource")
	DataSource hsqldataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(hsqlatabaseUrl);
		driverManagerDataSource.setUsername(hsqlDatabaseUser);
		driverManagerDataSource.setDriverClassName(hsqlDatabaseDriver);
		return driverManagerDataSource;
	}
	
	@Bean(name = "HSQLDatasourceInMemory")
	public DataSource dataSource() {
		System.out.println("In datasource bean.");
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL).addScript("classpath:table.sql")
				.addScript("classpath:userdata.sql").build();
		return db;
	}
	
	@Bean(name="postgreDatasource")
	DataSource postgredataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(databaseUrl);
		driverManagerDataSource.setUsername(databaseUser);
		//driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
		driverManagerDataSource.setDriverClassName(databaseDriver);
		return driverManagerDataSource;
	}
	
	@Bean
	TransactionManager transactionManager(@Qualifier("hsqlDatasource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean("namedJDBCTemplate")
	public NamedParameterJdbcOperations namedTemplate(@Qualifier("hsqlDatasource") DataSource dataSource) {
		NamedParameterJdbcOperations jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

		return jdbcTemplate;
	}
	
}
