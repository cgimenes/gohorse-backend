package com.xgh.test.model.command.operational.valueobjects;

import com.xgh.model.command.operational.valueobjects.Address;
import com.xgh.model.command.operational.valueobjects.PostalCode;
import org.springframework.stereotype.Component;

@Component("CommandAddressSampleData")
public class AddressSampleData {
    public Address getSample() {
        return new Address(getSamplePostalCode(), 33, "Apartamento 69");
    }

    public PostalCode getSamplePostalCode() {
        return new PostalCode(
                "87005-140",
                "Rua",
                "Rua Tabaete",
                "Jardim Tabaetê",
                "Maringá",
                "Paraná",
                "Brasil");
    }
}
