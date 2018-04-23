package com.xgh.infra;

import com.xgh.buildingblocks.command.Command;
import com.xgh.buildingblocks.command.CommandBus;
import com.xgh.buildingblocks.command.CommandHandler;
import com.xgh.buildingblocks.event.EventBus;
<<<<<<< HEAD
import com.xgh.eventhandlers.LaboratoryProjector;
import com.xgh.eventhandlers.OwnerProjector;
import com.xgh.eventhandlers.VeterinaryProjector;
import com.xgh.eventhandlers.SupplierProjector;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.command.laboratory.commandhandlers.LaboratoryDeletion;
import com.xgh.model.command.laboratory.commandhandlers.LaboratoryRegistration;
import com.xgh.model.command.laboratory.commandhandlers.LaboratoryUpdate;
import com.xgh.model.command.laboratory.commands.DeleteLaboratory;
import com.xgh.model.command.laboratory.commands.RegisterLaboratory;
import com.xgh.model.command.laboratory.commands.UpdateLaboratory;
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
import com.xgh.model.command.supplier.commandhandlers.SupplierDeletion;
import com.xgh.model.command.supplier.commandhandlers.SupplierRegistration;
import com.xgh.model.command.supplier.commandhandlers.SupplierUpdate;
import com.xgh.model.command.supplier.commands.DeleteSupplier;
import com.xgh.model.command.supplier.commands.RegisterSupplier;
import com.xgh.model.command.supplier.commands.UpdateSupplier;


=======
import com.xgh.buildingblocks.event.EventHandler;
>>>>>>> origin/master
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

@Component
public class BusInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private final DefaultListableBeanFactory context;

    private final Reflections reflections = new Reflections("com.xgh");

    @Autowired
    public BusInitializer(DefaultListableBeanFactory context) {
        this.context = context;
    }

    private void initializeCommandBus() {
        logger.info("Inicializando command bus");
<<<<<<< HEAD
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
        //Supplier
        CommandBus.addHandler(RegisterSupplier.class, new SupplierRegistration(eventStore));
        CommandBus.addHandler(UpdateSupplier.class, new SupplierUpdate(eventStore));
        CommandBus.addHandler(DeleteSupplier.class, new SupplierDeletion(eventStore));
=======

        Set<Class<? extends CommandHandler>> handlers = reflections.getSubTypesOf(CommandHandler.class);
        for (Class<? extends CommandHandler> handler : handlers) {
            CommandBus.addHandler(getCommandClass(handler), context.getBean(handler));
        }
>>>>>>> origin/master
    }

    private void initializeEventBus() {
        logger.info("Inicializando event bus");
<<<<<<< HEAD
        EventBus.addHandler(context.getBean(LaboratoryProjector.class));
        EventBus.addHandler(context.getBean(VeterinaryProjector.class));
        EventBus.addHandler(context.getBean(OwnerProjector.class));
        EventBus.addHandler(context.getBean(SupplierProjector.class));
=======

        Set<Class<? extends EventHandler>> handlers = reflections.getSubTypesOf(EventHandler.class);
        for (Class<? extends EventHandler> handler : handlers) {
            EventBus.addHandler(context.getBean(handler));
        }
    }

    /*
     * Obtém a classe do comando que um dado command handler executa, usando reflection
     */
    @SuppressWarnings("unchecked")
    private Class<? extends Command> getCommandClass(Class<? extends CommandHandler> handlerClass) {
        ParameterizedType parameterizedType = (ParameterizedType) handlerClass.getGenericInterfaces()[0];
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        return (Class<? extends Command>) typeArguments[0];
>>>>>>> origin/master
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initializeCommandBus();
        initializeEventBus();
        logger.info("Bus inicializados");
    }
}
