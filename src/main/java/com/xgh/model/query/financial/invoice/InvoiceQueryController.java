package com.xgh.model.query.financial.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoices")
public class InvoiceQueryController {
    protected final InvoiceQueryService service;

    @Autowired
    protected InvoiceQueryController(InvoiceQueryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<Invoice>> findAll(@RequestParam(name = "page", defaultValue = "0") int pageNumber) {
        Page<Invoice> page = service.findAllCreated(pageNumber);
        return ResponseEntity.ok().body(page);
    }
}
