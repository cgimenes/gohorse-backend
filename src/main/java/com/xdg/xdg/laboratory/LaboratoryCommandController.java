package com.xdg.xdg.laboratory;

import com.xdg.xdg.laboratory.commands.RegisterLaboratory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import java.net.URI;

@RestController
@RequestMapping("/laboratories")
public class LaboratoryCommandController {
    @Autowired
    private LaboratoryCommandService service;

    @PostMapping
    public ResponseEntity<LaboratoryId> post(@RequestBody RegisterLaboratory command) {
        LaboratoryId id = service.createLaboratory(command);
        return ResponseEntity.created(URI.create("/laboratories/" + id.toString())).build();
    }
}
