package com.xgh.model.query.supplier;

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
@RequestMapping("/suppliers")
public class SupplierQueryController {
    @Autowired
    private SupplierQueryService service;

    @GetMapping
    public ResponseEntity<Page<Supplier>> findAll(@RequestParam(name = "page", defaultValue = "0") int page) {
        Page<Supplier> suppliers = service.findAll(page);
        return ResponseEntity.ok().body(suppliers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> findById(@PathVariable UUID id) {
        Supplier supplier = service.findById(id);
        return ResponseEntity.ok().body(supplier);
    }
}
