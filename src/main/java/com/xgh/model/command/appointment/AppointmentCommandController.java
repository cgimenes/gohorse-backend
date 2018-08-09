package com.xgh.model.command.appointment;

import com.xgh.buildingblocks.command.CommandBus;
import com.xgh.infra.controller.BasicCommandController;
import com.xgh.model.command.appointment.commands.CancelAppointment;
import com.xgh.model.command.appointment.commands.DeleteAppointment;
import com.xgh.model.command.appointment.commands.FinishAppointment;
import com.xgh.model.command.appointment.commands.RegisterAppointment;
import com.xgh.model.command.appointment.commands.UpdateAppointment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentCommandController extends BasicCommandController<RegisterAppointment, UpdateAppointment, DeleteAppointment> {

    @Override
    protected String getBasePath() {
        return "/appointments";
    }

    @PutMapping("/cancel")
    public ResponseEntity<Void> update(@RequestBody CancelAppointment command) {
        CommandBus.dispatch(command);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/finish")
    public ResponseEntity<Void> update(@RequestBody FinishAppointment command) {
        CommandBus.dispatch(command);
        return ResponseEntity.noContent().build();
    }
}
