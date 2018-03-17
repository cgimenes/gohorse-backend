package com.xgh.xgh.laboratory.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xgh.buildingblocks.EntityId;

@Component
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
