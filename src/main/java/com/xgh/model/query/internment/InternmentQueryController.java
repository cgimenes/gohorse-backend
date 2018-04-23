package com.xgh.model.query.internment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xgh.infra.controller.BasicQueryController;

@RestController
@RequestMapping("/internments")
public class InternmentQueryController extends BasicQueryController<Internment, InternmentQueryService> {
	@Autowired
	protected InternmentQueryController(InternmentQueryService service) {
		super(service);
	}
}
