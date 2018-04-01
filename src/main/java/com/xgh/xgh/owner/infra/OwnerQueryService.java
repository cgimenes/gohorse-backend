package com.xgh.xgh.owner.infra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xgh.valueobjects.EntityId;
import com.xgh.xgh.owner.querymodel.Owner;

@Service
public class OwnerQueryService {
	@Autowired
	private OwnerQueryRepository repository;
	
	public List<Owner> findAllOwners() {
		return repository.findAll();
	}
	
	public Owner findById(EntityId id) {
		return repository.findById(id);
	}

}
