package com.xgh.model.query.supplier;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xgh.Constants;
import com.xgh.exceptions.EntityNotFoundException;

@Service
public class SupplierQueryService {
	@Autowired
	private SupplierRepository repository;
	
	public Page<Supplier> findAll(int page) {
		PageRequest request = PageRequest.of(page, Constants.PAGE_SIZE.asInteger());
        return repository.findByDeletedFalse(request);
	}
	
	public Supplier findById(UUID id) {
		Optional<Supplier> entity = repository.findOneByIdAndDeletedFalse(id);
		if(!entity.isPresent()) {
			throw new EntityNotFoundException();
		}
		
		return entity.get();
	}

}
