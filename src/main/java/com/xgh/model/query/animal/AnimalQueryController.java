package com.xgh.model.query.animal;

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
@RequestMapping("/animals")
public class AnimalQueryController {
	@Autowired
	private AnimalQueryService service;

	@GetMapping
	public ResponseEntity<Page<Animal>> findAll(@RequestParam(name = "page", defaultValue = "0")int page){
		Page<Animal> animals = service.findAll(page);
		return ResponseEntity.ok().body(animals);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Animal> findById(@PathVariable UUID id){
		Animal animal = service.findById(id);
		return ResponseEntity.ok().body(animal);
	}
}
