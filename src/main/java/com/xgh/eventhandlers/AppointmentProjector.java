package com.xgh.eventhandlers;

import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.exceptions.ProjectionFailedException;
import com.xgh.model.command.appointment.Appointment;
import com.xgh.model.command.appointment.events.AppointmentWasDeleted;
import com.xgh.model.command.appointment.events.AppointmentWasRegistered;
import com.xgh.model.command.appointment.events.AppointmentWasUpdated;
import com.xgh.model.query.address.Address;
import com.xgh.model.query.address.AddressProjector;
import com.xgh.model.query.animal.Animal;
import com.xgh.model.query.animal.AnimalRepository;
import com.xgh.model.query.appointment.AppointmentRepository;
import com.xgh.model.query.veterinary.Veterinary;
import com.xgh.model.query.veterinary.VeterinaryRepository;
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
    public boolean isSubscribedTo(Event<?> event) {
        return event instanceof AppointmentWasDeleted
                || event instanceof AppointmentWasRegistered
                || event instanceof AppointmentWasUpdated;
    }

    @Override
    public void execute(Event<?> event) {
        Appointment entity = eventStore.pull(Appointment.class, event.getEntityId());

        Address addressProjection = addressProjector.execute(entity.getAddress());

        Optional<Animal> animal = animalRepository.findById(entity.getAnimal().getValue());
        Optional<Veterinary> veterinary = veterinaryRepository.findById(entity.getVeterinary().getValue());

        if (!animal.isPresent()) {
            throw new ProjectionFailedException(Animal.class.getSimpleName());
        }

        if (!veterinary.isPresent()) {
            throw new ProjectionFailedException(Veterinary.class.getSimpleName());
        }

        com.xgh.model.query.appointment.Appointment projection = new com.xgh.model.query.appointment.Appointment(
                entity.getId().getValue(),
                animal.get(),
                veterinary.get(),
                entity.getDateTime(),
                entity.getStatus().toString(),
                entity.getAppointmentType().toString(),
                entity.getPlace().toString(),
                addressProjection,
                entity.isDeleted());

        repository.save(projection);
    }
}
