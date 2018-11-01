package com.xgh.infra.controller;

import com.xgh.buildingblocks.command.EntityCommand;
import com.xgh.buildingblocks.command.CommandBus;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class BasicCommandController<RegisterT extends EntityCommand, UpdateT extends EntityCommand, DeleteT extends EntityCommand> {
    protected abstract String getBasePath();

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody RegisterT command) {
        CommandBus.dispatch(command);
        URI locationHeader = URI.create(String.format("%s/%s", getBasePath(), command.getId()));
        return ResponseEntity.created(locationHeader).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UpdateT command) {
        CommandBus.dispatch(command);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody DeleteT command) {
        CommandBus.dispatch(command);
        return ResponseEntity.noContent().build();
    }
}
