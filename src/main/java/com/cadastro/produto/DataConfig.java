package com.cadastro.produto;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DataConfig {

	@Bean
	public DataSource dataSource() {
		
		//Configurações de Banco
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		// instancia objeto de configuração do banco
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); // driver
		dataSource.setUrl("jdbc:mysql://localhost:3306/db_produto"); // banco
		dataSource.setUsername("root"); // usuário do banco
		dataSource.setPassword("Matheusdev.1"); // senha do db
		
		// retorna o objeto
		return dataSource;
		
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		
		// configuração do hibernate
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
		adapter.setPrepareConnection(true);
		
		// retorna objeto
		return adapter;
		
	}
	
}
