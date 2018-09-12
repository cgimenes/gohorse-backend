package com.xgh.test.model.query.operational.breed;

import com.xgh.model.query.operational.breed.Breed;
import com.xgh.model.query.operational.breed.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("QueryBreedSampleData")
public class BreedSampleData {
    private final BreedRepository breedRepository;

    @Autowired
    protected BreedSampleData(BreedRepository breedRepository) {
        this.breedRepository = breedRepository;
    }

    public Breed getSample() {
        Breed breed = new Breed("Tremedeira");
        breedRepository.save(breed);
        return breed;
    }
}
