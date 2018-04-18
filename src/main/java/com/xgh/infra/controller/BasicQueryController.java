package com.xgh.infra.controller;

import com.xgh.infra.service.BasicQueryService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public abstract class BasicQueryController<Entity, Service extends BasicQueryService<Entity, ?>> {
    private final Service service;

    public BasicQueryController(Service service) {
        this.service = service;
    }

    // TODO pesquisa
    @GetMapping
    public ResponseEntity<Page<Entity>> findAll(@RequestParam(name = "page", defaultValue = "0") int pageNumber) {
        Page<Entity> page = service.findAll(pageNumber);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entity> findById(@PathVariable UUID id) {
        Entity entity = service.findById(id);
        return ResponseEntity.ok().body(entity);
    }
}
