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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(mode = AdviceMode.PROXY)
//@MapperScan(basePackages = "com.netsgroup.webapp.mapper.web")
public class WebDatasourceConfiguration {

	@Bean(name = "webDataSource")
	@ConfigurationProperties(prefix = "web.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "webTrManager")
	public DataSourceTransactionManager webTrManager(@Qualifier(value = "webDataSource") DataSource dataSource) {
		DataSourceTransactionManager dstm = new DataSourceTransactionManager(dataSource);
		return dstm;
	}

	@Bean(name = "sqlSessionFactoryWeb")
	public FactoryBean<SqlSessionFactory> sqlSessionFactoryWeb(@Qualifier(value = "webDataSource") DataSource dataSource){
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(dataSource);
		return ssfb;
	}
	
	@Bean(name = "web")
    public MapperScannerConfigurer mapperScannerConfigurer2() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.netsgroup.webapp.mapper.web");
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryWeb");
        return configurer;
    }
}