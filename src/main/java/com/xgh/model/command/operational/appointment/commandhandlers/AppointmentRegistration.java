package com.xgh.model.command.operational.appointment.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.exceptions.EntityFieldConflictedException;
import com.xgh.model.command.operational.appointment.Appointment;
import com.xgh.model.command.operational.appointment.commands.RegisterAppointment;
import com.xgh.model.query.operational.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentRegistration implements CommandHandler<RegisterAppointment> {
    private final EventStore eventStore;
    private final AppointmentRepository repository;

    @Autowired
    public AppointmentRegistration(EventStore eventStore, AppointmentRepository repository) {
        this.eventStore = eventStore;
        this.repository = repository;
    }

    @Override
    public void execute(RegisterAppointment command) {
        if (repository.existsByDateTime(command.getDateTime())) {
            throw new EntityFieldConflictedException(Appointment.class, "dateTime");
        }

        Appointment appointment = new Appointment();
        appointment.register(command.getId(), command.getAnimal(), command.getVeterinary(), command.getDateTime(), command.getAppointmentType(), command.getPlace(), command.getAddress());
        eventStore.push(appointment);
    }
}
