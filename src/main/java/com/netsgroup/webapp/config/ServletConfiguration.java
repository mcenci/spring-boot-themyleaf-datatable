package com.netsgroup.webapp.config;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.dandelion.core.web.DandelionFilter;
import com.github.dandelion.core.web.DandelionServlet;

@Configuration
public class ServletConfiguration {

	@Bean
	public ServletRegistrationBean servletRegistrationBean(){
	    return new ServletRegistrationBean(new DandelionServlet(),"/dandelion-assets/*");
	}
	
	@Bean
	public FilterRegistrationBean someFilterRegistration() {
	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(dandelionFilter());
	    registration.addUrlPatterns("/*");
	    registration.setName("dandelionFilter");
	    registration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD,
	    		DispatcherType.INCLUDE, DispatcherType.ERROR);
	    registration.setOrder(1);
	    return registration;
	} 

	public Filter dandelionFilter() {
	    return new DandelionFilter();
	}
}
