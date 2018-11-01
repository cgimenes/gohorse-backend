package com.xgh.eventhandlers.operational;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.exceptions.ProjectionFailedException;
import com.xgh.model.command.operational.surgery.Surgery;
import com.xgh.model.command.operational.surgery.events.SurgeryWasDeleted;
import com.xgh.model.command.operational.surgery.events.SurgeryWasRegistered;
import com.xgh.model.command.operational.surgery.events.SurgeryWasUpdated;
import com.xgh.model.query.operational.animal.Animal;
import com.xgh.model.query.operational.animal.AnimalRepository;
import com.xgh.model.query.operational.appointment.Appointment;
import com.xgh.model.query.operational.appointment.AppointmentRepository;
import com.xgh.model.query.operational.surgery.SurgeryRepository;
import com.xgh.model.query.operational.veterinary.Veterinary;
import com.xgh.model.query.operational.veterinary.VeterinaryRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurgeryProjector implements EventHandler {
    private final EventStore eventStore;
    private final SurgeryRepository repository;
    private final AnimalRepository animalRepository;
    private final VeterinaryRepository veterinaryRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public SurgeryProjector(SurgeryRepository repository, EventStore eventStore, AnimalRepository animalRepository,
                            VeterinaryRepository veterinaryRepository, AppointmentRepository appointmentRepository) {
        this.repository = repository;
        this.eventStore = eventStore;
        this.animalRepository = animalRepository;
        this.veterinaryRepository = veterinaryRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public boolean isSubscribedTo(Event event) {
        return event instanceof SurgeryWasDeleted || event instanceof SurgeryWasRegistered
                || event instanceof SurgeryWasUpdated;
    }

    @Override
    public void execute(Event event) {
        Surgery entity = eventStore.pull(Surgery.class, ((EntityEvent<?>) event).getEntityId());

        Optional<Animal> animal = animalRepository.findById(entity.getAnimal().getValue());
        Optional<Appointment> appointment = appointmentRepository.findById(entity.getAppointment().getValue());
        Optional<Veterinary> veterinary = veterinaryRepository.findById(entity.getVeterinary().getValue());

        if (!animal.isPresent()) {
            throw new ProjectionFailedException(Animal.class.getSimpleName());
        }

        if (!appointment.isPresent()) {
            throw new ProjectionFailedException(Appointment.class.getSimpleName());
        }

        if (!veterinary.isPresent()) {
            throw new ProjectionFailedException(Veterinary.class.getSimpleName());
        }

        com.xgh.model.query.operational.surgery.Surgery projection = new com.xgh.model.query.operational.surgery.Surgery(
                entity.getId().getValue(), animal.get(), veterinary.get(),
                appointment.get(), entity.isDeleted(), entity.getDateTime(), entity.getSurgeryType(),
                entity.getNotes(), entity.getStatus().toString());

        repository.save(projection);
    }
}
