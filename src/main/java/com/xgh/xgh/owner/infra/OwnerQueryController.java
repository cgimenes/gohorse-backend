package com.xgh.xgh.owner.infra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xgh.valueobjects.EntityId;
import com.xgh.xgh.owner.querymodel.Owner;

@RestController
@RequestMapping("/owners")
public class OwnerQueryController {
	@Autowired
	private OwnerQueryService service;
	
	@GetMapping
	public ResponseEntity<List<Owner>> findAll() {
		List<Owner> owners = service.findAllOwners();
		return ResponseEntity.ok().body(owners);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Owner> findById(@PathVariable EntityId id) {
		Owner owner = service.findById(id);
		return ResponseEntity.ok().body(owner);
	}
}
