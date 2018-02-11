package com.netsgroup.webapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;

@RestController
@RequestMapping("ajax")
public class AjaxController {

	@RequestMapping(value = "/persons")
	public @ResponseBody
	DatatablesResponse<Object> findAllForDataTablesFullSpring(@DatatablesParams DatatablesCriterias criterias) {
		DataSet<Object> dataSet = new DataSet<>(null, 1L, 1L);
		return DatatablesResponse.build(dataSet, criterias);
	}

}
