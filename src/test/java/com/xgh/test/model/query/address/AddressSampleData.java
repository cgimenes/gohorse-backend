package com.xgh.test.model.query.address;

import com.xgh.model.query.address.Address;
import com.xgh.model.query.address.AddressRepository;
import com.xgh.model.query.address.postalcode.PostalCode;
import com.xgh.model.query.address.postalcode.PostalCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressSampleData {
    @Autowired
    private PostalCodeRepository postalCodeRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Address getSampleAddress() {
        PostalCode postalCode = new PostalCode("87040-000", "Avenida", "Do Toninho", "Bairro Pacífico", "Londrina", "Paraná", "Brasil");
        postalCodeRepository.save(postalCode);
        Address address = new Address(postalCode, 123, null);
        addressRepository.save(address);
        return address;
    }
}
