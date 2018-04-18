package com.xgh.model.query.owner;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owners")
public class OwnerQueryController {
    private final OwnerQueryService service;

    @Autowired
    public OwnerQueryController(OwnerQueryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<Owner>> findAll(@RequestParam(name = "page", defaultValue = "0") int page) {
        Page<Owner> owners = service.findAll(page);
        return ResponseEntity.ok().body(owners);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> findById(@PathVariable UUID id) {
        Owner owner = service.findById(id);
        return ResponseEntity.ok().body(owner);
    }
}
