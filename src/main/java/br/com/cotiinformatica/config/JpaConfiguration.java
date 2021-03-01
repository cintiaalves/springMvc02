package br.com.cotiinformatica.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = { "br.com.cotiinformatica" })
@EnableTransactionManagement
public class JpaConfiguration {
	
	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory() {

		// método que irá configurar as propriedades de conexão do banco de dados
		// que foram mapeadas no arquivo /META-INF/persistence.xml

		LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitName("conexao");

		return factoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory factory) {
		
		//método para habilitar as operações de transação no banco de dados
		//INSERT, UPDATE ou DELETE
		
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(factory);

		return transactionManager;
	}

}
