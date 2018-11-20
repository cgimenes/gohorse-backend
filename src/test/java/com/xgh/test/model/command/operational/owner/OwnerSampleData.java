package com.xgh.test.model.command.operational.owner;

import com.xgh.buildingblocks.EventStore;
import com.xgh.model.command.operational.owner.Owner;
import com.xgh.model.command.operational.owner.OwnerId;
import com.xgh.model.command.operational.valueobjects.Cpf;
import com.xgh.model.command.operational.valueobjects.Email;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Phone;
import com.xgh.test.model.command.operational.valueobjects.AddressSampleData;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OwnerSampleData {
    @Autowired
    private EventStore eventStore;

    @Autowired
    private AddressSampleData addressSampleData;

    public Owner getSample() {
        Owner owner = new Owner();
        owner.register(new OwnerId(), new Name("Zé do ai"), new Phone("44313371337"), new Cpf("09450600929"),
                LocalDate.of(1001, 01, 01), addressSampleData.getSample(), new Email("teste@teste.com"));
        eventStore.push(owner);
        return owner;
    }
}
