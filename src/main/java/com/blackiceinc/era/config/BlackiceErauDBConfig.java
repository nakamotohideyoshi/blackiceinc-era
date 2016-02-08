package com.blackiceinc.era.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAsync
@EnableTransactionManagement
@PropertySource({ "classpath:persistence-blackicedb-erau.properties" })
@ComponentScan({ "com.blackiceinc.era.persistence.erau" })
@EnableJpaRepositories(basePackages = "com.blackiceinc.era.persistence.erau.repository", 
entityManagerFactoryRef = "blackiceDbErauEntityManager", 
transactionManagerRef = "blackiceDbErauTransactionManager")
public class BlackiceErauDBConfig {

	@Autowired
	private Environment env;

	public BlackiceErauDBConfig() {
		super();
	}

	@Bean(name = "blackiceDbErauEntityManager")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(erauDataSource());
		em.setPackagesToScan(new String[] { "com.blackiceinc.era.persistence.erau.model" });

		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		// vendorAdapter.set
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean(name = "erauDataSource")
	@Primary
	public DataSource erauDataSource() {

		final DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.pass"));

		return dataSource;
	}

	final Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();
		// hibernateProperties.setProperty("hibernate.hbm2ddl.auto",
		// env.getProperty("hibernate.hbm2ddl.auto"));
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		// hibernateProperties.setProperty("hibernate.globally_quoted_identifiers",
		// "true");
		hibernateProperties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");

		hibernateProperties.setProperty("hibernate.format_sql", "true");

		// hibernateProperties.setProperty("hibernate.max_fetch_depth", "3");

		// hibernateProperties.setProperty("hibernate.connection.provider_class",
		// "org.hibernate.connection.C3P0ConnectionProvider");

		// hibernateProperties.setProperty("hibernate.c3p0.acquire_increment",
		// "5");
		// hibernateProperties.setProperty("hibernate.c3p0.idle_test_period",
		// "1800");
		// hibernateProperties.setProperty("hibernate.c3p0.max_size", "600");
		// hibernateProperties.setProperty("hibernate.c3p0.max_statements",
		// "50");
		// hibernateProperties.setProperty("hibernate.c3p0.min_size", "5");
		// hibernateProperties.setProperty("hibernate.c3p0.timeout", "1800");
		// hibernateProperties.setProperty("hibernate.c3p0.validate", "true");

		// hibernateProperties.setProperty("hibernate.generate_statistics",
		// "true");

		 hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "false");
		// hibernateProperties.setProperty("hibernate.cache.use_query_cache",
		// "true");
		 hibernateProperties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");

		// hibernateProperties.setProperty("net.sf.ehcache.configurationResourceName",
		// "/ehcache-config.xml");

		return hibernateProperties;
	}

}
