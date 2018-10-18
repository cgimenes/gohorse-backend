package com.xgh.model.command.financial.invoice;

import com.xgh.buildingblocks.command.CommandBus;
import com.xgh.model.command.financial.invoice.commands.PayInvoice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/invoices")
@RestController
public class InvoiceCommandController {
    @PostMapping("/pay")
    public ResponseEntity<Void> register(@RequestBody PayInvoice command) {
        CommandBus.dispatch(command);
        return ResponseEntity.noContent().build();
    }
}
