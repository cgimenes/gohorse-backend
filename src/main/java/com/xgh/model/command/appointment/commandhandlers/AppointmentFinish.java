package com.xgh.model.command.appointment.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.appointment.Appointment;
import com.xgh.model.command.appointment.commands.FinishAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentFinish implements CommandHandler<FinishAppointment> {
    private final EventStore repository;

    @Autowired
    public AppointmentFinish(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(FinishAppointment command) {
        Appointment appointment = repository.pull(Appointment.class, command.getId());
        appointment.finish();
        repository.push(appointment);
    }
}
