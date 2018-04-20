package com.xgh.model.query.address;

import com.xgh.model.query.address.postalcode.PostalCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xgh.model.query.address.postalcode.PostalCodeRepository;

@Component
public class AddressProjector {

    @Autowired
    private PostalCodeRepository postalcodeRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Address execute(com.xgh.model.command.valueobjects.Address address) {
        PostalCode postalcodeProjection = projectPostalCode(address.getPostalCode());


        Address addressProjection = new Address(
                postalcodeProjection,
                address.getNumber(),
                address.getComplement());

        // TODO find by code and number and complement

        addressRepository.save(addressProjection);

        return addressProjection;
    }

    private PostalCode projectPostalCode(com.xgh.model.command.valueobjects.PostalCode postalcode) {
        PostalCode postalcodeProjection = new PostalCode(
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
