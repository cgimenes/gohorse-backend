package com.xgh.test.model.query.operational.appointment;

import com.xgh.model.query.operational.appointment.Appointment;
import com.xgh.model.query.operational.appointment.AppointmentRepository;
import com.xgh.test.model.query.operational.address.AddressSampleData;
import com.xgh.test.model.query.operational.animal.AnimalSampleData;
import com.xgh.test.model.query.operational.veterinary.VeterinarySampleData;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("QueryAppointmentSampleData")
public class AppointmentSampleData {
    private final AppointmentRepository repository;
    private final AnimalSampleData animalSampleData;
    private final VeterinarySampleData veterinarySampleData;
    private final AddressSampleData addressSampleData;

    @Autowired
    protected AppointmentSampleData(AppointmentRepository repository, AnimalSampleData animalSampleData, VeterinarySampleData veterinarySampleData, AddressSampleData addressSampleData) {
        this.repository = repository;
        this.animalSampleData = animalSampleData;
        this.veterinarySampleData = veterinarySampleData;
        this.addressSampleData = addressSampleData;
    }

    public Appointment getSample() {
        Appointment entity = new Appointment(
                UUID.randomUUID(),
                animalSampleData.getSample(),
                veterinarySampleData.getSample(),
                LocalDateTime.of(2012, 12, 12, 10, 10, 10),
                "SCHEDULED",
                "FIRST",
                "OTHER",
                addressSampleData.getSample(),
                null,
                false);
        repository.save(entity);
        return entity;
    }
}
