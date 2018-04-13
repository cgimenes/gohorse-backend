package com.xgh.model.query.laboratory;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xgh.Constants;

@Service
public class LaboratoryQueryService {
    @Autowired
    private LaboratoryRepository repository;

    public Page<Laboratory> findAll(int page) {
        PageRequest request = PageRequest.of(page, Constants.PAGE_SIZE.asInteger());
        return repository.findAllNotDeleted(request);
    }
    
	public Laboratory findById(UUID id) {
		return repository.getOneNotDeleted(id);
	}
}
