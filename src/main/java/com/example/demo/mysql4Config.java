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
@EnableJpaRepositories(basePackages = "com.example.demo.mysql4",
transactionManagerRef = "mysql4TM",
entityManagerFactoryRef = "mysql4LCEMFB")
public class mysql4Config {
	
	@Bean
	@ConfigurationProperties(prefix = "spring.mysql4.datasource")
	public DataSourceProperties mysql4DataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource mysql4DS(@Qualifier("mysql4DataSourceProperties") DataSourceProperties mysql4DataSourceProperties)
	{
		return mysql4DataSourceProperties.initializeDataSourceBuilder().build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean mysql4LCEMFB(@Qualifier("mysql4DS") DataSource mysql4DS)
	{
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	    entityManagerFactoryBean.setDataSource(mysql4DS);
	    entityManagerFactoryBean.setPackagesToScan("com.example.demo.mysql4");
	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
	    entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
	    return entityManagerFactoryBean;
	}
	
	@Bean
	public PlatformTransactionManager mysql4TM(@Qualifier("mysql4LCEMFB") EntityManagerFactory factory)
	{
		return new JpaTransactionManager(factory);
	}

}