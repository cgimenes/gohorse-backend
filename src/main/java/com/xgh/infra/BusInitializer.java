package com.xgh.infra;

import com.xgh.buildingblocks.command.CommandBus;
import com.xgh.buildingblocks.event.EventBus;
import com.xgh.eventhandlers.LaboratoryProjector;
import com.xgh.eventhandlers.OwnerProjector;
import com.xgh.eventhandlers.VeterinaryProjector;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.laboratory.commandhandlers.LaboratoryDeletion;
import com.xgh.model.command.laboratory.commandhandlers.LaboratoryRegistration;
import com.xgh.model.command.laboratory.commandhandlers.LaboratoryUpdate;
import com.xgh.model.command.laboratory.commands.DeleteLaboratory;
import com.xgh.model.command.laboratory.commands.RegisterLaboratory;
import com.xgh.model.command.laboratory.commands.UpdateLaboratory;
import com.xgh.model.command.product.commandhandlers.ProductDeletion;
import com.xgh.model.command.product.commandhandlers.ProductRegistration;
import com.xgh.model.command.product.commandhandlers.ProductUpdate;
import com.xgh.model.command.product.commands.DeleteProduct;
import com.xgh.model.command.product.commands.RegisterProduct;
import com.xgh.model.command.product.commands.UpdateProduct;
import com.xgh.model.command.veterinary.commandhandlers.VeterinaryDeletion;
import com.xgh.model.command.veterinary.commandhandlers.VeterinaryRegistration;
import com.xgh.model.command.veterinary.commandhandlers.VeterinaryUpdate;
import com.xgh.model.command.veterinary.commands.DeleteVeterinary;
import com.xgh.model.command.veterinary.commands.RegisterVeterinary;
import com.xgh.model.command.veterinary.commands.UpdateVeterinary;
import com.xgh.model.command.owner.commandhandlers.OwnerDeletion;
import com.xgh.model.command.owner.commandhandlers.OwnerRegistration;
import com.xgh.model.command.owner.commandhandlers.OwnerUpdate;
import com.xgh.model.command.owner.commands.DeleteOwner;
import com.xgh.model.command.owner.commands.RegisterOwner;
import com.xgh.model.command.owner.commands.UpdateOwner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

// TODO ver se é possível criar um meio de inicializar dinamicamente os handlers
@Component
public class BusInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger logger = LogManager.getLogger(this.getClass());

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
        // Product
        CommandBus.addHandler(RegisterProduct.class, new ProductRegistration(eventStore));
        CommandBus.addHandler(UpdateProduct.class, new ProductUpdate(eventStore));
        CommandBus.addHandler(DeleteProduct.class, new ProductDeletion(eventStore));
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
