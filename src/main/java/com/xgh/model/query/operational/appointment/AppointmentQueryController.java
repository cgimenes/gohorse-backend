package com.xgh.model.query.operational.appointment;

import com.xgh.infra.controller.BasicQueryController;
import java.time.LocalDate;
import java.util.List;
import com.xgh.model.command.operational.appointment.AppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/lasttwelvemonths")
    public ResponseEntity<List<Appointment>> findLastTwelveMonths() {
        List<Appointment> appointments = this.service.findLastTwelveMonths();
        return ResponseEntity.ok().body(appointments);
    }

    @GetMapping("/find")
    public ResponseEntity<Page<Appointment>> findByStatus(@RequestParam(name = "status") AppointmentStatus type,
                                                          @RequestParam(name = "page", defaultValue = "0") int pageNumber) {
        Page<Appointment> page = this.service.findByStatus(pageNumber, type);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/{year}/{month}/{day}")
    protected List<Appointment> findByDate(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day) {
        return service.findByDate(LocalDate.of(year, month, day));
    }
}
