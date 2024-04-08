package com.msa.template.order.config.database;

import com.msa.template.core.database.DatabaseType;
import com.msa.template.core.database.ReplicationRoutingDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	basePackages = DataBaseConfiguration.DOMAIN_BASE_PACKAGE,
	transactionManagerRef = DataBaseConfiguration.DOMAIN_TRANSACTION_MANAGER,
	entityManagerFactoryRef = DataBaseConfiguration.DOMAIN_ENTITY_MANAGER_FACTORY)
public class DataBaseConfiguration {

	public static final String DOMAIN_ENTITY_MANAGER_FACTORY = "domainEntityManagerFactory";
	public static final String DOMAIN_JPA_PROPERTIES = "domainJpaProperties";
	public static final String DOMAIN_HIBERNATE_PROPERTIES = "domainHibernateProperties";
	public static final String DOMAIN_TRANSACTION_MANAGER = "domainTransactionManager";
	public static final String DOMAIN_PERSIST_UNIT = "domain";
	public static final String DOMAIN_BASE_PACKAGE = "com.msa.template.order";

	@Bean(name = "integrationReadDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.read")
	public DataSource integrationReadDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "integrationWriteDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.write")
	public DataSource integrationWriteDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "integrationRoutingDataSource")
	public DataSource integrationRoutingDataSource() {
		ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();
		routingDataSource.setTargetDataSources(integrationTargetDataSources());
		routingDataSource.setDefaultTargetDataSource(integrationWriteDataSource());
		return routingDataSource;
	}

	private Map<Object, Object> integrationTargetDataSources() {
		Map<Object, Object> targetDataSourceMap = new HashMap<>();
		targetDataSourceMap.put(DatabaseType.READ, integrationReadDataSource());
		targetDataSourceMap.put(DatabaseType.WRITE, integrationWriteDataSource());
		return targetDataSourceMap;
	}

	@Bean(name = "integrationDataSource")
	public DataSource IntegrationDataSource() {
		return new LazyConnectionDataSourceProxy(integrationRoutingDataSource());
	}

	@Primary
	@Bean(name = DOMAIN_ENTITY_MANAGER_FACTORY)
	public LocalContainerEntityManagerFactoryBean domainEntityManagerFactory(
		@Qualifier("integrationDataSource") DataSource DataSource) {
		return new EntityManagerFactoryBuilder(
			new HibernateJpaVendorAdapter(), domainJpaProperties().getProperties(), null)
			.dataSource(DataSource)
			.properties(
				domainHibernateProperties()
					.determineHibernateProperties(
						domainJpaProperties().getProperties(), new HibernateSettings()))
			.persistenceUnit(DOMAIN_PERSIST_UNIT)
			.packages(DOMAIN_BASE_PACKAGE)
			.build();
	}

	@Bean(name = DOMAIN_JPA_PROPERTIES)
	@ConfigurationProperties(prefix = "spring.jpa")
	public JpaProperties domainJpaProperties() {
		return new JpaProperties();
	}

	@Bean(name = DOMAIN_HIBERNATE_PROPERTIES)
	@ConfigurationProperties(prefix = "spring.jpa.hibernate")
	public HibernateProperties domainHibernateProperties() {
		return new HibernateProperties();
	}

	@Primary
	@Bean(name = DOMAIN_TRANSACTION_MANAGER)
	public PlatformTransactionManager domainTransactionManager(
		@Autowired @Qualifier(DOMAIN_ENTITY_MANAGER_FACTORY)
		EntityManagerFactory domainEntityManagerFactory) {
		return new JpaTransactionManager(domainEntityManagerFactory);
	}
}
