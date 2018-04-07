package com.xgh.xgh.veterinary.query;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xgh.Constant;

@Service
public class VeterinaryQueryService {
	@Autowired
	private VeterinaryQueryRepository repository;

	public Page<Veterinary> findAll(int page) {
        PageRequest request = PageRequest.of(page, Constant.PAGE_SIZE.asInteger());
        return repository.findAll(request);
	}
	
	public Veterinary findById(UUID id) {
		return repository.getOne(id);
	}
}
