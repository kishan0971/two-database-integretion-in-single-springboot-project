package com.in2it.springbootwithmultipledatasource.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
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
        basePackages = "com.in2it.springbootwithmultipledatasource.product.repository",
        entityManagerFactoryRef = "mySqlEntityManagerFactoryBean",
        transactionManagerRef = "mysqlPlatformTransactionManager"
        
)
public class MySqlJpaConnectionConfiguration {
	
//	@Qualifier("mySqlEntityManagerFactoryBean")
//	@Bean
////	@Lazy
//	public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
//	   return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
//	}
//
//    @Autowired
//    private EntityManagerFactoryBuilder entityManagerFactoryBuilder;



    
    @Bean(name = "mySqlEntityManagerFactoryBean")
    LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactoryBean(
            @Qualifier("mySqlDataSource") DataSource dataSource) {
        
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);

        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(adapter);

        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");

        factoryBean.setJpaPropertyMap(properties);
        factoryBean.setPackagesToScan("com.in2it.springbootwithmultipledatasource.product.entity");

        return factoryBean;
    }

    @Bean
    PlatformTransactionManager mysqlPlatformTransactionManager(
            @Qualifier("mySqlEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }
}



