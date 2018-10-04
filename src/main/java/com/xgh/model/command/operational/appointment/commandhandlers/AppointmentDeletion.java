package com.xgh.model.command.operational.appointment.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.operational.appointment.Appointment;
import com.xgh.model.command.operational.appointment.commands.DeleteAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentDeletion implements CommandHandler<DeleteAppointment> {
    private final EventStore repository;

    @Autowired
    public AppointmentDeletion(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(DeleteAppointment command) {
        Appointment appointment = repository.pull(Appointment.class, command.getId());
        appointment.delete();
        repository.push(appointment);
    }
}
