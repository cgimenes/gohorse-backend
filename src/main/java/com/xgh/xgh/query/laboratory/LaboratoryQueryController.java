package com.xgh.xgh.query.laboratory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xgh.xgh.query.laboratory.Laboratory;

@RestController
@RequestMapping("/laboratories")
public class LaboratoryQueryController {
    @Autowired
    private LaboratoryQueryService service;

    @GetMapping
    public ResponseEntity<List<Laboratory>> findAll() {
        List<Laboratory> laboratories = service.findAllLaboratories();
        return ResponseEntity.ok().body(laboratories);
    }
}
