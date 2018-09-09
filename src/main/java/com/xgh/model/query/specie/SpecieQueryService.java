package com.xgh.model.query.specie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xgh.Constants;

@Service
public class SpecieQueryService {
	private SpecieRepository repository;
	
	@Autowired
    public SpecieQueryService(SpecieRepository repository) {
        this.repository = repository;
    }
    
    public Page<Specie> findByNameContainingIgnoreCase(int page, String name) {
        PageRequest request = PageRequest.of(page, Constants.PAGE_SIZE.asInteger());
        return this.repository.findByNameContainingIgnoreCase(request,name);
    }
}
