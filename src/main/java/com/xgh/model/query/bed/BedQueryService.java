package com.xgh.model.query.bed;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.xgh.infra.service.BasicQueryService;

@Service
public class BedQueryService extends BasicQueryService<Bed, BedRepository>{

	@Autowired
	public BedQueryService(BedRepository repository) {
		super(repository);
	}
}
