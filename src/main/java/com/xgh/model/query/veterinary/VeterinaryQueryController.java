package com.xgh.model.query.veterinary;

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
@RequestMapping("/veterinarians")
public class VeterinaryQueryController {
    private final VeterinaryQueryService service;

    @Autowired
    public VeterinaryQueryController(VeterinaryQueryService service) {
        this.service = service;
    }

    // TODO pesquisa
    @GetMapping
    public ResponseEntity<Page<Veterinary>> findAll(@RequestParam(name = "page", defaultValue = "0") int page) {
        Page<Veterinary> veterinarians = service.findAll(page);
        return ResponseEntity.ok().body(veterinarians);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veterinary> findById(@PathVariable UUID id) {
        Veterinary veterinary = service.findById(id);
        return ResponseEntity.ok().body(veterinary);
    }
}
