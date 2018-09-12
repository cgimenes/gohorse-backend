package com.xgh.test.model.command.operational.animal;

import com.xgh.buildingblocks.EventStore;
import com.xgh.model.command.operational.animal.Animal;
import com.xgh.model.command.operational.animal.AnimalId;
import com.xgh.model.command.operational.owner.OwnerId;
import com.xgh.model.command.operational.valueobjects.Name;
import com.xgh.model.command.operational.valueobjects.Sex;
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

        Animal animal = new Animal();
        animal.register(new AnimalId(),
                new Name("Torresmo"),
                ownerId,
                new Name("Sei lá"),
                new Name("Qualquer"),
                Sex.MALE,
                LocalDate.parse("2018-10-10"),
                new Float(10.50),
                false);
        eventStore.push(animal);
        return animal;
    }
}
