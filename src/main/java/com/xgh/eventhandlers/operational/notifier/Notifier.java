package com.xgh.eventhandlers.operational.notifier;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.xgh.buildingblocks.EventStore;
import com.xgh.buildingblocks.event.EntityEvent;
import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.eventhandlers.operational.notifier.email.EmailClient;
import com.xgh.eventhandlers.operational.notifier.email.templates.CancelledOperation;
import com.xgh.eventhandlers.operational.notifier.email.templates.RegisteredOperation;
import com.xgh.eventhandlers.operational.notifier.email.templates.UpdatedOperation;
import com.xgh.model.command.operational.animal.Animal;
import com.xgh.model.command.operational.appointment.Appointment;
import com.xgh.model.command.operational.appointment.events.AppointmentWasCancelled;
import com.xgh.model.command.operational.appointment.events.AppointmentWasRegistered;
import com.xgh.model.command.operational.appointment.events.AppointmentWasUpdated;
import com.xgh.model.command.operational.owner.Owner;
import com.xgh.model.command.operational.surgery.Surgery;
import com.xgh.model.command.operational.surgery.events.SurgeryWasCancelled;
import com.xgh.model.command.operational.surgery.events.SurgeryWasRegistered;
import com.xgh.model.command.operational.surgery.events.SurgeryWasUpdated;
import com.xgh.model.command.operational.veterinary.Veterinary;

@Component
public class Notifier implements EventHandler {
    private static final Logger logger = LogManager.getLogger(Notifier.class);
    private final EventStore eventStore;
    private final EmailClient emailClient;

    @Autowired
    public Notifier(EventStore eventStore, EmailClient emailClient) {
        this.eventStore = eventStore;
        this.emailClient = emailClient;
    }

    @Override
    public boolean isSubscribedTo(Event event) {
        return Arrays
                .asList(AppointmentWasRegistered.class, AppointmentWasUpdated.class, AppointmentWasCancelled.class,
                        SurgeryWasRegistered.class, SurgeryWasUpdated.class, SurgeryWasCancelled.class)
                .contains(event.getClass());
    }

    @Override
    @Async
    public void execute(Event event) {
        try {
            if (event.getClass().getPackage().getName().contains("appointment")) {
                Appointment appointment = eventStore.pull(Appointment.class, ((EntityEvent<?>) event).getEntityId());
                Veterinary veterinary = eventStore.pull(Veterinary.class, appointment.getVeterinary());
                Animal animal = eventStore.pull(Animal.class, appointment.getAnimal());
                Owner owner = eventStore.pull(Owner.class, animal.getOwner());

                switch (event.getClass().getSimpleName()) {
                    case "AppointmentWasRegistered":
                        emailClient.sendEmail(owner.getEmail().toString(),
                                new RegisteredOperation("Consulta", owner.getName().getValue(), animal.getName().getValue(),
                                        veterinary.getName().getValue(), appointment.getDateTime()));
                        break;
                    case "AppointmentWasUpdated":
                        emailClient.sendEmail(owner.getEmail().toString(),
                                new UpdatedOperation("Consulta", owner.getName().getValue(), animal.getName().getValue(),
                                        veterinary.getName().getValue(), appointment.getDateTime()));
                        break;
                    case "AppointmentWasCancelled":
                        emailClient.sendEmail(owner.getEmail().toString(), new CancelledOperation("Consulta",
                                owner.getName().getValue(), animal.getName().getValue(), appointment.getDateTime()));
                        break;
                    default:
                        break;
                }

            } else if (event.getClass().getPackage().getName().contains("surgery")) {
                Surgery surgery = eventStore.pull(Surgery.class, ((EntityEvent<?>) event).getEntityId());
                Veterinary veterinary = eventStore.pull(Veterinary.class, surgery.getVeterinary());
                Animal animal = eventStore.pull(Animal.class, surgery.getAnimal());
                Owner owner = eventStore.pull(Owner.class, animal.getOwner());

                switch (event.getClass().getSimpleName()) {
                    case "SurgeryWasRegistered":
                        emailClient.sendEmail(owner.getEmail().toString(),
                                new RegisteredOperation("Cirurgia", owner.getName().getValue(), animal.getName().getValue(),
                                        veterinary.getName().getValue(), surgery.getDateTime()));
                        break;
                    case "SurgeryWasUpdated":
                        emailClient.sendEmail(owner.getEmail().toString(),
                                new UpdatedOperation("Cirurgia", owner.getName().getValue(), animal.getName().getValue(),
                                        veterinary.getName().getValue(), surgery.getDateTime()));
                        break;
                    case "SurgeryWasCancelled":
                        emailClient.sendEmail(owner.getEmail().toString(), new CancelledOperation("Cirurgia",
                                owner.getName().getValue(), animal.getName().getValue(), surgery.getDateTime()));
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception ex) {
            logger.fatal("Erro ao notificar proprietário", ex);
        }
    }

}
