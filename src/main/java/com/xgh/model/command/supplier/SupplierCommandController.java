package com.xgh.model.command.supplier;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xgh.buildingblocks.command.CommandBus;
import com.xgh.model.command.supplier.commands.DeleteSupplier;
import com.xgh.model.command.supplier.commands.RegisterSupplier;
import com.xgh.model.command.supplier.commands.UpdateSupplier;

@RestController
@RequestMapping("/suppliers")
public class SupplierCommandController {
    @PostMapping
    public ResponseEntity<Void> register(@RequestBody RegisterSupplier command) {
        CommandBus.dispatch(command);
        return ResponseEntity.created(URI.create("/suppliers/" + command.getId().toString())).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UpdateSupplier command) {
        CommandBus.dispatch(command);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody DeleteSupplier command) {
        CommandBus.dispatch(command);
        return ResponseEntity.noContent().build();
    }
}
