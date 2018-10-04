package com.xgh.model.query.operational.appointment;

import com.xgh.infra.service.BasicQueryService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentQueryService extends BasicQueryService<Appointment, AppointmentRepository> {
    @Autowired
    protected AppointmentQueryService(AppointmentRepository repository) {
        super(repository);
    }

    public List<Appointment> findByDate(LocalDate date) {
        return repository.findByDate(date);
    }

    public List<LocalDate> findDatesWithAppointmentByMonth(LocalDate month) {
        return repository.findDatesWithAppointmentByMonth(month)
                .stream()
                .map(appointment -> appointment.getDateTime().toLocalDate())
                .collect(Collectors.toList());
    }
}
