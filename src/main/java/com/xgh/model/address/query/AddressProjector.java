package com.xgh.model.address.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xgh.model.postalcode.query.PostalCodeRepository;
import com.xgh.model.valueobjects.command.Address;
import com.xgh.model.valueobjects.command.PostalCode;

@Component
public class AddressProjector {
	
	@Autowired
	private PostalCodeRepository postalcodeRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public com.xgh.model.address.query.Address execute(Address address) {
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
