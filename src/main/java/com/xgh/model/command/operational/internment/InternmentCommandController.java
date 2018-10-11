package com.xgh.model.command.operational.internment;

import com.xgh.buildingblocks.command.CommandBus;
import com.xgh.infra.controller.BasicCommandController;
import com.xgh.model.command.operational.internment.commands.FinishInternment;
import com.xgh.model.command.operational.internment.commands.DeleteInternment;
import com.xgh.model.command.operational.internment.commands.RegisterInternment;
import com.xgh.model.command.operational.internment.commands.UpdateInternment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internments")
public class InternmentCommandController
        extends BasicCommandController<RegisterInternment, UpdateInternment, DeleteInternment> {
    @Override
    protected String getBasePath() {
        return "/internments";
    }

    @PutMapping("/finish")
    public ResponseEntity<Void> update(@RequestBody FinishInternment command) {
        CommandBus.dispatch(command);
        return ResponseEntity.noContent().build();
    }
}