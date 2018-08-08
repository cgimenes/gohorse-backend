package com.xgh.model.query.appointment;

import com.xgh.infra.controller.BasicQueryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apppointments")
public class AppointmentQueryController extends BasicQueryController<Appointment, AppointmentQueryService> {
    @Autowired
    protected AppointmentQueryController(AppointmentQueryService service) {
        super(service);
    }
}
