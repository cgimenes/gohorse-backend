package com.xgh.xgh.veterinary.query;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xgh.infra.repository.PagedResult;

@Service
public class VeterinaryQueryService {
	@Autowired
	private VeterinaryQueryRepository repository;

	public PagedResult<Veterinary> findAllVeterinarians(int page) {
		return repository.findAll(page);
	}
	
	public Veterinary findById(UUID id) {
		return repository.findById(id);
	}
}
