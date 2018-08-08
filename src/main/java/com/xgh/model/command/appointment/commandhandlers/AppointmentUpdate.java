package com.xgh.model.command.appointment.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.appointment.Appointment;
import com.xgh.model.command.appointment.commands.UpdateAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentUpdate implements CommandHandler<UpdateAppointment> {
    private final EventStore repository;

    @Autowired
    public AppointmentUpdate(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(UpdateAppointment command) {
        Appointment appointment = repository.pull(Appointment.class, command.getId());
        appointment.update(command.getVeterinary(), command.getDateTime(), command.getPlace(), command.getAddress());
        repository.push(appointment);
    }
}
