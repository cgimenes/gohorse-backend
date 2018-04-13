package com.xgh.model.query.internment;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xgh.Constants;

@Service
public class InternmentQueryService {
	@Autowired
	private InternmentRepository repository;

	public Page<Internment> findAll(int page) {
		PageRequest request = PageRequest.of(page, Constants.PAGE_SIZE.asInteger());
		return repository.findAll(request);
	}

	public Internment findById(UUID id) {
		return repository.getOne(id);
	}
}
