package com.xgh.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.xgh.buildingblocks.event.Event;
import com.xgh.buildingblocks.event.EventHandler;
import com.xgh.infra.repository.PostgresEventStore;
import com.xgh.model.address.query.AddressRepository;
import com.xgh.model.laboratory.command.Laboratory;
import com.xgh.model.laboratory.command.events.LaboratoryWasDeleted;
import com.xgh.model.laboratory.command.events.LaboratoryWasRegistered;
import com.xgh.model.laboratory.command.events.LaboratoryWasUpdated;
import com.xgh.model.laboratory.query.LaboratoryRepository;
import com.xgh.model.postalcode.query.PostalCodeRepository;
import com.xgh.model.valueobjects.Address;
import com.xgh.model.valueobjects.PostalCode;

@Component
public class LaboratoryProjector implements EventHandler {

	@Autowired
	private PostgresEventStore eventStore;
	
	@Autowired
	private LaboratoryRepository laboratoryRepository;
	
	@Autowired
	private PostalCodeRepository postalcodeRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public boolean isSubscribedTo(Event<?> event) {
		return event instanceof LaboratoryWasDeleted
			|| event instanceof LaboratoryWasRegistered
			|| event instanceof LaboratoryWasUpdated;
	}

	@Async
	@Override
	public void execute(Event<?> event) {
		Laboratory entity = eventStore.pull(Laboratory.class, event.getEntityId());
		

		com.xgh.model.address.query.Address addressProjection = projectAddress(entity.getAddress());
		
		com.xgh.model.laboratory.query.Laboratory laboratoryProjection = new com.xgh.model.laboratory.query.Laboratory(
				entity.getId().getValue(),
				entity.getCompanyName().getValue(),
				entity.getPhone().getValue(),
				addressProjection,
				entity.isDeleted());
		
		laboratoryRepository.save(laboratoryProjection);
	}
	
	private com.xgh.model.address.query.Address projectAddress(Address address) {
		com.xgh.model.postalcode.query.PostalCode postalcodeProjection = projectPostalCode(address.getPostalCode());
		

		com.xgh.model.address.query.Address addressProjection = new com.xgh.model.address.query.Address(
				postalcodeProjection,
				address.getNumber(),
				address.getComplement());
		
		// TODO find by code and number and complement
		
		addressRepository.save(addressProjection);
		
		return addressProjection;
	}

	private com.xgh.model.postalcode.query.PostalCode projectPostalCode(PostalCode postalcode) {
		com.xgh.model.postalcode.query.PostalCode postalcodeProjection = new com.xgh.model.postalcode.query.PostalCode(
				postalcode.getCode(),
				postalcode.getStreetType(),
				postalcode.getStreetName(),
				postalcode.getNeighbourhood(),
				postalcode.getCity(),
				postalcode.getState(),
				postalcode.getCountry());
		
		postalcodeRepository.save(postalcodeProjection);
		
		return postalcodeProjection;
	}

}
