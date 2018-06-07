package com.xgh.infra.controller;

import com.xgh.infra.service.BasicQueryService;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class BasicQueryController<EntityT, ServiceT extends BasicQueryService<EntityT, ?>> {
    private final ServiceT service;

    protected BasicQueryController(ServiceT service) {
        this.service = service;
    }

    // TODO pesquisa
    @GetMapping
    public ResponseEntity<Page<EntityT>> findAll(@RequestParam(name = "page", defaultValue = "0") int pageNumber) {
        Page<EntityT> page = service.findAll(pageNumber);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityT> findById(@PathVariable UUID id) {
        EntityT entity = service.findById(id);
        return ResponseEntity.ok().body(entity);
    }
}
