package com.TwoDBProject.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.TwoDBProject.primary",
													entityManagerFactoryRef = "primarydbentityManagerFactory",
													transactionManagerRef = "primarydbtransactionManager")
public class PrimaryDatabaseConfig {

	// define database connection using datasource

	   @Value("${spring.datasource.first.url}")
	    private String dbUrl;

	    @Value("${spring.datasource.first.username}")
	    private String dbUsername;

	    @Value("${spring.datasource.first.password}")
	    private String dbPassword;

	    @Value("${spring.datasource.first.driver-class-name}")
	    private String dbDriver;
	    
	    
	@Bean("primarydbdatasource")
	@Primary
	public DataSource databaseconnection() {
		DriverManagerDataSource conn = new DriverManagerDataSource();
		conn.setDriverClassName(dbDriver);
		conn.setUrl(dbUrl);
		conn.setUsername(dbUsername);
		conn.setPassword(dbPassword);
		return conn;
	}
	@Bean("primarydbentityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory=new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(databaseconnection());
		factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factory.setPackagesToScan("com.TwoDBProject.primary");
		Properties jpaProperties=new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", "update");
	    jpaProperties.put("hibernate.show_sql", "true");
	    jpaProperties.put("hibernate.format_sql", "true");
	    factory.setJpaProperties(jpaProperties);
		return factory;
	}
	@Bean("primarydbtransactionManager")
	@Primary
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory ) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}
