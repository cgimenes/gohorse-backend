package com.xgh.model.command.appointment.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.appointment.Appointment;
import com.xgh.model.command.appointment.commands.CancelAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentCancellation implements CommandHandler<CancelAppointment> {
    private final EventStore repository;

    @Autowired
    public AppointmentCancellation(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(CancelAppointment command) {
        Appointment appointment = repository.pull(Appointment.class, command.getId());
        appointment.cancel();
        repository.push(appointment);
    }
}
