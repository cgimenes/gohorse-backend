package com.xgh.model.command.appointment;

import com.xgh.infra.controller.BasicCommandController;
import com.xgh.model.command.appointment.commands.DeleteAppointment;
import com.xgh.model.command.appointment.commands.RegisterAppointment;
import com.xgh.model.command.appointment.commands.UpdateAppointment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentCommandController extends BasicCommandController<RegisterAppointment, UpdateAppointment, DeleteAppointment> {

    @Override
    protected String getBasePath() {
        return "/appointments";
    }
}
