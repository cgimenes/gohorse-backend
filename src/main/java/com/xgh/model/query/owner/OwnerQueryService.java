package com.xgh.model.query.owner;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xgh.Constants;

@Service
public class OwnerQueryService {
	@Autowired
	private OwnerRepository repository;
	
	public Page<Owner> findAll(int page) {
		PageRequest request = PageRequest.of(page, Constants.PAGE_SIZE.asInteger());
        return repository.findAllNotDeleted(request);
	}
	
	public Owner findById(UUID id) {
		return repository.getOneNotDeleted(id);
	}

}
