package dz.acs.formation.web.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "dz.acs.formation.web")
@PropertySource(value = {"classpath:/jdbc.properties","classpath:/app.properties"})
@EnableTransactionManagement
@EnableWebMvc
@EnableJpaRepositories(basePackages = "dz.acs.formation.web",enableDefaultTransactions =true,
entityManagerFactoryRef = "entityManagerFactory",transactionManagerRef = "tansactionManager" )
public class AppConfig {

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.driverClassName}")	
	private String dcn;

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dmDs = new DriverManagerDataSource(url, username, password);
		dmDs.setDriverClassName(dcn);
		return dmDs;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jva = new HibernateJpaVendorAdapter();
		jva.setDatabase(Database.MYSQL);
		jva.setDatabasePlatform(org.hibernate.dialect.MySQL5Dialect.class.getCanonicalName());
		return jva;
	}
	
	@Bean
	public JpaDialect jpaDialect() {
		JpaDialect jd = new HibernateJpaDialect();		
		return jd;
	}
	
	@Bean("entityManagerFactory")
	@Autowired
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource dataSource,
			JpaDialect jpaDialect, 
			JpaVendorAdapter jpaVendorAdapter) {
		
		LocalContainerEntityManagerFactoryBean  emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setJpaDialect(jpaDialect);
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setPersistenceUnitName("pu");
		
		
		Map<String, Object> jpaPropertyMap =new HashMap<String, Object>();		
		jpaPropertyMap.put("hibernate.format_sql",Boolean.TRUE);
		jpaPropertyMap.put("hibernate.show_sql",Boolean.TRUE);
		jpaPropertyMap.put("hibernate.hbm2ddl.auto","update");
		emf.setJpaPropertyMap(jpaPropertyMap);
		return emf;
	}
	
	@Bean("tansactionManager")
	@Autowired
	public PlatformTransactionManager tansactionManager(
			DataSource dataSource,JpaDialect jpaDialect,
			EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager jpaTransactionManager= new JpaTransactionManager(entityManagerFactory);
		jpaTransactionManager.setJpaDialect(jpaDialect);
		jpaTransactionManager.setDataSource(dataSource);
		return jpaTransactionManager;		
	}

}
