package com.xgh.model.command.bed;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xgh.buildingblocks.command.CommandBus;
import com.xgh.model.command.bed.commands.DeleteBed;
import com.xgh.model.command.bed.commands.RegisterBed;
import com.xgh.model.command.bed.commands.UpdateBed;

@RestController
@RequestMapping("/bed")
public class BedCommandController {

	@PostMapping
	public ResponseEntity<Void> register(@RequestBody RegisterBed command){
		CommandBus.dispatch(command);
		return ResponseEntity.created(URI.create("/bed/" + command.getId().toString())).build();
	}
	
	@PutMapping
	public ResponseEntity<Void> update(@RequestBody UpdateBed command){
		CommandBus.dispatch(command);
		return ResponseEntity.noContent().build();		
	}
	
	@DeleteMapping
	public ResponseEntity<Void> delete(@RequestBody DeleteBed command){
		CommandBus.dispatch(command);
		return ResponseEntity.noContent().build();
	}
}
