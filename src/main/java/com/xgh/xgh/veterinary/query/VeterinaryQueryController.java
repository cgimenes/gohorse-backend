package com.xgh.xgh.veterinary.query;

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
@RequestMapping("/veteriniarians")
public class VeterinaryQueryController {
	@Autowired
	private VeterinaryQueryService service;

	// TODO pesquisa
	@GetMapping
	public ResponseEntity<PagedResult<Veterinary>> findAll(@RequestParam(value = "page", defaultValue = "0") int page) {
		PagedResult<Veterinary> veterinarians = service.finAllVeterinarians(page);
		return ResponseEntity.ok().body(veterinarians);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Veterinary> findById(@PathVariable UUID id){
		Veterinary veterinary = service.findById(id);
		return ResponseEntity.ok().body(veterinary);
	}
}
