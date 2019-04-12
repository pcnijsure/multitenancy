package com.example.demo;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.demo.mysql1",
transactionManagerRef = "mysql1TM",
entityManagerFactoryRef = "mysql1LCEMFB")
public class mysql1Config {
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.mysql1.datasource")
	public DataSourceProperties mysql1DataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource mysql1DS(@Qualifier("mysql1DataSourceProperties") DataSourceProperties mysql1DataSourceProperties)
	{
		return mysql1DataSourceProperties.initializeDataSourceBuilder().build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean mysql1LCEMFB(@Qualifier("mysql1DS") DataSource mysql1DS)
	{
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	    entityManagerFactoryBean.setDataSource(mysql1DS);
	    entityManagerFactoryBean.setPackagesToScan("com.example.demo.mysql1");
	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
	    entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
	    return entityManagerFactoryBean;
	}
	
	@Bean
	@Primary
	public PlatformTransactionManager mysql1TM(@Qualifier("mysql1LCEMFB") EntityManagerFactory factory)
	{
		return new JpaTransactionManager(factory);
	}

}