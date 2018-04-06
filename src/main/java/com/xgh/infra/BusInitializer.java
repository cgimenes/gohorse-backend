package com.xgh.infra;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.xgh.buildingblocks.CommandBus;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.xgh.laboratory.command.Laboratory;
import com.xgh.xgh.laboratory.command.LaboratoryId;
import com.xgh.xgh.laboratory.command.commandhandlers.LaboratoryDeletion;
import com.xgh.xgh.laboratory.command.commandhandlers.LaboratoryRegistration;
import com.xgh.xgh.laboratory.command.commandhandlers.LaboratoryUpdate;
import com.xgh.xgh.laboratory.command.commands.DeleteLaboratory;
import com.xgh.xgh.laboratory.command.commands.RegisterLaboratory;
import com.xgh.xgh.laboratory.command.commands.UpdateLaboratory;
import com.xgh.xgh.veterinary.command.Veterinary;
import com.xgh.xgh.veterinary.command.VeterinaryId;
import com.xgh.xgh.veterinary.command.commandhandlers.VeterinaryDeletion;
import com.xgh.xgh.veterinary.command.commandhandlers.VeterinaryRegistration;
import com.xgh.xgh.veterinary.command.commandhandlers.VeterinaryUpdate;
import com.xgh.xgh.veterinary.command.commands.DeleteVeterinary;
import com.xgh.xgh.veterinary.command.commands.RegisterVeterinary;
import com.xgh.xgh.veterinary.command.commands.UpdateVeterinary;

// TODO ver se é possível criar um meio de inicializar dinamicamente os handlers
@Component
public class BusInitializer implements ApplicationListener<ContextRefreshedEvent> {
	private Logger logger = LogManager.getLogger(this.getClass());
	
	/*
	 * Dependências dos handlers
	 */
    @Autowired
    private PostgresEventStore<Laboratory, LaboratoryId> laboratoryEventStore;
    
    @Autowired
	private PostgresEventStore<Veterinary, VeterinaryId> veterinaryEventStore;
	
	private void initializeCommandBus() {
		logger.info("Inicializando command bus");
		// Laboratory
		CommandBus.addHandler(RegisterLaboratory.class, new LaboratoryRegistration(laboratoryEventStore));
		CommandBus.addHandler(UpdateLaboratory.class, new LaboratoryUpdate(laboratoryEventStore));
		CommandBus.addHandler(DeleteLaboratory.class, new LaboratoryDeletion(laboratoryEventStore));
		// Veterinary
		CommandBus.addHandler(RegisterVeterinary.class, new VeterinaryRegistration(veterinaryEventStore));
		CommandBus.addHandler(UpdateVeterinary.class, new VeterinaryUpdate(veterinaryEventStore));
		CommandBus.addHandler(DeleteVeterinary.class, new VeterinaryDeletion(veterinaryEventStore));
	}

	private void initializeEventBus() {
		logger.info("Inicializando event bus");
	}

	@Override 
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initializeCommandBus();
		initializeEventBus();
		logger.info("Bus inicializados");
    }
}
