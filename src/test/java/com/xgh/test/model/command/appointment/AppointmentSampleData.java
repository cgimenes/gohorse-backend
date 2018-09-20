package com.xgh.test.model.command.appointment;

import com.xgh.buildingblocks.EventStore;
import com.xgh.model.command.appointment.Appointment;
import com.xgh.model.command.appointment.AppointmentId;
import com.xgh.model.command.appointment.AppointmentPlace;
import com.xgh.model.command.appointment.AppointmentType;
import com.xgh.test.model.command.animal.AnimalSampleData;
import com.xgh.test.model.command.veterinary.VeterinarySampleData;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentSampleData {
    @Autowired
    private EventStore eventStore;

    @Autowired
    private VeterinarySampleData veterinarySampleData;

    @Autowired
    private AnimalSampleData animalSampleData;

    public Appointment getSample() {
        Appointment appointment = new Appointment();
        appointment.register(new AppointmentId(),
                animalSampleData.getSample().getId(),
                veterinarySampleData.getSample().getId(),
                LocalDateTime.now(),
                AppointmentType.FIRST,
                AppointmentPlace.CLINIC,
                null);
        eventStore.push(appointment);
        return appointment;
    }
}
