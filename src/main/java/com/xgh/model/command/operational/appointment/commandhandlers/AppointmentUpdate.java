package com.xgh.model.command.operational.appointment.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.exceptions.EntityFieldConflictedException;
import com.xgh.model.command.operational.appointment.Appointment;
import com.xgh.model.command.operational.appointment.commands.UpdateAppointment;
import com.xgh.model.query.operational.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentUpdate implements CommandHandler<UpdateAppointment> {
    private final EventStore eventStore;
    private final AppointmentRepository repository;

    @Autowired
    public AppointmentUpdate(EventStore eventStore, AppointmentRepository repository) {
        this.eventStore = eventStore;
        this.repository = repository;
    }

    @Override
    public void execute(UpdateAppointment command) {
        if (repository.existsByDeletedFalseAndIdNotAndDateTime(command.getId().getValue(), command.getDateTime())) {
            throw new EntityFieldConflictedException(Appointment.class, "dateTime");
        }

        Appointment appointment = eventStore.pull(Appointment.class, command.getId());
        appointment.update(command.getVeterinary(), command.getDateTime(), command.getPlace(), command.getAddress());
        eventStore.push(appointment);
    }
}
