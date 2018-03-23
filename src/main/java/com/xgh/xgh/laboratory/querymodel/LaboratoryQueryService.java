package com.xgh.xgh.laboratory.querymodel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xgh.valueobjects.EntityId;

@Service
public class LaboratoryQueryService {
    @Autowired
    private LaboratoryQueryRepository repository;

	public List<Laboratory> findAllLaboratories() {
		return repository.findAll();
	}

	public Laboratory findById(EntityId id) {
		return repository.findById(id);
	}

}
