package com.netsgroup.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netsgroup.webapp.service.AuthenticationService;
import com.netsgroup.webapp.service.WebService;

@Controller
public class TestDataController {

	private AuthenticationService authService;
	
	private WebService webService;

	public TestDataController(AuthenticationService authService, WebService webService) {
		super();
		this.authService = authService;
		this.webService = webService;
	}

	@GetMapping(value = "/auth")
	@ResponseBody
	public List<String> auth() {
		return authService.getCustomers();
	}
	
	@GetMapping(value = "/web")
	@ResponseBody
	public List<String> web() {
		return webService.getData();
	}
	
	@Value(value = "${spring.application.name}")
	private String applicationName;
	
	@GetMapping(value = {"/" , "/home"})
	public String index(Model model) {
		Map<String, Object> application = new HashMap<>();
		application.put("name", applicationName);
		application.put("customer", auth());
		application.put("users", web());
		model.addAllAttributes(application);
		return "home";
	}
}
