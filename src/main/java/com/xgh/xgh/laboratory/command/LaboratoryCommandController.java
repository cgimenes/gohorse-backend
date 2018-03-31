package com.xgh.xgh.laboratory.command;

import org.springframework.web.bind.annotation.RestController;

import com.xgh.xgh.laboratory.command.commands.DeleteLaboratory;
import com.xgh.xgh.laboratory.command.commands.RegisterLaboratory;
import com.xgh.xgh.laboratory.command.commands.UpdateLaboratory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import java.net.URI;

// TODO logar commandos
@RestController
@RequestMapping("/laboratories")
public class LaboratoryCommandController {
    @Autowired
    private LaboratoryCommandService service;

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody RegisterLaboratory command) {
        LaboratoryId id = service.registerLaboratory(command);
        return ResponseEntity.created(URI.create("/laboratories/" + id.toString())).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UpdateLaboratory command) {
        service.updateLaboratory(command);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody DeleteLaboratory command) {
        service.deleteLaboratory(command);
        return ResponseEntity.noContent().build();
    }
}
