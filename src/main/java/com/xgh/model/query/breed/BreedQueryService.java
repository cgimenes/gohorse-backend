package com.xgh.model.query.breed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xgh.Constants;

@Service
public class BreedQueryService {
	private BreedRepository repository;

	@Autowired
    public BreedQueryService(BreedRepository repository) {
        this.repository = repository;
    }
    
    public Page<Breed> findByNameContainingIgnoreCase(int page, String name) {
        PageRequest request = PageRequest.of(page, Constants.PAGE_SIZE.asInteger());
        return this.repository.findByNameContainingIgnoreCase(request,name);
    }
}
