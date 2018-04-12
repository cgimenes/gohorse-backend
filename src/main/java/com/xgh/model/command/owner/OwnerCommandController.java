package com.xgh.model.command.owner;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xgh.buildingblocks.command.CommandBus;
import com.xgh.model.command.owner.commands.DeleteOwner;
import com.xgh.model.command.owner.commands.RegisterOwner;
import com.xgh.model.command.owner.commands.UpdateOwner;

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
    public ResponseEntity<Void> delete(@RequestBody DeleteOwner command) {
        CommandBus.dispatch(command);
        return ResponseEntity.noContent().build();
    }
}
