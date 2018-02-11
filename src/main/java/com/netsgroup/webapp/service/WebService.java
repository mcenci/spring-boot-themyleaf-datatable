package com.netsgroup.webapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netsgroup.webapp.mapper.web.WebMapper;

@Service
@Transactional(transactionManager = "webTrManager" , value = "webTrManager")
public class WebService {

	private WebMapper mapper;
	
	public WebService(WebMapper mapper) {
		this.mapper = mapper;
	}
	
	public List<String> getData() {
		return mapper.getData();
	}
}
