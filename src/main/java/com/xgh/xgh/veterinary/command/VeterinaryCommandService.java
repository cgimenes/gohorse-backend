package com.xgh.xgh.veterinary.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.xgh.veterinary.command.commandhandlers.VeterinaryRegistration;
import com.xgh.xgh.veterinary.command.commandhandlers.VeterinaryUpdate;
import com.xgh.xgh.veterinary.command.commands.RegisterVeterinary;
import com.xgh.xgh.veterinary.command.commands.UpdateVeterinary;

@Service
public class VeterinaryCommandService {
	@Autowired
	private PostgresEventStore<Veterinary, VeterinaryId> eventStore;

	public VeterinaryId registerVeterinary(RegisterVeterinary command) {
		VeterinaryRegistration handler = new VeterinaryRegistration(eventStore);
		handler.execute(command);
		return command.getId();
	}

	public void updateVeterinary(UpdateVeterinary command) {
		VeterinaryUpdate handler = new VeterinaryUpdate(eventStore);
		handler.execute(command);
	}
}
