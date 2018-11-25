package com.xgh.model.query.operational.appointment;

import com.xgh.Constants;
import com.xgh.infra.service.BasicQueryService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.xgh.model.command.operational.appointment.AppointmentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public List<Appointment> findLastTwelveMonths() {
        return repository.findDatesWithAppointmentBetweenTwelveMonths();
    }

    public Page<Appointment> findByStatus(int page, AppointmentStatus status) {
        PageRequest request = PageRequest.of(page, Constants.PAGE_SIZE.asInteger());
        return this.repository.findByStatus(request,status.toString());
    }

}
