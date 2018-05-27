package com.xgh.test.model.command.owner;

import com.xgh.buildingblocks.EventStore;
import com.xgh.model.command.owner.Owner;
import com.xgh.model.command.owner.OwnerId;
import com.xgh.model.command.valueobjects.Address;
import com.xgh.model.command.valueobjects.Cpf;
import com.xgh.model.command.valueobjects.Date;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Phone;
import com.xgh.model.command.valueobjects.PostalCode;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OwnerSampleData {
    @Autowired
    private EventStore eventStore;

    public Owner getSample() {
        Owner owner = new Owner();
        Address address = new Address(new PostalCode("87024-360", "Rua", "Garimpo", "Jardim Diamante", "Maringá", "PR", "Brasil"),
                389, null);
        owner.register(new OwnerId(), new Name("Zé do ai"), new Phone("44313371337"), new Cpf("09450600929"),
                new Date(LocalDate.of(1001, 01, 01)), address);
        eventStore.push(owner);
        return owner;
    }

}
