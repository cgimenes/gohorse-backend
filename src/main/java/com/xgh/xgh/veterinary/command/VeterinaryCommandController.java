package com.xgh.xgh.veterinary.command;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xgh.xgh.veterinary.command.commands.RegisterVeterinary;
import com.xgh.xgh.veterinary.command.commands.UpdateVeterinary;

// TODO logar commandos
@RestController
@RequestMapping("/veterinarians")
public class VeterinaryCommandController {
	@Autowired
	private VeterinaryCommandService service;

	@PostMapping
	public ResponseEntity<VeterinaryId> register(@RequestBody RegisterVeterinary command) {
		VeterinaryId id = service.registerVeterinary(command);
		return ResponseEntity.created(URI.create("/veterinarians/" + id.toString())).build();
	}

	@PutMapping
	public ResponseEntity<VeterinaryId> update(@RequestBody UpdateVeterinary command) {
		service.updateVeterinary(command);
		return ResponseEntity.noContent().build();
	}

}
