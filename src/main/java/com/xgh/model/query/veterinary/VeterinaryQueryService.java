package com.xgh.model.query.veterinary;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.xgh.Constants;

@Service
public class VeterinaryQueryService {
    private final VeterinaryRepository repository;

    @Autowired
    public VeterinaryQueryService(VeterinaryRepository repository) {
        this.repository = repository;
    }

    public Page<Veterinary> findAll(int page) {
        PageRequest request = PageRequest.of(page, Constants.PAGE_SIZE.asInteger());
        return repository.findAll(request);
    }

    public Veterinary findById(UUID id) {
        return repository.getOne(id);
    }
}
