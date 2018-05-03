package com.xgh.model.query.bed;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xgh.infra.controller.BasicQueryController;

@RestController
@RequestMapping("/bed")
public class BedQueryController extends BasicQueryController<Bed, BedQueryService>{

	@Autowired
	public	BedQueryController(BedQueryService service) {
		super(service);
	}
	
}
