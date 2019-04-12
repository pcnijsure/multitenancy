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
@EnableJpaRepositories(basePackages = "com.example.demo.mysql5",
transactionManagerRef = "mysql5TM",
entityManagerFactoryRef = "mysql5LCEMFB")
public class mysql5Config {
	
	@Bean
	@ConfigurationProperties(prefix = "spring.mysql5.datasource")
	public DataSourceProperties mysql5DataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource mysql5DS(@Qualifier("mysql5DataSourceProperties") DataSourceProperties mysql5DataSourceProperties)
	{
		return mysql5DataSourceProperties.initializeDataSourceBuilder().build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean mysql5LCEMFB(@Qualifier("mysql5DS") DataSource mysql5DS)
	{
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	    entityManagerFactoryBean.setDataSource(mysql5DS);
	    entityManagerFactoryBean.setPackagesToScan("com.example.demo.mysql5");
	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
	    entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
	    return entityManagerFactoryBean;
	}
	
	@Bean
	public PlatformTransactionManager mysql5TM(@Qualifier("mysql5LCEMFB") EntityManagerFactory factory)
	{
		return new JpaTransactionManager(factory);
	}

}