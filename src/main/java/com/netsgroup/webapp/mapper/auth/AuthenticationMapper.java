package com.netsgroup.webapp.mapper.auth;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface AuthenticationMapper {

	@Transactional
	public List<String> getCustomers();
	
}
