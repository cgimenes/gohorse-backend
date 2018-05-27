package com.xgh.infra.controller;

import com.xgh.buildingblocks.command.Command;
import com.xgh.buildingblocks.command.CommandBus;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class BasicCommandController<Register extends Command, Update extends Command, Delete extends Command> {
    protected abstract String getBasePath();

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody Register command) {
        CommandBus.dispatch(command);
        URI locationHeader = URI.create(String.format("%s/%s", getBasePath(), command.getId()));
        return ResponseEntity.created(locationHeader).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Update command) {
        CommandBus.dispatch(command);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody Delete command) {
        CommandBus.dispatch(command);
        return ResponseEntity.noContent().build();
    }
}
