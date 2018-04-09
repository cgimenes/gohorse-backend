package com.xgh.xgh.owner.query;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xgh.Constant;

@Service
public class OwnerQueryService {
	@Autowired
	private OwnerQueryRepository repository;
	
	public Page<Owner> findAll(int page) {
		PageRequest request = PageRequest.of(page, Constant.PAGE_SIZE.asInteger());
        return repository.findAll(request);
	}
	
	public Owner findById(UUID id) {
		return repository.getOne(id);
	}

}
