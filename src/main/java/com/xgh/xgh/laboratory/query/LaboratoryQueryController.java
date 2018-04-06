package com.xgh.xgh.laboratory.query;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xgh.infra.repository.PagedResult;

@RestController
@RequestMapping("/laboratories")
public class LaboratoryQueryController {
    @Autowired
    private LaboratoryQueryService service;

    // TODO pesquisa
    @GetMapping
    public ResponseEntity<PagedResult<Laboratory>> findAll(@RequestParam(name="page", defaultValue="0") int page) {
        PagedResult<Laboratory> laboratories = service.findAll(page);
        return ResponseEntity.ok().body(laboratories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laboratory> findById(@PathVariable UUID id) {
        Laboratory laboratory = service.findById(id);
        return ResponseEntity.ok().body(laboratory);
    }
}
