package com.xgh.test.model.query.animal;

import com.xgh.model.query.animal.Animal;
import com.xgh.model.query.animal.AnimalRepository;
import com.xgh.test.model.query.breed.BreedSampleData;
import com.xgh.test.model.query.owner.OwnerSampleData;
import com.xgh.test.model.query.specie.SpecieSampleData;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("QueryAnimalSampleData")
public class AnimalSampleData {
    private final AnimalRepository repository;
    private final OwnerSampleData ownerSampleData;
    private final BreedSampleData breedSampleData;
    private final SpecieSampleData specieSampleData;

    @Autowired
    protected AnimalSampleData(AnimalRepository repository, OwnerSampleData ownerSampleData, BreedSampleData breedSampleData, SpecieSampleData specieSampleData) {
        this.repository = repository;
        this.ownerSampleData = ownerSampleData;
        this.breedSampleData = breedSampleData;
        this.specieSampleData = specieSampleData;
    }

    public Animal getSample() {
        Animal entity = new Animal(
                UUID.randomUUID(),
                "Farelo",
                ownerSampleData.getSample(),
                breedSampleData.getSample(),
                specieSampleData.getSample(),
                'M',
                LocalDate.of(2012, 12, 12),
                false,
                new Float(100.50),
                false);
        repository.save(entity);
        return entity;
    }
}
