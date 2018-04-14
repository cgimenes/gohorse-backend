package com.xgh.model.command.product;

import com.xgh.buildingblocks.command.CommandBus;
import com.xgh.model.command.product.commands.DeleteProduct;
import com.xgh.model.command.product.commands.RegisterProduct;
import com.xgh.model.command.product.commands.UpdateProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductCommandController {
    @PostMapping
    public ResponseEntity<Void> register(@RequestBody RegisterProduct command) {
        CommandBus.dispatch(command);
        return ResponseEntity.created(URI.create("/products/" + command.getId().toString())).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UpdateProduct command) {
        CommandBus.dispatch(command);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody DeleteProduct command) {
        CommandBus.dispatch(command);
        return ResponseEntity.noContent().build();
    }
}
