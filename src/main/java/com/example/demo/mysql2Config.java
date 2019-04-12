package com.example.demo;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.demo.mysql2",
transactionManagerRef = "mysql2TM",
entityManagerFactoryRef = "mysql2LCEMFB")
public class mysql2Config {
	
	@Bean
	@ConfigurationProperties(prefix = "spring.mysql2.datasource")
	public DataSourceProperties mysql2DataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource mysql2DS(@Qualifier("mysql2DataSourceProperties") DataSourceProperties mysql2DataSourceProperties)
	{
		return mysql2DataSourceProperties.initializeDataSourceBuilder().build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean mysql2LCEMFB(@Qualifier("mysql2DS") DataSource mysql2DS)
	{
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	    entityManagerFactoryBean.setDataSource(mysql2DS);
	    entityManagerFactoryBean.setPackagesToScan("com.example.demo.mysql2");
	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
	    entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
	    return entityManagerFactoryBean;
	}
	
	@Bean
	public PlatformTransactionManager mysql2TM(@Qualifier("mysql2LCEMFB") EntityManagerFactory factory)
	{
		return new JpaTransactionManager(factory);
	}

}