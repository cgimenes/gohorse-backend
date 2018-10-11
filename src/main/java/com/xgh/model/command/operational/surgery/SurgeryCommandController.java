package com.xgh.model.command.operational.surgery;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xgh.buildingblocks.command.CommandBus;
import com.xgh.infra.controller.BasicCommandController;
import com.xgh.model.command.operational.surgery.commands.CancelSurgery;
import com.xgh.model.command.operational.surgery.commands.DeleteSurgery;
import com.xgh.model.command.operational.surgery.commands.FinishSurgery;
import com.xgh.model.command.operational.surgery.commands.RegisterSurgery;
import com.xgh.model.command.operational.surgery.commands.UpdateSurgery;

@RestController
@RequestMapping("/surgeries")
public class SurgeryCommandController extends BasicCommandController<RegisterSurgery, UpdateSurgery, DeleteSurgery> {

	@Override
	protected String getBasePath() {
		return "/surgeries";
	}
	
	@PutMapping("/cancel")
    public ResponseEntity<Void> update(@RequestBody CancelSurgery command) {
        CommandBus.dispatch(command);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/finish")
    public ResponseEntity<Void> update(@RequestBody FinishSurgery command) {
        CommandBus.dispatch(command);
        return ResponseEntity.noContent().build();
    }
}
