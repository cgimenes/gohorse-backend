package com.xgh.xgh.laboratory.command;

import org.springframework.web.bind.annotation.RestController;

import com.xgh.xgh.laboratory.command.commands.RegisterLaboratory;
import com.xgh.xgh.laboratory.command.commands.UpdateLaboratory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import java.net.URI;

// TODO tratar exceções
@RestController
@RequestMapping("/laboratories")
public class LaboratoryCommandController {
    @Autowired
    private LaboratoryCommandService service;

    @PostMapping
    public ResponseEntity<LaboratoryId> register(@RequestBody RegisterLaboratory command) {
        LaboratoryId id = service.registerLaboratory(command);
        return ResponseEntity.created(URI.create("/laboratories/" + id.toString())).build();
    }

    @PutMapping
    public ResponseEntity<LaboratoryId> update(@RequestBody UpdateLaboratory command) {
        service.updateLaboratory(command);
        return ResponseEntity.noContent().build();
    }
}
