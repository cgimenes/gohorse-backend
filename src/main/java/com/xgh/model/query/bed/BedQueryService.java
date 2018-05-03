package com.xgh.model.query.bed;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xgh.Constants;
import com.xgh.exceptions.EntityNotFoundException;

@Service
public class BedQueryService {

	@Autowired
	private BedRepository repository;
	
	public Page<Bed> findAll(int page){
		PageRequest request = PageRequest.of(page, Constants.PAGE_SIZE.asInteger());
		return repository.findByDeletedFalse(request);
	}
	
	public Bed findById(UUID id) {
		Optional<Bed> entity = repository.findOneByIdAndDeletedFalse(id);
		if(!entity.isPresent()){
			throw new EntityNotFoundException();
		}
		return entity.get();
	}
}
