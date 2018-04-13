package com.xgh.model.query.internment;

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
@RequestMapping("/internments")
public class InternmentQueryController {
	@Autowired
	private InternmentQueryService service;

	@GetMapping
	public ResponseEntity<Page<Internment>> findAll(@RequestParam(name = "page", defaultValue = "0") int page) {
		return ResponseEntity.ok().body((Page<Internment>) service.findAll(page));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Internment> findById(@PathVariable UUID id) {
		return ResponseEntity.ok().body((Internment) service.findById(id));
	}
}
