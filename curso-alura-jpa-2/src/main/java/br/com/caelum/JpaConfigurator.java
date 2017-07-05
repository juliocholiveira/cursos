package br.com.caelum;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
public class JpaConfigurator {

	@Bean
	public DataSource getDataSource() throws PropertyVetoException {
		
	    /*
	     * Esta abordagem não serve para o pool de conexões uma vez que para toda requisição
	     * é aberto um EntityManager.
	     * 
	     * DriverManagerDataSource dataSource = new DriverManagerDataSource();

	    dataSource.setDriverClassName("org.postgresql.Driver");
	    dataSource.setUrl("jdbc:postgresql://localhost:5432/curso_alura_jpa_2");
	    dataSource.setUsername("postgres");
	    dataSource.setPassword("123456");*/
	    
		/* Mudamos o dataSource para a classe ComboPooledDataSource, assim habilitamos
		 * o pool de conexões */
	    ComboPooledDataSource dataSource = new ComboPooledDataSource();
	    
	    // Configuração de acesso ao banco
	    dataSource.setDriverClass("org.postgresql.Driver");
	    dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/curso_alura_jpa_2");
	    dataSource.setUser("postgres");
	    dataSource.setPassword("123456");
	    
	    // Configuração do pool
	    // Página da documentação http://www.mchange.com/projects/c3p0/
	    // Esta configuração já abre o pool com 5 conexões 
	    dataSource.setMinPoolSize(5);
	    // Define o nr máximo de conexões. O padrão são 15
	    dataSource.setMaxPoolSize(20);
	    // Habilita o uso de Threads. Isso melhora o desempenho
	    dataSource.setNumHelperThreads(50);
	    // Precisamos ensinar o pool a matar as conexões que ficam ociosas 
	    // por muito tempo, eliminando o risco de escolher uma conexão quebrada.
	    // Aqui a cada 340 segundos testamos as conexões ociosas
	    dataSource.setIdleConnectionTestPeriod(340);

	    return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactory.setPackagesToScan("br.com.caelum");
		entityManagerFactory.setDataSource(dataSource);

		entityManagerFactory
				.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		Properties props = new Properties();

		props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		
		/* Habilita o uso de cache de segundo nível.
		 * Site da documentação: 
		 * https://docs.jboss.org/hibernate/orm/3.3/reference/en/html/performance.html#performance-cache
		 * Agora basta anotar as classes e os relacionamentos com
		 * @Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
		 */
		props.setProperty("hibernate.cache.use_second_level_cache", "true");
		props.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
		// Habilitar o uso de query cache. Para habilitar a query deve-se usar 
		// esse código typedQuery.setHint("org.hibernate.cacheable", "true");
		props.setProperty("hibernate.cache.use_query_cache", "true");

		entityManagerFactory.setJpaProperties(props);
		return entityManagerFactory;
	}

	@Bean
	public JpaTransactionManager getTransactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}

}
