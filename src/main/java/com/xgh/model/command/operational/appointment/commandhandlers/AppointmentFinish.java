package com.xgh.model.command.operational.appointment.commandhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.model.command.operational.appointment.Appointment;
import com.xgh.model.command.operational.appointment.commands.FinishAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentFinish implements CommandHandler<FinishAppointment>  {
    private final EventStore repository;

    @Autowired
    public AppointmentFinish(EventStore repository) {
        this.repository = repository;
    }

    @Override
    public void execute(FinishAppointment command) {
        Appointment appointment = repository.pull(Appointment.class, command.getId());
        appointment.finish(command.getPrice());
        repository.push(appointment);
    }
}
