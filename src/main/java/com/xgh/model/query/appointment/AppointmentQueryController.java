package com.xgh.model.query.appointment;

import com.xgh.infra.controller.BasicQueryController;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentQueryController extends BasicQueryController<Appointment, AppointmentQueryService> {
    @Autowired
    protected AppointmentQueryController(AppointmentQueryService service) {
        super(service);
    }

    @GetMapping("/{year}/{month}")
    protected List<LocalDate> findDatesWithAppointmentByMonth(@PathVariable Integer year, @PathVariable Integer month) {
        return service.findDatesWithAppointmentByMonth(LocalDate.of(year, month, 1));
    }

    @GetMapping("/{year}/{month}/{day}")
    protected List<Appointment> findByDate(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day) {
        return service.findByDate(LocalDate.of(year, month, day));
    }
}
