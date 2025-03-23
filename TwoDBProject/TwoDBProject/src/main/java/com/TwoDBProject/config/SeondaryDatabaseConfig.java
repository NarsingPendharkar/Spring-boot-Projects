package com.TwoDBProject.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;
import lombok.val;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.TwoDBProject.secondary",
													entityManagerFactoryRef = "seconddbentityManagerFactory",
													transactionManagerRef = "seconddbtransactionManager")
public class SeondaryDatabaseConfig {

	// define database connection using datasource

	   @Value("${spring.datasource.second.url}")
	    private String dbUrl;

	    @Value("${spring.datasource.second.username}")
	    private String dbUsername;

	    @Value("${spring.datasource.second.password}")
	    private String dbPassword;

	    @Value("${spring.datasource.second.driver-class-name}")
	    private String dbDriver;
	    
	    
	@Bean("seconddbdatasource")
	public DataSource databaseconnection() {
		DriverManagerDataSource conn = new DriverManagerDataSource();
		conn.setDriverClassName(dbDriver);
		conn.setUrl(dbUrl);
		conn.setUsername(dbUsername);
		conn.setPassword(dbPassword);
		return conn;
	}
	@Bean("seconddbentityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory=new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(databaseconnection());
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factory.setPackagesToScan("com.TwoDBProject.secondary");
		Properties jpaProperties=new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", "update");
	    jpaProperties.put("hibernate.show_sql", "true");
	    jpaProperties.put("hibernate.format_sql", "true");
	    factory.setJpaProperties(jpaProperties);
		return factory;
	}
	@Bean("seconddbtransactionManager")
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory ) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}