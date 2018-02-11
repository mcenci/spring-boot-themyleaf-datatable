package com.netsgroup.webapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netsgroup.webapp.mapper.auth.AuthenticationMapper;

@Service
@Transactional(transactionManager = "authTrManager")
public class AuthenticationService {

	private AuthenticationMapper mapper;
	
	public AuthenticationService(AuthenticationMapper mapper) {
		this.mapper = mapper;
	}
	
	public List<String> getCustomers() {
		return mapper.getCustomers();
	}
}
