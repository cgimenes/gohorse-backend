package com.xgh.xgh.owner.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xgh.infra.PostgresEventStore;
import com.xgh.xgh.owner.commandmodel.Owner;
import com.xgh.xgh.owner.commandmodel.OwnerId;
import com.xgh.xgh.owner.commandmodel.commandhandlers.OwnerRegistration;
import com.xgh.xgh.owner.commandmodel.commandhandlers.OwnerUpdate;
import com.xgh.xgh.owner.commandmodel.commands.RegisterOwner;
import com.xgh.xgh.owner.commandmodel.commands.UpdateOwner;

@Service
public class OwnerCommandService {
	@Autowired
	private PostgresEventStore<Owner, OwnerId> eventStore;
	
	public OwnerId registerOwner(RegisterOwner command) {
		OwnerRegistration handler = new OwnerRegistration(eventStore);
		handler.execute(command);
		return command.getId();
	}

	public void updateOwner(UpdateOwner command) {
		OwnerUpdate handler = new OwnerUpdate(eventStore);
		handler.execute(command);
		
	}

}
