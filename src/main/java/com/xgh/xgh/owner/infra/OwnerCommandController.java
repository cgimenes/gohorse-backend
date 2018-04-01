package com.xgh.xgh.owner.infra;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xgh.xgh.owner.commandmodel.OwnerId;
import com.xgh.xgh.owner.commandmodel.commands.RegisterOwner;
import com.xgh.xgh.owner.commandmodel.commands.UpdateOwner;

@RestController
@RequestMapping("/owners")
public class OwnerCommandController {
	@Autowired
	private OwnerCommandService service;
	
	@PostMapping
	public ResponseEntity<Void> regiter(@RequestBody RegisterOwner command) {
		OwnerId id = service.registerOwner(command);
		return ResponseEntity.created(URI.create("/owners/" + id.toString())).build();
	}
	
	@PutMapping
	public ResponseEntity<Void> update(@RequestBody UpdateOwner command) {
		service.updateOwner(command);
		return ResponseEntity.noContent().build();
	}

}
