package com.xgh.model.veterinary.command;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xgh.buildingblocks.command.CommandBus;
import com.xgh.model.veterinary.command.commands.DeleteVeterinary;
import com.xgh.model.veterinary.command.commands.RegisterVeterinary;
import com.xgh.model.veterinary.command.commands.UpdateVeterinary;

@RestController
@RequestMapping("/veterinarians")
public class VeterinaryCommandController {
	@PostMapping
	public ResponseEntity<Void> register(@RequestBody RegisterVeterinary command) {
		CommandBus.dispatch(command);
		return ResponseEntity.created(URI.create("/veterinarians/" + command.getId().toString())).build();
	}

	@PutMapping
	public ResponseEntity<Void> update(@RequestBody UpdateVeterinary command) {
		CommandBus.dispatch(command);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping
	public ResponseEntity<Void> delete(@RequestBody DeleteVeterinary command) {
		CommandBus.dispatch(command);
		return ResponseEntity.noContent().build();
	}
}
