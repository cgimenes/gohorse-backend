package com.xgh.eventhandlers.operational;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.exceptions.ProjectionFailedException;
import com.xgh.model.command.operational.appointment.Appointment;
import com.xgh.model.command.operational.appointment.events.AppointmentWasCancelled;
import com.xgh.model.command.operational.appointment.events.AppointmentWasDeleted;
import com.xgh.model.command.operational.appointment.events.AppointmentWasFinished;
import com.xgh.model.command.operational.appointment.events.AppointmentWasRegistered;
import com.xgh.model.command.operational.appointment.events.AppointmentWasUpdated;
import com.xgh.model.query.operational.appointment.AppointmentRepository;
import com.xgh.model.query.operational.address.Address;
import com.xgh.model.query.operational.address.AddressProjector;
import com.xgh.model.query.operational.animal.Animal;
import com.xgh.model.query.operational.animal.AnimalRepository;
import com.xgh.model.query.operational.veterinary.Veterinary;
import com.xgh.model.query.operational.veterinary.VeterinaryRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentProjector implements EventHandler {
    private final EventStore eventStore;
    private final AppointmentRepository repository;
    private final AddressProjector addressProjector;
    private final AnimalRepository animalRepository;
    private final VeterinaryRepository veterinaryRepository;

    @Autowired
    public AppointmentProjector(AppointmentRepository repository, EventStore eventStore, AddressProjector addressProjector, AnimalRepository animalRepository, VeterinaryRepository veterinaryRepository) {
        this.repository = repository;
        this.eventStore = eventStore;
        this.addressProjector = addressProjector;
        this.animalRepository = animalRepository;
        this.veterinaryRepository = veterinaryRepository;
    }

    @Override
    public boolean isSubscribedTo(Event event) {
        return event instanceof AppointmentWasDeleted
                || event instanceof AppointmentWasRegistered
                || event instanceof AppointmentWasUpdated
                || event instanceof AppointmentWasFinished
                || event instanceof AppointmentWasCancelled;
    }

    @Override
    public void execute(Event event) {
        Appointment entity = eventStore.pull(Appointment.class, ((EntityEvent<?>) event).getEntityId());

        Address addressProjection = null;
        if (entity.getAddress() != null) {
            addressProjection = addressProjector.execute(entity.getAddress());
        }

        Optional<Animal> animal = animalRepository.findById(entity.getAnimal().getValue());
        Optional<Veterinary> veterinary = veterinaryRepository.findById(entity.getVeterinary().getValue());

        if (!animal.isPresent()) {
            throw new ProjectionFailedException(Animal.class.getSimpleName());
        }

        if (!veterinary.isPresent()) {
            throw new ProjectionFailedException(Veterinary.class.getSimpleName());
        }

        com.xgh.model.query.operational.appointment.Appointment projection = new com.xgh.model.query.operational.appointment.Appointment(
                entity.getId().getValue(),
                animal.get(),
                veterinary.get(),
                entity.getDateTime(),
                entity.getStatus().toString(),
                entity.getAppointmentType().toString(),
                entity.getPlace().toString(),
                addressProjection,
                entity.getPrice(),
                entity.isDeleted());

        repository.save(projection);
    }
}
