package com.xgh.infra;

import com.xgh.model.owner.command.commands.DeleteOwner;
import com.xgh.model.owner.command.commands.RegisterOwner;
import com.xgh.model.owner.command.commands.UpdateOwner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.xgh.buildingblocks.command.CommandBus;
import com.xgh.buildingblocks.event.EventBus;
import com.xgh.eventhandlers.LaboratoryProjector;
import com.xgh.eventhandlers.OwnerProjector;
import com.xgh.eventhandlers.VeterinaryProjector;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.laboratory.command.commandhandlers.LaboratoryDeletion;
import com.xgh.model.laboratory.command.commandhandlers.LaboratoryRegistration;
import com.xgh.model.laboratory.command.commandhandlers.LaboratoryUpdate;
import com.xgh.model.laboratory.command.commands.DeleteLaboratory;
import com.xgh.model.laboratory.command.commands.RegisterLaboratory;
import com.xgh.model.laboratory.command.commands.UpdateLaboratory;
import com.xgh.model.owner.command.commandhandlers.OwnerDeletion;
import com.xgh.model.owner.command.commandhandlers.OwnerRegistration;
import com.xgh.model.owner.command.commandhandlers.OwnerUpdate;
import com.xgh.model.veterinary.command.commandhandlers.VeterinaryDeletion;
import com.xgh.model.veterinary.command.commandhandlers.VeterinaryRegistration;
import com.xgh.model.veterinary.command.commandhandlers.VeterinaryUpdate;
import com.xgh.model.veterinary.command.commands.DeleteVeterinary;
import com.xgh.model.veterinary.command.commands.RegisterVeterinary;
import com.xgh.model.veterinary.command.commands.UpdateVeterinary;


// TODO ver se é possível criar um meio de inicializar dinamicamente os handlers
@Component
public class BusInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private Logger logger = LogManager.getLogger(this.getClass());

    /*
     * Dependências dos handlers
     */
    // TODO injetar automaticamente
    @Autowired
    private PostgresEventStore eventStore;
    
    @Autowired
    private ApplicationContext context;

    private void initializeCommandBus() {
        logger.info("Inicializando command bus");
        // Laboratory
        CommandBus.addHandler(RegisterLaboratory.class, new LaboratoryRegistration(eventStore));
        CommandBus.addHandler(UpdateLaboratory.class, new LaboratoryUpdate(eventStore));
        CommandBus.addHandler(DeleteLaboratory.class, new LaboratoryDeletion(eventStore));
        // Veterinary
        CommandBus.addHandler(RegisterVeterinary.class, new VeterinaryRegistration(eventStore));
        CommandBus.addHandler(UpdateVeterinary.class, new VeterinaryUpdate(eventStore));
        CommandBus.addHandler(DeleteVeterinary.class, new VeterinaryDeletion(eventStore));
        // Owner
        CommandBus.addHandler(RegisterOwner.class, new OwnerRegistration(eventStore));
        CommandBus.addHandler(UpdateOwner.class, new OwnerUpdate(eventStore));
        CommandBus.addHandler(DeleteOwner.class, new OwnerDeletion(eventStore));
    }

    private void initializeEventBus() {
        logger.info("Inicializando event bus");
        EventBus.addHandler(context.getBean(LaboratoryProjector.class));
        EventBus.addHandler(context.getBean(VeterinaryProjector.class));
        EventBus.addHandler(context.getBean(OwnerProjector.class));
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initializeCommandBus();
        initializeEventBus();
        logger.info("Bus inicializados");
    }
}
