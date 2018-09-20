package com.xgh.test.model.command.veterinary;

import com.xgh.buildingblocks.EventStore;
import com.xgh.model.command.valueobjects.Address;
import com.xgh.model.command.valueobjects.Crmv;
import com.xgh.model.command.valueobjects.Email;
import com.xgh.model.command.valueobjects.Name;
import com.xgh.model.command.valueobjects.Phone;
import com.xgh.model.command.valueobjects.PostalCode;
import com.xgh.model.command.veterinary.Veterinary;
import com.xgh.model.command.veterinary.VeterinaryId;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VeterinarySampleData {
    @Autowired
    private EventStore eventStore;

    public Veterinary getSample() {
        Veterinary veterinary = new Veterinary();
        veterinary.register(new VeterinaryId(),
                new Name("Ricardo Requena"),
                new Address(new PostalCode("87043-050", "Rua", "Rio Andaraí", "Oásis", "Maringá", "PR", "Brasil"), 374,
                        null),
                new Phone("44998015821"), new Crmv("9375"), new Email("espacoanimal.vet@hotmail.com"),
                LocalDate.of(1986, 10, 03));
        eventStore.push(veterinary);
        return veterinary;
    }
}
