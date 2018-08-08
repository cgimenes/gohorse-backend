package com.xgh.model.query.appointment;

import com.xgh.infra.service.BasicQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentQueryService extends BasicQueryService<Appointment, AppointmentRepository> {
    @Autowired
    protected AppointmentQueryService(AppointmentRepository repository) {
        super(repository);
    }
}
