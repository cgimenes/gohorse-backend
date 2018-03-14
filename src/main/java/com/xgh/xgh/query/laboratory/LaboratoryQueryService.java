package com.xgh.xgh.query.laboratory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LaboratoryQueryService {
    @Autowired
    private LaboratoryQueryRepository repository;

	public List<Laboratory> findAllLaboratories() {
		return repository.findAll();
	}

}
