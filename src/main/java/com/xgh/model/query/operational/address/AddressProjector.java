package com.xgh.model.query.operational.address;

import com.xgh.model.query.operational.address.postalcode.PostalCode;
import com.xgh.model.query.operational.address.postalcode.PostalCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressProjector {
    private final PostalCodeRepository postalcodeRepository;
    private final AddressRepository addressRepository;

    @Autowired
    protected AddressProjector(PostalCodeRepository postalcodeRepository, AddressRepository addressRepository) {
        this.postalcodeRepository = postalcodeRepository;
        this.addressRepository = addressRepository;
    }

    public Address execute(com.xgh.model.command.operational.valueobjects.Address address) {
        PostalCode postalcodeProjection = projectPostalCode(address.getPostalCode());

        Address addressProjection = new Address(
                postalcodeProjection,
                address.getNumber(),
                address.getComplement());

        // TODO find by code and number and complement

        addressRepository.save(addressProjection);

        return addressProjection;
    }

    private PostalCode projectPostalCode(com.xgh.model.command.operational.valueobjects.PostalCode postalcode) {
        PostalCode postalcodeProjection = new PostalCode(
                postalcode.getCode(),
                postalcode.getStreetName(),
                postalcode.getNeighbourhood(),
                postalcode.getCity(),
                postalcode.getState(),
                postalcode.getCountry());

        postalcodeRepository.save(postalcodeProjection);

        return postalcodeProjection;
    }
}
