package com.netsgroup.webapp.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(mode = AdviceMode.PROXY)
public class AuthenticationDatasourceConfiguration {

	@Primary
	@Bean(name = "authDatasource")
	@ConfigurationProperties(prefix = "auth.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = "authTrManager")
    public DataSourceTransactionManager authTrManager(@Qualifier(value = "authDatasource") DataSource dataSource) {
        DataSourceTransactionManager dstm = new DataSourceTransactionManager(dataSource);
        return dstm;
    }
		
	@Primary
	@Bean(name = "sqlSessionFactoryAuth")
	public FactoryBean<SqlSessionFactory> sqlSessionFactoryAuth(@Qualifier(value = "authDatasource") DataSource dataSource){
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(dataSource);
		return ssfb;
	}
	
	@Bean("auth")
    public MapperScannerConfigurer mapperScannerConfigurer2() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.netsgroup.webapp.mapper.auth");
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryAuth");
        return configurer;
    }
	
}