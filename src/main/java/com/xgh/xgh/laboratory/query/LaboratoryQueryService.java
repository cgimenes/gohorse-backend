package com.xgh.xgh.laboratory.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xgh.infra.repository.PagedResult;
import com.xgh.valueobjects.EntityId;

@Service
public class LaboratoryQueryService {
    @Autowired
    private LaboratoryQueryRepository repository;

	public PagedResult<Laboratory> findAllLaboratories(int page) {
		return repository.findAll(page);
	}

	public Laboratory findById(EntityId id) {
		return repository.findById(id);
	}

}
