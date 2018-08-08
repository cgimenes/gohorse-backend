package com.xgh.model.command.appointment.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.appointment.Appointment;
import com.xgh.model.command.appointment.commands.RegisterAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentRegistration implements CommandHandler<RegisterAppointment> {
    private final EventStore repository;

    @Autowired
    public AppointmentRegistration(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(RegisterAppointment command) {
        Appointment appointment = new Appointment();
        appointment.register(command.getId(), command.getAnimal(), command.getVeterinary(), command.getDateTime(), command.getAppointmentType(), command.getPlace(), command.getAddress());
        repository.push(appointment);
    }
}
