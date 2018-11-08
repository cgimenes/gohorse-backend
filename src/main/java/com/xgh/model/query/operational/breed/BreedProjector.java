package com.xgh.model.query.operational.breed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BreedProjector {
    private final BreedRepository breedRepository;

    @Autowired
    public BreedProjector(BreedRepository breedRepository) {
        this.breedRepository = breedRepository;
    }

    public Breed execute(com.xgh.model.command.operational.valueobjects.Name breed) {
        Breed breedProjection = new Breed(
                breed.toString());
        breedRepository.save(breedProjection);

        return breedProjection;
    }
}
