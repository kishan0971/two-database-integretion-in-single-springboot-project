package com.in2it.springbootwithmultipledatasource.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MySqlDataSourceConfigretion {
//	@Primary
	@ConfigurationProperties("spring.datasource.mysql")
	@Bean
	public DataSourceProperties mySqlDataSourceProperties() {
		return new DataSourceProperties();
	}

//	public DataSource muSqlDAtasource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setUsername(mySqlDataSourceProperties().getUsername());
//		dataSource.setPassword(mySqlDataSourceProperties().getPassword());
//		dataSource.setUrl(mySqlDataSourceProperties().getUrl());
//		dataSource.setDriverClassName(mySqlDataSourceProperties().getDriverClassName());
//		return  dataSource;
//	}

	@Bean(name = "mySqlDataSource")
	@Qualifier("mySqlDataSourceProperties")
	public DataSource mySqlDatasource() {

		return mySqlDataSourceProperties().initializeDataSourceBuilder().build();
	}

}
