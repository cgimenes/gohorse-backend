package com.xgh.xgh.laboratory.command;

import org.springframework.web.bind.annotation.RestController;

import com.xgh.buildingblocks.CommandBus;
import com.xgh.xgh.laboratory.command.commands.DeleteLaboratory;
import com.xgh.xgh.laboratory.command.commands.RegisterLaboratory;
import com.xgh.xgh.laboratory.command.commands.UpdateLaboratory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import java.net.URI;

@RestController
@RequestMapping("/laboratories")
public class LaboratoryCommandController {
    @PostMapping
    public ResponseEntity<Void> register(@RequestBody RegisterLaboratory command) {
    	CommandBus.dispatch(command);
        return ResponseEntity.created(URI.create("/laboratories/" + command.getId().toString())).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UpdateLaboratory command) {
    	CommandBus.dispatch(command);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody DeleteLaboratory command) {
    	CommandBus.dispatch(command);
        return ResponseEntity.noContent().build();
    }
}
