package br.com.casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JPAConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryManager = new LocalContainerEntityManagerFactoryBean();
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
		// define a implementação do JPA
		factoryManager.setJpaVendorAdapter(vendorAdapter);
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("postgres");
		dataSource.setPassword("123456");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/casadocodigo");
		dataSource.setDriverClassName("org.postgresql.Driver");
		
		/*dataSource.setUsername("webapp");
		dataSource.setPassword("12345678");
		dataSource.setUrl("jdbc:mysql://localhost/casadocodigo");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");*/
		
		factoryManager.setDataSource(dataSource);
		
		Properties props = new Properties();
		//props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "update");
		
		factoryManager.setJpaProperties(props);
		
		factoryManager.setPackagesToScan("br.com.casadocodigo.loja.models");
		
		return factoryManager;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf){
		return new JpaTransactionManager(emf);
	}

}
