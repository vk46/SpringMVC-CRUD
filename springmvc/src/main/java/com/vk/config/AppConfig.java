/**
 * 
 */
package com.vk.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Vinay Kumar
 *
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.vk")
@EnableJpaRepositories({"com.vk.repository"})
@PropertySource("classpath:dbconfig.properties")
@EnableTransactionManagement
public class AppConfig {
	private static final Logger logger = Logger.getLogger(AppConfig.class);
	
	@Value("${connection.driver_class}")
    private String driverClass;
	
    @Value("${connection.url}")
    private String connectionUrl;
    
    @Value("${connection.username}")
    private String userName;
    
    @Value("${connection.password}")
    private String password;
    
    @Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new org.apache.commons.dbcp.BasicDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(connectionUrl);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		logger.info("DATA SOURCE CONFIGURED");
		return dataSource;
	}
	
    @Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setPersistenceProvider(new HibernatePersistenceProvider());
		lef.setDataSource(dataSource);
		lef.setJpaVendorAdapter(jpaVendorAdapter);
		lef.setPackagesToScan("com.vk");
		return lef;
	}
    
    @Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		hibernateJpaVendorAdapter.setGenerateDdl(true);
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		return hibernateJpaVendorAdapter;
	}
    
    @Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
	    return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean 
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }
}
