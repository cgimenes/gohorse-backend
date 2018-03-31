package com.xgh.xgh.veterinary.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xgh.infra.PostgresEventStore;
import com.xgh.xgh.veterinary.commandmodel.Veterinary;
import com.xgh.xgh.veterinary.commandmodel.VeterinaryId;
import com.xgh.xgh.veterinary.commandmodel.commandhandlers.VeterinaryRegistration;
import com.xgh.xgh.veterinary.commandmodel.commandhandlers.VeterinaryUpdate;
import com.xgh.xgh.veterinary.commandmodel.commands.RegisterVeterinary;
import com.xgh.xgh.veterinary.commandmodel.commands.UpdateVeterinary;

@Service
public class VeterinaryCommandService {
	@Autowired
	PostgresEventStore<Veterinary, VeterinaryId> eventStore;

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
