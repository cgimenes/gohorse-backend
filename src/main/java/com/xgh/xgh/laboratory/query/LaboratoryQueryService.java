package com.xgh.xgh.laboratory.query;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xgh.infra.repository.PagedResult;

@Service
public class LaboratoryQueryService {
    @Autowired
    private LaboratoryQueryRepository repository;

	public PagedResult<Laboratory> findAllLaboratories(int page) {
		return repository.findAll(page);
	}

	public Laboratory findById(UUID id) {
		return repository.findById(id);
	}

}
