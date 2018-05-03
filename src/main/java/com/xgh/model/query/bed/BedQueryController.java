package com.xgh.model.query.bed;

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
@RequestMapping("/bed")
public class BedQueryController {

	@Autowired
	private BedQueryService service;
	
	//pesquisa 
	@GetMapping
	public ResponseEntity<Page<Bed>> findAll(@RequestParam(name = "page", defaultValue = "0") int page){
		Page<Bed> bed = service.findAll(page);
		return ResponseEntity.ok().body(bed);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Bed> findById(@PathVariable UUID id){
		Bed bed = service.findById(id);
		return ResponseEntity.ok().body(bed);
	}
}
