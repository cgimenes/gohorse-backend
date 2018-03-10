package com.xdg.xdg.laboratory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/laboratories")
public class LaboratoryCommandController {
    @Autowired
    private LaboratoryCommandService service;

    @PostMapping
    public ResponseEntity<CorId> post(@RequestBody CriarCor comando) throws SQLException {
        if (System.currentTimeMillis()%2 == 0) {
            throw new SQLException("Vixe!");
        }
        Optional<CorId> optionalCorId = service.executar(comando);
        if (optionalCorId.isPresent()) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(optionalCorId.get()).toUri();
            return ResponseEntity.created(location).build();
        }
        return ResponseEntity.badRequest().build();
    }

}
