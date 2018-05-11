package com.xgh.model.command.animal;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xgh.buildingblocks.command.CommandBus;
import com.xgh.model.command.animal.commands.RegisterAnimal;

@RestController
@RequestMapping("/animals")
public class AnimalCommandController {
	
	@PostMapping
	public ResponseEntity<Void> register(@RequestBody RegisterAnimal command){
		CommandBus.dispatch(command);
		return ResponseEntity.created(URI.create("/animals/" + command.getId().toString())).build();
	}
}
