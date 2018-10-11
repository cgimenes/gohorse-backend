package com.xgh.test.model.command.operational.surgery;

import com.xgh.buildingblocks.EventStore;
import com.xgh.model.command.operational.surgery.Surgery;
import com.xgh.model.command.operational.surgery.SurgeryId;
import com.xgh.model.command.operational.veterinary.VeterinaryId;
import com.xgh.test.model.command.operational.animal.AnimalSampleData;
import com.xgh.test.model.command.operational.appointment.AppointmentSampleData;
import com.xgh.test.model.command.operational.veterinary.VeterinarySampleData;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurgerySampleData {
    @Autowired
    private EventStore eventStore;

    @Autowired
    private VeterinarySampleData veterinarySampleData;

    @Autowired
    private AnimalSampleData animalSampleData;

    @Autowired
    private AppointmentSampleData appointmentSampleData;

    public Surgery getSample() {
        VeterinaryId veterinary = veterinarySampleData.getSample().getId();
        Surgery surgery = new Surgery();
        surgery.register(new SurgeryId(), animalSampleData.getSample().getId(), veterinary, LocalDateTime.now(),
                "Ortopédica", null, appointmentSampleData.getSample().getId());
        eventStore.push(surgery);
        return surgery;
    }
}
