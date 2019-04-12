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
@EnableJpaRepositories(basePackages = "com.example.demo.mysql3",
transactionManagerRef = "mysql3TM",
entityManagerFactoryRef = "mysql3LCEMFB")
public class mysql3Config {
	
	@Bean
	@ConfigurationProperties(prefix = "spring.mysql3.datasource")
	public DataSourceProperties mysql3DataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource mysql3DS(@Qualifier("mysql3DataSourceProperties") DataSourceProperties mysql3DataSourceProperties)
	{
		return mysql3DataSourceProperties.initializeDataSourceBuilder().build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean mysql3LCEMFB(@Qualifier("mysql3DS") DataSource mysql3DS)
	{
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	    entityManagerFactoryBean.setDataSource(mysql3DS);
	    entityManagerFactoryBean.setPackagesToScan("com.example.demo.mysql3");
	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
	    entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
	    return entityManagerFactoryBean;
	}
	
	@Bean
	public PlatformTransactionManager mysql3TM(@Qualifier("mysql3LCEMFB") EntityManagerFactory factory)
	{
		return new JpaTransactionManager(factory);
	}

}