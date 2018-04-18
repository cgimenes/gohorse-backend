package com.xgh.infra.service;

import com.xgh.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// TODO logar
public abstract class BasicQueryService<Entity, Repository extends JpaRepository<Entity, UUID>> {
    private final Repository repository;

    public BasicQueryService(Repository repository) {
        this.repository = repository;
    }

    public Page<Entity> findAll(int page) {
        PageRequest request = PageRequest.of(page, Constants.PAGE_SIZE.asInteger());
        return repository.findAll(request);
    }

    public Entity findById(UUID id) {
        return repository.getOne(id);
    }
}
