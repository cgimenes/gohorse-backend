package com.xgh.test.model.command.valueobjects;

import com.xgh.model.command.valueobjects.Address;
import com.xgh.model.command.valueobjects.PostalCode;

import org.springframework.stereotype.Component;

@Component("AddressSampleData")
public class AddressSampleData {

	public Address getSample() {
		Address address = new Address(new PostalCode( "87005-140", "Rua", "Rua Tabaete", "Jardim Tabaetê", "Maringá", "Paraná", "Brasil"), 33, "Apartamento 69");
        return address;
	}
}
