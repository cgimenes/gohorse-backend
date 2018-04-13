package com.xgh.model.command.internment;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xgh.buildingblocks.command.CommandBus;
import com.xgh.model.command.internment.commands.DeleteInternment;
import com.xgh.model.command.internment.commands.RegisterInternment;
import com.xgh.model.command.internment.commands.UpdateInternment;

@RestController
@RequestMapping("/internments")
public class InternmentCommandController {
	@PostMapping
	public ResponseEntity<Void> register(@RequestBody RegisterInternment command) {
		CommandBus.dispatch(command);
		return ResponseEntity.created(URI.create("/internments/" + command.getId().toString())).build();
	}

	@PutMapping
	public ResponseEntity<Void> update(@RequestBody UpdateInternment command) {
		CommandBus.dispatch(command);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping
	public ResponseEntity<Void> delete(@RequestBody DeleteInternment command) {
		CommandBus.dispatch(command);
		return ResponseEntity.noContent().build();
	}

}