package com.xgh.model.query.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xgh.model.query.postalcode.PostalCodeRepository;
import com.xgh.model.command.valueobjects.Address;
import com.xgh.model.command.valueobjects.PostalCode;

@Component
public class AddressProjector {

    @Autowired
    private PostalCodeRepository postalcodeRepository;

    @Autowired
    private AddressRepository addressRepository;

    public com.xgh.model.query.address.Address execute(Address address) {
        com.xgh.model.query.postalcode.PostalCode postalcodeProjection = projectPostalCode(address.getPostalCode());


        com.xgh.model.query.address.Address addressProjection = new com.xgh.model.query.address.Address(
                postalcodeProjection,
                address.getNumber(),
                address.getComplement());

        // TODO find by code and number and complement

        addressRepository.save(addressProjection);

        return addressProjection;
    }

    private com.xgh.model.query.postalcode.PostalCode projectPostalCode(PostalCode postalcode) {
        com.xgh.model.query.postalcode.PostalCode postalcodeProjection = new com.xgh.model.query.postalcode.PostalCode(
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
