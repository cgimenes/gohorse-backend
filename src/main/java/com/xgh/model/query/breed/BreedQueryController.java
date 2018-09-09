package com.xgh.model.query.breed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/breeds")
public class BreedQueryController {
	private BreedQueryService service;

	@Autowired
    public BreedQueryController(BreedQueryService service) {
        this.service = service;
    }
    
    @GetMapping("/find")
    public ResponseEntity<Page<Breed>> findByName(@RequestParam(name = "name") String name, 
    											  @RequestParam(name = "page", defaultValue = "0") int pageNumber){
    	Page<Breed> page = this.service.findByNameContainingIgnoreCase(pageNumber,name);
        return ResponseEntity.ok().body(page);
    }
}
