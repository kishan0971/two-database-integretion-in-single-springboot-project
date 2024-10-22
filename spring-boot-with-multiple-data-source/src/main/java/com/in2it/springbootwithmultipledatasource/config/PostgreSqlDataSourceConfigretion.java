package com.in2it.springbootwithmultipledatasource.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class PostgreSqlDataSourceConfigretion {
//	@Primary
	@ConfigurationProperties("spring.datasource.pg")
	@Bean
	public DataSourceProperties postgreSqlDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Qualifier("postgreSqlDataSourceProperties")
	@Bean("postgreSqlDAtasource")
	public DataSource postgreSqlDAtasource() {

		return postgreSqlDataSourceProperties().initializeDataSourceBuilder().build();
	}

}
