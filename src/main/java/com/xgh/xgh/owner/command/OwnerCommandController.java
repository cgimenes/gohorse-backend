package com.xgh.xgh.owner.command;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xgh.buildingblocks.CommandBus;
import com.xgh.xgh.laboratory.command.commands.DeleteLaboratory;
import com.xgh.xgh.owner.command.commands.RegisterOwner;
import com.xgh.xgh.owner.command.commands.UpdateOwner;

@RestController
@RequestMapping("/owners")
public class OwnerCommandController {
	@PostMapping
	public ResponseEntity<Void> register(@RequestBody RegisterOwner command) {
		CommandBus.dispatch(command);
		return ResponseEntity.created(URI.create("/owners/" + command.getId().toString())).build();
	}
	
	@PutMapping
	public ResponseEntity<Void> update(@RequestBody UpdateOwner command) {
		CommandBus.dispatch(command);
		return ResponseEntity.noContent().build();
	}
	
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody DeleteLaboratory command) {
    	CommandBus.dispatch(command);
        return ResponseEntity.noContent().build();
    }
}
