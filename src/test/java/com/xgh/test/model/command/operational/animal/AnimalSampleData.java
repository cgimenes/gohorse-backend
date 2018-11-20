package com.xgh.test.model.command.operational.animal;

import com.xgh.buildingblocks.EventStore;
import com.xgh.model.command.operational.animal.Animal;
import com.xgh.model.command.operational.animal.AnimalId;
import com.xgh.model.command.operational.enumerator.EnumeratorId;
import com.xgh.model.command.operational.owner.OwnerId;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.test.model.command.operational.owner.OwnerSampleData;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnimalSampleData {
    @Autowired
    private EventStore eventStore;

    @Autowired
    private OwnerSampleData ownerSampleData;

    public Animal getSample() {
        OwnerId ownerId = ownerSampleData.getSample().getId();
        //Enumerator specie = new Enumerator().register(new EnumeratorId("760ddc16-6e8d-4139-a684-da70ce7a657a"), new Name("specie"), new Description("animal"));
        //Enumerator raça = new Enumerator().register(new EnumeratorId("760ddc16-6e8d-4139-a684-da70ce7a657a"), new Name("raça"), new Description("chow chow"))

        Animal animal = new Animal();
        animal.register(new AnimalId(),
                new Name("Torresmo"),
                ownerId,
                new EnumeratorId(),
                new EnumeratorId(),
                new EnumeratorId(),
                LocalDate.parse("2018-10-10"),
                new Float(10.50),
                false);
        eventStore.push(animal);
        return animal;
    }
}
