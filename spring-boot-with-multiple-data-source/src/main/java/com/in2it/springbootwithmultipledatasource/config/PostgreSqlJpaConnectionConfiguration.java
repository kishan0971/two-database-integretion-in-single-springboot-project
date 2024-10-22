package com.in2it.springbootwithmultipledatasource.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@EnableJpaRepositories(
		basePackages = "com.in2it.springbootwithmultipledatasource.order.repository",
		entityManagerFactoryRef = "postgreSqlEntityManagerFactoryBean",
		transactionManagerRef = "postgreSqlPlatformTransactionManager"
		)
public class PostgreSqlJpaConnectionConfiguration {
	@Autowired
	EntityManagerFactoryBuilder entityManagerFactoryBuilder;
	
	@Qualifier("postgreSqlEntityManagerFactoryBean")
	@Bean
	public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
	   return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
	}
	
	@Bean
	LocalContainerEntityManagerFactoryBean postgreSqlEntityManagerFactoryBean(@Autowired EntityManagerFactoryBuilder entityManagerFactoryBuilder,@Qualifier("postgreSqlDAtasource") DataSource dataSource) {
		 LocalContainerEntityManagerFactoryBean factoryBean = entityManagerFactoryBuilder.dataSource(dataSource)
				.packages("com.in2it.springbootwithmultipledatasource.order.entity")
				.build();

	
		    JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		    factoryBean.setJpaVendorAdapter(adapter);
	
		    Map<String, String> properties = new HashMap<>();
		    properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		    properties.put("hibernate.show_sql", "true");
		    properties.put("hibernate.hbm2ddl.auto", "update");
	
		    factoryBean.setJpaPropertyMap(properties);
		 
		 return factoryBean;
	}
	
	
	@Bean
	PlatformTransactionManager postgreSqlPlatformTransactionManager(@Qualifier("postgreSqlEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
		return new JpaTransactionManager(entityManagerFactoryBean.getObject());
	}

}





